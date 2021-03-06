package model;

import server.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-20 <br>
 * Time: 10:03 <br>
 * Project: IntelliJ IDEA <br>
 */
public class Pair {
    private ObjectOutputStream client1;
    private ObjectOutputStream client2;
    private Game game = new Game(this);

    public Pair() {
    }

    public void addClient(ObjectOutputStream client) {
        if (!client1Exists()) {
            client1 = client;
        } else if (!client2Exists()) {
            client2 = client;
        } else {
            System.out.println("Pair is full.");
        }
    }

    public boolean readyToPlay() {
        return client1Exists() && client2Exists();
    }

    public void removeClient(ObjectOutputStream out) {
        if (client1 == out) {
            client1 = null;
        } else {
            client2 = null;
        }
    }

    public boolean client1Exists() {
        return client1 != null;
    }

    public boolean client2Exists() {
        return client2 != null;
    }

    public void whenClientDisconnect(ObjectOutputStream out) {
        try {
            if (out == client1 && client2Exists()) {
                client2.writeObject("Disconnected");
            } else if (out == client2 && client1Exists()) {
                client1.writeObject("Disconnected");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToClients(Object output) {
        try {
            client1.writeObject(output);
            client2.writeObject(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGameUpdaters(Object output1, Object output2) {
        try {
            System.out.println("[SERVER] send to client1: " + output1.toString());
            client1.writeObject(output1);
            client1.reset();

            System.out.println("[SERVER] send to client2: " + output2.toString());
            client2.writeObject(output2);
            client2.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Game getGame() {
        return game;
    }
}
