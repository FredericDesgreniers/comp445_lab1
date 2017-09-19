package lab1.parser.commands;

import lab1.http.HttpRequestInfo;

public class CommandVerbose extends Command{
    
    @Override
    public void processArgument(String argument) {
        
    }

    @Override
    public void applyToRequestInfo(HttpRequestInfo requestInfo) {
        requestInfo.setVerbose(true);
    }

}
