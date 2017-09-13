package lab1.sockets;

import java.io.IOException;

public class IoSocketException extends IOException {
    public IoSocketException(String message){
        super(message);
    }
}
