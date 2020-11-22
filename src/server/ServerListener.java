package server;

import model.Pair;

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
    private List<Pair> pairList = new ArrayList<>();

    public ServerListener() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try {
                if (clients == 1){
                    pairList.add(new Pair());
                }

                final Socket socket = serverSocket.accept();
                Server clientSocket = new Server(socket, pairList.get(pairList.size()-1));
                if (clients == 2) {
                    if(pairList.get(pairList.size()-1).client1Exists()) {
                        pairList.remove(pairList.size() - 1);
                        clients = 0;
                    } else {
                        clients = 1;
                    }
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
