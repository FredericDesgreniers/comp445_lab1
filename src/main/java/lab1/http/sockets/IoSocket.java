package lab1.http.sockets;

import java.io.IOException;
import java.net.Socket;

public class IoSocket {
    private final String host;
    private final int port;

    public IoSocket(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    public IoSocketConnection connect() throws IoSocketException {
        try {
            return tryConnect();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IoSocketException("Could not connect on socket");
        }
    }

    private IoSocketConnection tryConnect() throws IOException {
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(2000);
        return new IoSocketConnection(socket);
    }
}
