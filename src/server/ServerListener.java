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
        List<Server> playersQueue = new ArrayList<>();
        System.out.println("Server is listening!");
        while (true) {

            try {//when we accept the socket we creeate server, put them in a queue
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                playersQueue.add(server);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (playersQueue.size() == 2) {//when there are two players we can start, but need to check if both of them are "ready"
                Server server1 = playersQueue.get(0);//if server 1 is not ready we remove it from the queue and try again the loop
                Server server2 = playersQueue.get(1);
                if (!server1.isReady()) {
                    playersQueue.remove(server1);
                    continue;
                }
                if (!server2.isReady()) {
                    playersQueue.remove(server2);//if none of if conditions happen we are starting the game and  empty the queue
                    continue;
                }
                server1.sendPaired();
                server2.sendPaired();
                Game game = new Game(server1, server2);
                game.start();
                playersQueue.clear();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerListener();
    }
}
