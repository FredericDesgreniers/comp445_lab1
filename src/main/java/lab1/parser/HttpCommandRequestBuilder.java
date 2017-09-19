package lab1.parser;

import java.util.ArrayList;
import java.util.List;
import lab1.http.HttpRequestInfo;
import lab1.http.HttpRequestType;
import lab1.parser.commands.Command;

public class HttpCommandRequestBuilder {
    
    String url = "";
    
    private List<Command> commands;
    
    private HttpRequestType type = HttpRequestType.GET;
    
    public HttpCommandRequestBuilder(){
        commands = new ArrayList<>();        
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    public void addCommand(Command command){
        commands.add(command);
    }
    
    public void setType(HttpRequestType type){
        this.type = type;
    }
    
    public HttpRequestInfo buildRequestInfo(){
        HttpRequestInfo requestInfo = new HttpRequestInfo(type, url);
        
        commands.forEach(c->c.applyToRequestInfo(requestInfo));
        
        return requestInfo;
    }
}
