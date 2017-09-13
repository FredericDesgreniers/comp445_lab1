package lab1.http.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import lab1.http.HttpPrintWriter;

public class IoSocketConnection {
    private Socket socket;
    private HttpPrintWriter output;
    private BufferedReader input;
    
    public IoSocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        createOutputStream();
        createInputReader();
    }
    
    private void createOutputStream() throws IOException {
        output = new HttpPrintWriter(socket.getOutputStream());
    }

    private void createInputReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void sendLineToBuffer(String line){
        output.println(line);
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
