package lab1.http;

import lab1.http.sockets.IoSocketConnection;

public class HttpRequestConnection {
    private IoSocketConnection socketConnection;
    private HttpRequestInfo requestInfo;
    private String path;
    
    public HttpRequestConnection(HttpRequestInfo requestInfo, IoSocketConnection socketConnection, String path){
        this.socketConnection = socketConnection;
        this.requestInfo = requestInfo;
        this.path = path;
    }

    public void sendRequestMessage(){
        sendRequestLine();
        sendHeaders();
        sendStandardEmptyLine();
        socketConnection.sendBufferToHost();
    }

    private void sendRequestLine(){
        socketConnection.sendLineToBuffer(requestInfo.getRequestType().toString()+" "+path+" HTTP/1.0");
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
