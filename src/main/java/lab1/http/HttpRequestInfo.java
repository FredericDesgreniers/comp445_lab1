package lab1.http;

import java.util.ArrayList;
import java.util.List;

public class HttpRequestInfo {
    private HttpRequestType requestType;
    private String host = "";
    private String path = "/";
    private String query = "";
    private int port;

    private List<String> additionalHeaders;
    
    private boolean verbose = false;
    
    private String inlineData = "";
    
    public HttpRequestInfo(HttpRequestType requestType, String url) {
        this.requestType = requestType;
        setUrl(url);
        this.port = 80;
        additionalHeaders = new ArrayList<>();
    }

    public void setUrl(String url){
        if(url.startsWith("http://")){
            url = url.substring(url.indexOf("http://")+"http://".length());
        }
        
        int firstBarLocation = url.indexOf('/');
        
        host = url.substring(0, firstBarLocation);
        String pathAndQuery = url.substring(firstBarLocation);
        
        int queryStartIndex = pathAndQuery.indexOf('?');
        if(queryStartIndex > 0){
            path = pathAndQuery.substring(0, queryStartIndex);
            query = pathAndQuery.substring(queryStartIndex);
        }else{
            path = pathAndQuery;
        }
    }
    
    public HttpRequestType getRequestType() {
        return requestType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    
    public String getPath(){
        return path;
    }
    
    public void addHeader(String header){
        additionalHeaders.add(header);
    }
    
    public List<String> getAdditionalHeaders(){
        return additionalHeaders;
    }
    
    public void setVerbose(boolean v){
        verbose = v;
    }
    
    public void setInlineData(String inlineData){
        this.inlineData = inlineData;
    }
    
    public boolean getVerbose(){
        return verbose;
    }

    public String getInlineData() {
        return inlineData;
    }
    
    public String constructPathWithQuery(){
        return path + query;
    }
}   
