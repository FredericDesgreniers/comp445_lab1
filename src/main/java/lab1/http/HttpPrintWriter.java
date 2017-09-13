package lab1.http;

import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpPrintWriter{

    private PrintWriter normalPrintWriter;
    
    public HttpPrintWriter(OutputStream out) {
        normalPrintWriter = new PrintWriter(out, false);
    }

    public void println(String line) {
        normalPrintWriter.print(addHttpEndLine(line));
    }
    
    public String addHttpEndLine(String line){
        return line + "\r\n";
    }
    
    public void flush(){
        normalPrintWriter.flush();
    }
}
