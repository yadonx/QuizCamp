package server;


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

    private List<ObjectOutputStream> pair;

    public Server(Socket socket, List<ObjectOutputStream> pair) {
        this.socket = socket;
        this.pair = pair;
    }

    public void run() {

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            pair.add(out);
//            game = new Game(pair);
            Object input = null;

            // tillfällig lösning för att testa.
            if (pair.size() == 2)
                for (ObjectOutputStream s : pair)
                    s.writeObject("paired");
            while (true) {
                   input = in.readObject();

                System.out.println(input);

//                int test = 1;
//                for (ObjectOutputStream stream : pair)
//                    stream.writeObject(input + " " + test++);

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            if (e.getMessage().equals("Connection reset"))
            pair.remove(out);
        }
//        finally {
//            counter--;
//            pairList.remove(pair);
//        }
    }
}
