package ma.emsi.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import ma.emsi.Managers.ChatManager;
import ma.emsi.StateManagement.Session;

public class UserClient {
    private static String SERVER_ADDRESS = "127.0.0.1";
    private static int PORT = 8080;

    private Socket socket;
    private InputStream inputStream;
    private InputStreamReader iStreamReader;
    private BufferedReader serverOutput;
    private OutputStream clientOutput;
    private PrintWriter pw;

    public UserClient(ChatManager chatManager) {
        initialize();
        startThread(chatManager);
    }

    public void initialize() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            inputStream = socket.getInputStream();

            iStreamReader = new InputStreamReader(inputStream);
            serverOutput = new BufferedReader(iStreamReader);

            clientOutput = socket.getOutputStream();
            pw = new PrintWriter(clientOutput, true);

            // sends an init i aliaztaion message to the Server
            pw.println("-1:" + Session.getCurrentUser().getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThread(ChatManager chatManager) {
        Thread serverRespoThread = new Thread(() -> {
            try {
                // Listens to any incoming traffic from the server
                while (true) {
                    String response = serverOutput.readLine();
                    if (response != null) {
                        Platform.runLater(() -> {
                            if(isMessage(response)) chatManager.updateChat(response.split(":", 2)[1]);
                        });
                        System.out.println(response);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverRespoThread.start();
    }

    public void sendMessage(String request) {
        pw.println(request);
    }

    private boolean isMessage(String message) {
        String[] data = message.split(":", 2);
        if(data[0].equals("MSG")) return true;
        return false;
    }
    // public void close() {
    // try {
    // socket.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

}
