package lab1.parser;

import lab1.http.HttpRequestType;
import lab1.parser.commands.Command;
import lab1.parser.commands.CommandDefault;
import lab1.parser.commands.CommandFactory;

public class ParamParser {
    
    private Command currentCommand;
    
    private HttpCommandRequestBuilder commandRequestBuilder;
    
    public ParamParser(HttpCommandRequestBuilder commandRequestBuilder){
        this.commandRequestBuilder = commandRequestBuilder;
        currentCommand = new CommandDefault();
    }
    
    public void parse(String str){
        
        String[] params = StringToParamUtil.getPramsFromString(str);
        
        for(int i=0; i < params.length; i++){
                 parseParam(params[i]);       
        }
    }
    
    private void parseParam(String param){
        
        if(checkForCommand(param)){
            return;
        }
        
        if(currentCommand.needsArgument(param)){
            return;
        }
        
        if(checkForRequestType(param)){
            return;
        }
        
        commandRequestBuilder.setUrl(param);
        
    }
    
    private boolean checkForCommand(String str){
        if(isCommand(str)){
            getAndAddCommand(str.substring(1));
            return true;
        }
        
        return false;
    }
    
    private void getAndAddCommand(String command){
        currentCommand = CommandFactory.getCommand(command);
        commandRequestBuilder.addCommand(currentCommand);
    }
    
    private boolean isCommand(String str){
        return str.toCharArray()[0] == '-';
    }
    
    private boolean checkForRequestType(String param) {
        try {
            HttpRequestType type = HttpRequestType.valueOf(param.toUpperCase());
            commandRequestBuilder.setType(type);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
