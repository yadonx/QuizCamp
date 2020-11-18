package controller;

import model.Answer;
import model.Question;
import model.QuestionOfCategory;
import model.Result;


import view.QuizCampGUI;
import model.*;

import javax.swing.*;
import java.awt.*;
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
 */
public class ClientHandler {
    private int port = 12345;
    private String ip = "127.0.0.1";
    private Socket socket;

    private QuizCampGUI gui;

    private JPanel buttonPanel;
    private JButton[] gameButtons;
    private JButton startButton;
    private JTextPane textPane;
    private JLabel playerLabel;
    private JLabel opponentLabel;

    private String username;
    private String opponentsName;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler() throws IOException { // no recovery from exception so better if program crushes
        this.socket = new Socket(ip, port);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    ClientHandler() {} // temp

    public ClientHandler(QuizCampGUI gui) {
        this.gui = gui;
        this.gameButtons = gui.getGameButtons();
        this.buttonPanel = gui.getButtonPanel();
        this.startButton = gui.getStartButton();
        this.textPane = gui.getTextPane();
        this.playerLabel = gui.getPlayerLabel();
        this.opponentLabel = gui.getOpponentLabel();
    }

    public void startButton() {
        buttonPanel.removeAll();
        buttonPanel.add(new JLabel("Searching for opponent..."));
        buttonPanel.updateUI();
    }

    private void switchToGameButtons() {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(2, 2));
        for (JButton button : gameButtons)
            buttonPanel.add(button);
        buttonPanel.updateUI();
    }

    public void connectToServer() {
        connectSocket();
        if (socket != null)
            receiveFromServerThread = new Thread(() -> {

                try {
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Object input;

                    while (true) {
                        input = in.readObject();
                        System.out.println("Tar emot: " + input);

                        // tillfällig lösning för att testa.
                        if (input instanceof String) {
                            if (input.equals("paired"))
                                switchToGameButtons();
                        }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        receiveFromServerThread.start();
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
    private void sendToServer(Object output) {
        if (socket != null)
            sendToServerThread = new Thread(() -> {
                try {
                    outputStream.writeObject(output);
                    System.out.println("Skickar: " + output.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        sendToServerThread.start();
    }

    public static void main(String[] args) throws IOException {//no recovery from exception so program crush
        ClientHandler ch = new ClientHandler();
    }
}
