package lab1.http;

import lab1.http.sockets.IoSocketConnection;

public class HttpRequestConnection {
    private IoSocketConnection socketConnection;
    private HttpRequestInfo requestConnectionInfo;
    
    public HttpRequestConnection(HttpRequestInfo requestConnectionInfo, IoSocketConnection socketConnection){
        this.socketConnection = socketConnection;
        this.requestConnectionInfo = requestConnectionInfo;
    }

    public void sendRequestMessage(){
        sendRequestLine();
        sendHeaders();
        sendStandardEmptyLine();
        sendInlineData();
        socketConnection.sendBufferToHost();
    }

    private void sendRequestLine(){
        socketConnection.sendLineToBuffer(requestConnectionInfo.getRequestType().toString()+" "+requestConnectionInfo.constructPathWithQuery()+" HTTP/1.1");
    }

    private void sendHeaders(){
        socketConnection.sendLineToBuffer("Host: "+requestConnectionInfo.getHost()+":"+requestConnectionInfo.getPort());
        socketConnection.sendLineToBuffer("User-Agent: Mozilla/5.0");
        
        for(String header : requestConnectionInfo.getAdditionalHeaders()){
            socketConnection.sendLineToBuffer(header);
        }

        sendInlineDataHeader();
    }
    
    private void sendInlineDataHeader(){
        String inlineData = requestConnectionInfo.getInlineData();
        if(inlineData.length() > 0){
            socketConnection.sendLineToBuffer("Content-Length:"+inlineData.length());
        }
    }

    private void sendStandardEmptyLine(){
        socketConnection.sendLineToBuffer("");
    }
    
    private void sendInlineData(){
        String inlineData = requestConnectionInfo.getInlineData();
        if(inlineData.length() > 0){
            socketConnection.sendLineToBuffer(inlineData);
        }
    }
    
    public IoSocketConnection getSocketConnection(){
        return socketConnection;
    }
}
