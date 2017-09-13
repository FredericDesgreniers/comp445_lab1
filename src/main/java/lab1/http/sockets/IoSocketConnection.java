package lab1.http.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        output = new PrintWriter(socket.getOutputStream());
    }

    private void createInputReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void setOutputStream(Class<? extends PrintWriter> outputStreamClass) throws IoSocketException {
        try {
            output = getOutputStreamExpectedClassConstructor(outputStreamClass).newInstance(socket.getOutputStream());
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not set output stream due to reflection error");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not set output stream due to reflection error");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not set output stream due to reflection error");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not set output stream due to socket IO exception");
        }
    }
    
    private Constructor<? extends PrintWriter> getOutputStreamExpectedClassConstructor(Class<? extends PrintWriter> outputStreamClass)
        throws IoSocketException {
        Class<?> expectedConstructorArgumentForPrintWriter = OutputStream.class;
        try {
            return outputStreamClass.getDeclaredConstructor(expectedConstructorArgumentForPrintWriter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IoSocketException("Invalid output stream class type given");
        }
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
