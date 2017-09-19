package lab1;

import lab1.http.*;
import lab1.http.sockets.*;
import lab1.parser.HttpCommandRequestBuilder;
import lab1.parser.ParamParser;

public class Start {
    public static void main(String[] args) {

        HttpCommandRequestBuilder httpCommandRequestBuilder = new HttpCommandRequestBuilder();
        
        ParamParser parser = new ParamParser(httpCommandRequestBuilder);
        parser.parse("post -h Content-Type:application/json -d '{\"Assignment\": 1}' http://httpbin.org/post");
        
        HttpRequest request = new HttpRequest(httpCommandRequestBuilder.buildRequestInfo());
        
        
        try {
            HttpRequestConnection requestConnection = request.connect();
            requestConnection.sendRequestMessage();
            
            printSocketResponse(requestConnection.getSocketConnection());
            
        } catch (HttpRequestException e) {
            e.printStackTrace();
        } catch (IoSocketException e) {
            e.printStackTrace();
        }
    }
    
    public static void printSocketResponse(IoSocketConnection socketConnection) throws IoSocketException {
        System.out.println("---------- RESPONSE ----------");
        String line;
        while((line = socketConnection.getLine()) != null)
        {
            System.out.println(line);
        }
    }
}
