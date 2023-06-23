package ma.emsi.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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

    public UserClient() {
        initialize();
        startThread();
    }

    public void initialize() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            inputStream = socket.getInputStream();

            iStreamReader = new InputStreamReader(inputStream);
            serverOutput = new BufferedReader(iStreamReader);

            clientOutput = socket.getOutputStream();
            pw = new PrintWriter(clientOutput, true);

            pw.println("-1:"+Session.getCurrentUser().getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThread() {
        Thread serverRespoThread = new Thread(() -> {
            try {
                while (true) {
                    String response = serverOutput.readLine();
                    if (response != null)
                        System.out.println(response);
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

    // public void close() {
    // try {
    // socket.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

}
