package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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


    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run(){

        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Object input = in.readObject();
            System.out.println(input + "Connected");

            out.writeObject("HEJ");

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
