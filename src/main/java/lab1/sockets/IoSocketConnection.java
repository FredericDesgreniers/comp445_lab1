package lab1.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IoSocketConnection {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    
    public IoSocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        createOutputStream();
        createInputReader();
    }
    
    private void createOutputStream() throws IOException {
        output = new PrintWriter(socket.getOutputStream(), false);
    }

    private void createInputReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void sendLineToBuffer(String line){
        output.print(line+"\r\n");
        System.out.println(line);
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
    
}
