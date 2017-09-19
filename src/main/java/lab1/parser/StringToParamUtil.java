package lab1.parser;

import java.util.ArrayList;
import java.util.List;

public class StringToParamUtil {
    
    List<String> params;
    
    boolean inString = false;
    char stringEnterChar;
    
    String currentParam;
    
    boolean ignoreNextCharIfSpecial = false;
    
    char currentChar = ' ';
    
    public static String[] getPramsFromString(String s){
        return new StringToParamUtil().parse(s);
    }
    
    private StringToParamUtil(){
        
    }
    
    public String[] parse(String s){
        params = new ArrayList<>();
        currentParam = "";
        
        for(char c : s.toCharArray()){
            processChar(c);    
        }
        
        if(currentParam.length() > 0){
            params.add(currentParam);
        }
        
        return params.toArray(new String[]{});
    }
    
    private void processChar(char c){
        
        currentChar = c;
        
        if(checkForSpecialCharIgnore()){
            return;
        }
        
        if(checkForString()){
            return;
        }
        
        if(checkForBreak()){
            return;
        }
        
        
        
        currentParam += currentChar;
    }
    
    private boolean checkForSpecialCharIgnore(){
        if(ignoreNextCharIfSpecial){
            currentParam += currentChar;
            ignoreNextCharIfSpecial = false;
            return true;
        }
        
        return false;
    }
    
    private boolean checkForString(){
        
        if(checkForInString()){
            return true;
        }

        if(checkStringStart()){
            return true;
        }
        
        return false;
    }
    
    private boolean checkForInString(){
        if(inString){
            if(currentChar == stringEnterChar){
                inString = false;
            }else {
                currentParam += currentChar;
            }

            return true;
        }
        return false;
    }
    
    private boolean checkStringStart(){
        if(currentChar == '\'' || currentChar == '"'){
            inString = true;
            stringEnterChar = currentChar;
            return true;
        }
        
        return false;
    }
    
    private boolean checkForBreak(){
        if(currentChar == ' '){
            params.add(currentParam);
            currentParam=  "";
            return true;
        }
        
        return false;
    }
}
