package ma.emsi.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadSevrver extends Thread {

    private List<Communication> connectedClients = new ArrayList<>();

    public static void main(String[] args) {
        new MultiThreadSevrver().start();
        ;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Waiting for a connection ...");
            while (true) {
                Socket socket = serverSocket.accept();
                
                Communication communication = new Communication(socket);
                connectedClients.add(communication);

                communication.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Communication
     */
    public class Communication extends Thread {
        private Socket socket;

        public Communication(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is); // collects bites until it has a character
                BufferedReader clientResponse = new BufferedReader(isr); // collects a string of characters

                OutputStream serverResponse = socket.getOutputStream();

                while(true) {
                    String request  = clientResponse.readLine();
                    diffuse(request, socket);
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void diffuse(String request, Socket socket) {
            for(Communication client : connectedClients) {
                try {
                    PrintWriter pw = new PrintWriter(client.socket.getOutputStream(), true);
                    if(client.socket != socket)
                        pw.println(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
