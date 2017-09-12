package lab1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IoSocket {
    private String host;
    
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public IoSocket(String host){
        this.host = host;
    }
    
    public void connect() throws IoSocketException {
        try {
            tryConnect();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not connect on socket");
        }
    }

    private void tryConnect() throws IOException {
        socket = new Socket(host, 80);
        createInputReader();
        createOutputStream();
    }

    private void createOutputStream() throws IOException {
        output = new PrintWriter(socket.getOutputStream(), false);
    }

    private void createInputReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void sendLineToBuffer(String line){
        output.print(line+"\r\n");
    }
    
    public void sendBufferToHost(){
        output.flush();
    }
    
    public String getLine() throws IoSocketException {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not read line");
        }
    }
    
    public PrintWriter getOutputStream(){
        return output;
    }
    
    public BufferedReader getInputReader(){
        return input;
    }

}
