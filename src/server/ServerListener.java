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
    private int port = 12345;
    private int clients = 1;
    private List<List> pairList = new ArrayList<>();

    public ServerListener() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try {
                if (clients == 1){
                    pairList.add(new ArrayList());
                }

                final Socket socket = serverSocket.accept();
                Server clientSocket = new Server(socket, pairList.get(pairList.size()-1));
                if (clients == 2) {
                    pairList.remove(pairList.size()-1);
                    clients = 0;
                }
                clients++;
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
