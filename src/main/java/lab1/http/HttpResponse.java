package lab1.http;

import java.util.ArrayList;

public class HttpResponse {
    private int statusCode;
    private ArrayList<String> headerData;
    private String bodyData;


    public HttpResponse(int statusCode, ArrayList<String> headerData, String bodyData) {
        this.statusCode = statusCode;
        this.headerData = headerData;
        this.bodyData = bodyData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ArrayList<String> getHeaderData() {
        return headerData;
    }

    public String getBodyData() {
        return bodyData;
    }
}
