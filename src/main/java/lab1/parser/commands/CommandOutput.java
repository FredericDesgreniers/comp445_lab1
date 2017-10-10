package lab1.parser.commands;

import lab1.http.HttpRequestInfo;

public class CommandOutput extends Command{

    String fileName = "";
    
    public CommandOutput()
    {
        acceptingArguments = true;
    }
    
    @Override
    public void processArgument(String argument) {
        fileName = argument;
        acceptingArguments = false;
    }

    @Override
    public void applyToRequestInfo(HttpRequestInfo requestInfo) {
        requestInfo.setOutputFilePath(fileName);
    }
}
