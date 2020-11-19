package client;

import model.Answer;
import model.Question;
import model.QuestionOfCategory;
import model.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final int port = 12345;
    private final String ip = "127.0.0.1";

    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Client() throws IOException { // no recovery from exception so better if program crushes
        this.socket = new Socket(ip, port);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public QuestionOfCategory receiveQuestion() {//GUI can call n receive question from server via client
        try {
            return (QuestionOfCategory) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendAnswer(Answer clientAnswer) {//GUI can send answer to Server
        try {
            outputStream.writeObject(clientAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question receiveRoundQuestion() {
        try {
            return (Question) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Result receiveResult() {
        try {
            return (Result) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isPaired() {//server checks if client can send n receive stuff, is active n socket is working
        try {                   //happens when server listener is going to match client to someone
            while (true) {
                Object input = inputStream.readObject();
                if (input instanceof String) {
                    if (input.equals("ready?")) {
                        outputStream.writeObject("Yes!");
                    } else if (input.equals("paired")) {
                        return true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
