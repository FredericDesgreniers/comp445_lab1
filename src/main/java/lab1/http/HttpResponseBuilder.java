package lab1.http;

import java.util.ArrayList;

public class HttpResponseBuilder {
    private int statusCode;
    private ArrayList<String> headerData = new ArrayList<>();
    private String bodyData = "";
    
    public HttpResponseBuilder()
    {
        
    }
    
    public HttpResponseBuilder setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
        return this;    
    }
    
    public HttpResponseBuilder addHeaderData(String headerData)
    {
        this.headerData.add(headerData);
        return this;
    }
    
    public HttpResponseBuilder setBodyData(String bodyData)
    {
        this.bodyData = bodyData;
        return this;
    }
    
    
    public HttpResponse build()
    {
        return new HttpResponse(statusCode, headerData, bodyData);
    }
}
