package lab1.http;

import java.io.IOException;
import lab1.http.sockets.IoSocket;
import lab1.http.sockets.IoSocketConnection;
import lab1.http.sockets.IoSocketException;

public class HttpRequest {
    private HttpRequestInfo requestInfo;
    private IoSocket socket;
    
    public HttpRequest(HttpRequestInfo requestInfo){
        this.requestInfo = requestInfo;
        socket = new IoSocket(requestInfo.getHost(), requestInfo.getPort());
    }
    
    public HttpRequestConnection connect() throws HttpRequestException {
        try {
            return tryToConnect();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpRequestException(this, "Could not connect");
        }
    }
    
    private HttpRequestConnection tryToConnect() throws IOException {
        return new HttpRequestConnection(requestInfo, createHttpSocketConnection());
    }
    
    private IoSocketConnection createHttpSocketConnection() throws IoSocketException {
        IoSocketConnection socketConnection = socket.connect();
        socketConnection.setOutputStream(HttpPrintWriter.class);
        
        return socketConnection;
    }

    public HttpRequestInfo getRequestInfo() {
        return requestInfo;
    }
}
