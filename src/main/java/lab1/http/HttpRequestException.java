package lab1.http;

import java.io.IOException;

public class HttpRequestException extends IOException {
    public HttpRequestException(HttpRequest request, String message){
        super(request.getRequestInfo().getHost() + ": "+message);
    }
}
