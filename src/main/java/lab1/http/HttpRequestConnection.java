package lab1.http;

import lab1.http.sockets.IoSocketConnection;

public class HttpRequestConnection {
    private IoSocketConnection socketConnection;
    private HttpRequestConnectionInfo requestConnectionInfo;
    
    public HttpRequestConnection(HttpRequestConnectionInfo requestConnectionInfo, IoSocketConnection socketConnection){
        this.socketConnection = socketConnection;
        this.requestConnectionInfo = requestConnectionInfo;
    }

    public void sendRequestMessage(){
        sendRequestLine();
        sendHeaders();
        sendStandardEmptyLine();
        socketConnection.sendBufferToHost();
    }

    private void sendRequestLine(){
        socketConnection.sendLineToBuffer(requestConnectionInfo.getHttpRequestInfo().getRequestType().toString()+" "+requestConnectionInfo.getPath()+" HTTP/1.0");
    }

    private void sendHeaders(){
        socketConnection.sendLineToBuffer("Host: "+requestConnectionInfo.getHttpRequestInfo().getHost()+":"+requestConnectionInfo.getHttpRequestInfo().getPort());
        socketConnection.sendLineToBuffer("User-Agent: Mozilla/5.0");
    }

    private void sendStandardEmptyLine(){
        socketConnection.sendLineToBuffer("");
    }
    
    public IoSocketConnection getSocketConnection(){
        return socketConnection;
    }
}
