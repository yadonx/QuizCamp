package server;


import model.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:17
 * Project: QuizCamp
 * Package: server
 */

public class Server extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Game game;

    private Pair pair;

    public Server(Socket socket, Pair pair) {
        this.socket = socket;
        this.pair = pair;
    }

    public void run() {

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            pair.addClient(out);
//            game = new Game(pair);
            Object input = null;

            // tillfällig lösning för att testa.
            if (pair.readyToPlay()) {
                pair.writeToClients("paired");
            }
            while (true) {
                   input = in.readObject();

                System.out.println(input);

                int test = 1;
                pair.writeToClients(input);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            if (e.getMessage().equals("Connection reset")) {
                pair.removeClient(out);
            }
        }
//        finally {
//            counter--;
//            pairList.remove(pair);
//        }
    }
}
