package lab1.parser.commands;


import lab1.http.HttpRequestInfo;

public abstract class Command {
    boolean acceptingArguments = false;
    
    public final boolean needsArgument(String argument){
        if(acceptingArguments){
            processArgument(argument);
            return true;
        }
        return false;
    }
    
    public abstract void processArgument(String argument);
    
    public abstract void applyToRequestInfo(HttpRequestInfo requestInfo);
}
