package lab1.parser.commands;


import lab1.http.HttpRequestInfo;

public class CommandFile extends Command {

    String name;
    
    public CommandFile(){
        acceptingArguments = true;
    }
    
    @Override
    public void processArgument(String argument) {
        name = argument;
        acceptingArguments = false;
    }

    @Override
    public void applyToRequestInfo(HttpRequestInfo requestInfo) {
        
    }
}
