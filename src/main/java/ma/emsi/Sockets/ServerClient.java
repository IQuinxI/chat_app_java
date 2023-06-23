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
                // listens to any incoming requests and forwards them to the handleRequest class
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
            // when the request is tagged with a -1, that's an intialization request and it
            // sets the userId for the communication class
            if (recipientUserId == -1) {
                setUserId(Integer.parseInt(message));
                System.out.println("Initialized User successfully: " + getUserId());
            } else {
                // when the request is tagged with another number, it points towards the recepient
                // the recepient id is compared inside the list, if found a message is sent, if not 
                // an error message is sent back to the sender
                for (Communication client : connectedClients) {
                    if (client.getUserId() == recipientUserId) {
                        try {
                            PrintWriter recipientPw = new PrintWriter(client.socket.getOutputStream(), true);
                            recipientPw.println("MSG:"+message);
                            pw.println("Message sent successfully");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
                pw.println("NOT_FOUND:Recipient user not found");
            }

        }

    }
}
