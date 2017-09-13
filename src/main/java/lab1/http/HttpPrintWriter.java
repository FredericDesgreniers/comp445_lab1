package lab1.http;

import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpPrintWriter extends PrintWriter{
    
    public HttpPrintWriter(OutputStream out) {
        super(out);
    }

    public void println(String line) {
        print(addHttpEndLine(line));
    }
    
    public String addHttpEndLine(String line){
        return line + "\r\n";
    }
}
