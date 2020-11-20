package model;

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

    public Pair(){}

    public void addClient(ObjectOutputStream client){
        if (client1Exists() && client2Exists()){
            System.out.println("Pair is full.");
            return;
        }

        if (!client1Exists() && !client2Exists()){
            client1 = client;
            System.out.println("första");
        } else if (client1Exists()){
            client2 = client;
            System.out.println("andra");
        } else if (client2Exists()){
            client1 = client;
            System.out.println("första");
        }
    }

    public boolean readyToPlay(){
        return client1Exists() && client2Exists();
    }

    public void removeClient(ObjectOutputStream out){
        if (client1 == out) {
            client1 = null;
        } else {
            client2 = null;
        }
    }


    private boolean client1Exists(){
        return client1 != null;
    }

    private boolean client2Exists(){
        return client2 != null;
    }

    public ObjectOutputStream getClient1() {
        return client1;
    }

    public ObjectOutputStream getClient2() {
        return client2;
    }

    public void writeToClients(Object output){
        try {
            client1.writeObject(output);
            client2.writeObject(output);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
