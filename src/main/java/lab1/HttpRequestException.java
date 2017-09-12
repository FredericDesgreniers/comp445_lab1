package lab1;

import java.io.IOException;

public class HttpRequestException extends IOException {
    public HttpRequestException(HttpRequest request, String message){
        super(request.host + ": "+message);
    }
}
