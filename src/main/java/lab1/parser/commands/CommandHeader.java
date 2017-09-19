package lab1.parser.commands;


import lab1.http.HttpRequestInfo;

public class CommandHeader extends Command {
    
    private String header;
    
    public CommandHeader(){
        acceptingArguments = true;
    }
    
    @Override
    public void processArgument(String argument) {
        header = argument;
        acceptingArguments = false;
    }

    @Override
    public void applyToRequestInfo(HttpRequestInfo requestInfo) {
        requestInfo.addHeader(header);
    }
}
