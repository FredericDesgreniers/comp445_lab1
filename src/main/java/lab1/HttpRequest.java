package lab1;

import java.io.DataOutputStream;

public class HttpRequest {
    protected final String host;
    private IoSocket socket;
    
    public HttpRequest(String host){
        this.host = host;
        socket = new IoSocket(host);
    }
    
    public void connect() throws HttpRequestException {
        try {
            tryConnect();
        } catch (IoSocketException e) {
            e.printStackTrace();
            throw new HttpRequestException(this, "Could not connect");
        }
    }
    
    private void tryConnect() throws IoSocketException {
        socket.connect();
    }

    public IoSocket getSocket() {
        return socket;
    }
    
    public void sendRequestMessage(){
        sendRequestLine();
        sendHeaders();
        sendStandardEmptyLine();
        socket.sendBufferToHost();
    }
    
    private void sendRequestLine(){
        socket.sendLineToBuffer("GET / HTTP/1.0");
    }
    
    private void sendHeaders(){
        socket.sendLineToBuffer("Host: "+host+":80");
        socket.sendLineToBuffer("User-Agent: Mozilla/5.0");
    }
    
    private void sendStandardEmptyLine(){
        socket.sendLineToBuffer("");
    }
}
