package lab1.http;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import lab1.http.sockets.IoSocketConnection;
import lab1.http.sockets.IoSocketException;

public class HttpResponseReader {

    private HttpRequestInfo httpRequestInfo;
    private IoSocketConnection socketConnection;
    HttpResponseBuilder builder;
    
    PrintWriter outputWriter;
    
    int contentLenght = -1;
    
    public HttpResponseReader(HttpRequestInfo httpRequestInfo, IoSocketConnection socketConnection){
        this.httpRequestInfo = httpRequestInfo;
        this.socketConnection = socketConnection;
         builder = new HttpResponseBuilder();
         
         if(httpRequestInfo.getOutputFilePath().length() > 0)
         {
             try {
                 outputWriter = new PrintWriter(httpRequestInfo.getOutputFilePath(), "UTF-8");
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
         }
    }
    
    public HttpResponse getResponse()
    {
        System.out.println("Getting response!");
        
        addStatusCodeToBuilder();

        addHeadersToBuilder();

        addBodyToBuilder();
        
        if(outputWriter != null)
        {
            outputWriter.close();
        }
        
        return builder.build();
    }
    

    
    public void addStatusCodeToBuilder()
    {
        builder.setStatusCode(parseLineAsStatusCode());
    }
    
    public int parseLineAsStatusCode()
    {
        try {
            String line = socketConnection.getLine();
            int statusCode = Integer.valueOf(line.split(" ")[1]);

            tryToOutputToFile(line+"\n");
            
            return statusCode;
        } catch (IoSocketException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public void addHeadersToBuilder()
    {
        try{
            String line;
            
            do{
                
                line = socketConnection.getLine();
                builder.addHeaderData(line);

                tryToOutputToFile(line+"\n");
                
                if(line.startsWith("Content-Length"))
                {
                    contentLenght = Integer.valueOf(line.split(":")[1].trim());
                }
                
            }while(!line.equalsIgnoreCase(""));
            
        } catch (IoSocketException e) {
            e.printStackTrace();
        }
    }
    
    public void addBodyToBuilder()
    {
        builder.setBodyData(readBody());
    }
    
    public String readBody()
    {
        String bodyData = "";
        String line;

        try {
            while(((line = socketConnection.getLine()) != null) && (bodyData.length() < contentLenght))
            {
                bodyData += (line + "\n");
                tryToOutputToFile(line+"\n");
                
                if((bodyData.length() >= contentLenght) && contentLenght > 0)
                {
                    break;
                }
            }
        } catch (IoSocketException e) {
            //e.printStackTrace();
        }
        
        return bodyData;
    }
    
    public void tryToOutputToFile(String line)
    {
        if(outputWriter != null)
        {
            outputWriter.write(line);
        }
    }
    
}
