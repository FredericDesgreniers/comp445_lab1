package lab1.http;


public class HttpRequestConnectionInfo {
    private HttpRequestInfo requestInfo;
    private String path;
    
    public HttpRequestConnectionInfo(HttpRequestInfo requestInfo, String path){

        this.requestInfo = requestInfo;
        this.path = path;
    }
    
    public HttpRequestInfo getHttpRequestInfo(){
        return requestInfo;
    }
    
    public String getPath(){
        return path;
    }
}
