package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:13
 * Project: QuizCamp
 */
public class ServerListener {
    private int port = 12345;


    public ServerListener() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                Server clientSocket = new Server(socket);
                clientSocket.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerListener();
    }
}
