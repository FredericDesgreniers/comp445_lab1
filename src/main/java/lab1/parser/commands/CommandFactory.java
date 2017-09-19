package lab1.parser.commands;


public class CommandFactory {
    
    public static Command getCommand(String command){
        switch(command){
            case "v": {
                return new CommandVerbose();
            }
            
            case "h": {
                return new CommandHeader();   
            }
            
            case "d": {
                return new CommandData();
            }
            
            case "f": {
                return new CommandFile();                
            }
        }
        
        return new CommandDefault();
    }
}
