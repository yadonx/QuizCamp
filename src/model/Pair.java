package model;

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
        if (client1Exists()){
            client2 = client;
        } else if (client2Exists()){
            client1 = client;
        }
    }

    public boolean readyToPlay(){
        return client1Exists() && client2Exists();
    }

    public void removeClient1(){
        client1 = null;
    }

    public void removeClient2(){
        client2 = null;
    }

    private boolean client1Exists(){
        return client1 == null;
    }

    private boolean client2Exists(){
        return client2 == null;
    }

    public ObjectOutputStream getClient1() {
        return client1;
    }

    public ObjectOutputStream getClient2() {
        return client2;
    }

}
