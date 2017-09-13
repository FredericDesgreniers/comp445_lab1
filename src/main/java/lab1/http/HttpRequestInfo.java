package lab1.http;

public class HttpRequestInfo {
    private HttpRequestType requestType;
    private String host;
    private int port;

    public HttpRequestInfo(HttpRequestType requestType, String host, int port) {
        this.requestType = requestType;
        this.host = host;
        this.port = port;
    }

    public HttpRequestType getRequestType() {
        return requestType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}   
