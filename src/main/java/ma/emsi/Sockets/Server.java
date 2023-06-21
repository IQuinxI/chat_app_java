package ma.emsi.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Waiting for a connection ...");
        
        Socket socket = serverSocket.accept();
        
        InputStream clientInput = socket.getInputStream();
        OutputStream serverOutput = socket.getOutputStream();

        System.out.println("Waiting for a number input ...");

        int number = clientInput.read();

        System.out.println("A number has been received");
        System.out.println("The result: " + number*10);

        serverOutput.write(number*10);

        socket.close();
        serverSocket.close();
    }
    


}
