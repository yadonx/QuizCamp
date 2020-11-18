package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:13
 * Project: QuizCamp
 */
public class ServerListener {
    private final int port = 12345;

    public ServerListener() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try {
                final Socket socket1 = serverSocket.accept();
                final Socket socket2 = serverSocket.accept();
                Server server1 = new Server(socket1);
                Server server2 = new Server(socket2);
                Game game = new Game(server1, server2);
                game.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerListener();
    }
}
