package lab1;

import java.io.BufferedReader;

public class Start {
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest("httpbin.org");
        try {
            request.connect();
            request.sendRequestMessage();
            
            System.out.println("---------- RESPONSE ----------");
            
            String line;
            while((line = request.getSocket().getLine()) != null)
            {
                System.out.println(line);
            }
        } catch (HttpRequestException e) {
            e.printStackTrace();
        } catch (IoSocketException e) {
            e.printStackTrace();
        }
    }
}
