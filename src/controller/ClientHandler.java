package controller;

import model.Answer;
import model.Question;
import model.QuestionOfCategory;
import model.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:48
 * Project: QuizCamp
 * Package: PACKAGE_NAME
 */
public class ClientHandler {
    private int port = 12345;
    private String ip = "127.0.0.1";
    private Socket socket;

    private String username;
    private String opponentsName;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler() throws IOException { // no recovery from exception so better if program crushes
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

    public static void main(String[] args) throws IOException {//no recovery from exception so program crush
        ClientHandler ch = new ClientHandler();
    }
}
