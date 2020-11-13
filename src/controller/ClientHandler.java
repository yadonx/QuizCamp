package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:48
 * Project: QuizCamp
 * Package: PACKAGE_NAME
 */
public class ClientHandler {
    private int port = 12345;
    private String ip = "127.0.0.1";
    private Socket socket;

    private String username;
    private String opponentsName;

    private ObjectOutputStream outputStream;

    private Thread sendToServerThread;
    private Thread receiveFromServerThread;

    public void connectToServer() {
        connectSocket();
        if (socket != null)
            receiveFromServerThread = new Thread(() -> {

                try {
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Object input;

                    while (true) {
                        input = in.readObject();
                        System.out.println("Tar emot: " + input);
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        receiveFromServerThread.start();
    }

    private void connectSocket() {
        try {
            socket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendToServer(Object output) {
        if (socket != null)
            sendToServerThread = new Thread(() -> {
                try {
                    outputStream.writeObject(output);
                    System.out.println("Skickar: " + output.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        sendToServerThread.start();
    }


    public static void main(String[] args) {
        ClientHandler ch = new ClientHandler();
        ch.connectToServer();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            ch.sendToServer(s);
        }
    }
}
