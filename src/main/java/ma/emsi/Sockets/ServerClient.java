package ma.emsi.Sockets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerClient extends Thread {
    private List<Communication> connectedClients = new ArrayList<>();
    private static int PORT = 8080;
    

    public static void main(String[] args) {
        new ServerClient().start();
    }

    @Override
    public void run() {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Waiting for a new user connection ...");
            while (true) {
                socket = serverSocket.accept();

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
        private int userId;

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public Communication(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader clientResponse = new BufferedReader(isr);

                OutputStream serverResponse = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(serverResponse, true);

                while (true) {
                    String request = clientResponse.readLine();
                    if (request != null) {
                        System.out.println("The client sent the string: " + request);
                        handleRequest(request, pw);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleRequest(String request, PrintWriter pw) {
            String[] data = request.split(":", 2);
            int recipientUserId = Integer.parseInt(data[0]);
            String message = data[1];
            if (recipientUserId == -1) {
                setUserId(Integer.parseInt(message));
                System.out.println("Initialized User successfully: "+getUserId());
            } else {
                for (Communication client : connectedClients) {
                    if (client.getUserId() == recipientUserId) {
                        try {
                            PrintWriter recipientPw = new PrintWriter(client.socket.getOutputStream(), true);
                            recipientPw.println(message);
                            pw.println("Message sent successfully");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
                pw.println("Recipient user not found");
            }

        }

        
    }
}
