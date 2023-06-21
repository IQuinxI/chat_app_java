package ma.emsi.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to your client terminal");

        Socket socket = new Socket("127.0.0.1", 8080);

        InputStream inputStream = socket.getInputStream();
        InputStreamReader iStreamReader = new InputStreamReader(inputStream);
        BufferedReader serverOutput = new BufferedReader(iStreamReader);

        OutputStream clientOutput = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(clientOutput, true);

        Thread serverRespoThread = new Thread(() -> {
            try {
                String response;
                while ((response = serverOutput.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverRespoThread.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write a nice message: ");
            String message = scanner.nextLine();

            if(message.equals("exit")) break;


            pw.println(message);

            String response = serverOutput.readLine();

            System.out.println(response);
        }

        socket.close();
        scanner.close();
    }

}
