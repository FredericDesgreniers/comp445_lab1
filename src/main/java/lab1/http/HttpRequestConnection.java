package lab1.http;

import lab1.sockets.IoSocketConnection;

public class HttpRequestConnection {
    private IoSocketConnection socketConnection;
    private HttpRequestInfo requestInfo;
    
    public HttpRequestConnection(HttpRequestInfo requestInfo, IoSocketConnection socketConnection){
        this.socketConnection = socketConnection;
        this.requestInfo = requestInfo;
    }

    public void sendRequestMessage(){
        sendRequestLine();
        sendHeaders();
        sendStandardEmptyLine();
        socketConnection.sendBufferToHost();
    }

    private void sendRequestLine(){
        socketConnection.sendLineToBuffer(requestInfo.getRequestType().toString()+" / HTTP/1.0");
    }

    private void sendHeaders(){
        socketConnection.sendLineToBuffer("Host: "+requestInfo.getHost()+":"+requestInfo.getPort());
        socketConnection.sendLineToBuffer("User-Agent: Mozilla/5.0");
    }

    private void sendStandardEmptyLine(){
        socketConnection.sendLineToBuffer("");
    }
    
    public IoSocketConnection getSocketConnection(){
        return socketConnection;
    }
}
