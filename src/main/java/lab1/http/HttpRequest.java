package lab1.http;

import lab1.http.sockets.IoSocket;
import lab1.http.sockets.IoSocketException;

public class HttpRequest {
    private HttpRequestInfo requestInfo;
    private IoSocket socket;
    
    public HttpRequest(HttpRequestInfo requestInfo){
        this.requestInfo = requestInfo;
        socket = new IoSocket(requestInfo.getHost(), requestInfo.getPort());
    }
    
    public HttpRequestConnection connectOnPath(String path) throws HttpRequestException {
        try {
            return tryToConnectOnPath(path);
        } catch (IoSocketException e) {
            e.printStackTrace();
            throw new HttpRequestException(this, "Could not connect");
        }
    }
    
    private HttpRequestConnection tryToConnectOnPath(String path) throws IoSocketException {
        return new HttpRequestConnection(requestInfo, socket.connect(), path);
    }

    public HttpRequestInfo getRequestInfo() {
        return requestInfo;
    }
}
