package lab1;

import lab1.http.*;
import lab1.http.sockets.*;

public class Start {
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest(new HttpRequestInfo(HttpRequestType.GET, "httpbin.org", 80));
        try {
            HttpRequestConnection requestConnection = request.connectOnPath("/get");
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
