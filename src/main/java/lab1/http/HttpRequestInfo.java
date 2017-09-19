package lab1.http;

import java.util.ArrayList;
import java.util.List;

public class HttpRequestInfo {
    private HttpRequestType requestType;
    private String host = "";
    private String path = "/";
    private int port;

    private List<String> additionalHeaders;
    
    private boolean verbose = false;
    
    private String inlineData = "";
    
    public HttpRequestInfo(HttpRequestType requestType, String url) {
        this.requestType = requestType;
        setUrl(url);
        this.port = 80;
        additionalHeaders = new ArrayList<>();
    }

    public void setUrl(String url){
        if(url.startsWith("http://")){
            // is http
            url = url.substring(url.indexOf("http://")+"http://".length());
        }
        
        int firstBarLocation = url.indexOf('/');
        path = url.substring(firstBarLocation);
        host = url.substring(0, firstBarLocation);
        
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
    
    public String getPath(){
        return path;
    }
    
    public void addHeader(String header){
        additionalHeaders.add(header);
    }
    
    public List<String> getAdditionalHeaders(){
        return additionalHeaders;
    }
    
    public void setVerbose(boolean v){
        verbose = v;
    }
    
    public void setInlineData(String inlineData){
        this.inlineData = inlineData;
    }
    
    public boolean getVerbose(){
        return verbose;
    }

    public String getInlineData() {
        return inlineData;
    }
}   
