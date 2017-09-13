package lab1;

public class HttpRequest {
    private HttpRequestInfo requestInfo;
    private IoSocket socket;
    
    public HttpRequest(HttpRequestInfo requestInfo){
        this.requestInfo = requestInfo;
        socket = new IoSocket(requestInfo.getHost(), requestInfo.getPort());
    }
    
    public HttpRequestConnection connect() throws HttpRequestException {
        try {
            return tryConnect();
        } catch (IoSocketException e) {
            e.printStackTrace();
            throw new HttpRequestException(this, "Could not connect");
        }
    }
    
    private HttpRequestConnection tryConnect() throws IoSocketException {
        return new HttpRequestConnection(requestInfo, socket.connect());
    }
    

    public IoSocket getSocket() {
        return socket;
    }

    public HttpRequestInfo getRequestInfo() {
        return requestInfo;
    }
}
