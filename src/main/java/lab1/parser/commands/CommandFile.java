package lab1.parser.commands;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import lab1.http.HttpRequestInfo;
import sun.misc.IOUtils;

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
        InputStream fileStream= this.getClass().getClassLoader().getResourceAsStream(name);

        BufferedReader fileStreamReader = new BufferedReader(new InputStreamReader(fileStream));
        String fileContents = fileStreamReader.lines().collect(Collectors.joining("\n"));
        
        requestInfo.setInlineData(fileContents);
    }
}
