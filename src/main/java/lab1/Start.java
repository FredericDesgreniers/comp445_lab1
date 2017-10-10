package lab1;

import lab1.http.*;
import lab1.http.sockets.*;
import lab1.parser.HttpCommandRequestBuilder;
import lab1.parser.ParamParser;

public class Start {
    public static void main(String[] args) {

        String arg = "post -h Content-Type:application/json -f test.json -o out.txt http://httpbin.org/post";
        
        //arg = "get http://httpbin.org/redirect/1";
        
        HttpCommandRequestBuilder httpCommandRequestBuilder = new HttpCommandRequestBuilder();
        
        ParamParser parser = new ParamParser(httpCommandRequestBuilder);
        parser.parse(arg);
        
        HttpRequest request = new HttpRequest(httpCommandRequestBuilder.buildRequestInfo());
        
        
        try {
            HttpRequestConnection requestConnection = request.connect();
            requestConnection.sendRequestMessage();
            
            HttpResponseReader responseReader = new HttpResponseReader(requestConnection.getRequestConnectionInfo(), requestConnection.getSocketConnection());
            HttpResponse response = responseReader.getResponse();
            
            System.out.println(response.getStatusCode());
            response.getHeaderData().forEach(System.out::println);
            String body = response.getBodyData();
            System.out.println(body);
            
            
        } catch (HttpRequestException e) {
            e.printStackTrace();
        } 
    }
    
}
