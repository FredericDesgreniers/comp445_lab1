package lab1.parser.commands;

import lab1.http.HttpRequestInfo;

public class CommandData extends Command {

    String data;
    
    public CommandData(){
        acceptingArguments = true;
    }
    
    @Override
    public void processArgument(String argument) {
        data = argument;
        acceptingArguments = false;   
    }

    @Override
    public void applyToRequestInfo(HttpRequestInfo requestInfo) {
        requestInfo.setInlineData(data);
    }
}
