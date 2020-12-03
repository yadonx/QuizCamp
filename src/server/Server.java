package server;


import model.Pair;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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
            game = pair.getGame();
            Object input;

            if (pair.readyToPlay()) {

                game.startGame();
            }
            while (true) {
                   input = in.readObject();
                   if (input instanceof String){
                       break;
                   }else {
                       game.checkInputObject(input);
                   }

            }
        } catch (SocketException e){
            if (e.getMessage().equals("Connection reset")) {
                System.out.println("Client disconnected");
                pair.whenClientDisconnect(out);
            }
            else e.printStackTrace();
        } catch (Exception e) {
            System.out.println("End of Thread: " + e.getMessage());
        }
        finally {
            pair.removeClient(out);
        }

    }
}
