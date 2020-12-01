package client.controller;


import client.view.QuizCampGUI;
import model.Category;
import model.CategoryProtocol;
import model.GameUpdater;
import model.Question;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
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
    private JTextPane questionText;
    private JTextPane categoryText;
    private JLabel playerLabel;
    private JLabel opponentLabel;
    private JLabel playerNameLabel;
    private JLabel opponentNameLabel;

    private ObjectOutputStream outputStream;
    private ObjectInputStream in;

    private Thread sendToServerThread;
    private Thread receiveFromServerThread;

    private CategoryProtocol protocol;

    private GameUpdater gameUpdater;
    private Question question;

    public static final int GAME_FINISHED = 1;
    public static final int CLIENT_DISCONNECTED = 2;


    public ClientHandler(QuizCampGUI gui) {
        this.gui = gui;
        this.gameButtons = gui.getGameButtons();
        this.buttonPanel = gui.getButtonPanel();
        this.startButton = gui.getStartButton();
        this.questionText = gui.getTextPane2();
        this.categoryText = gui.getTextPane();
        this.playerLabel = gui.getPlayerLabel();
        this.opponentLabel = gui.getOpponentLabel();
        this.opponentNameLabel = gui.getOpponentNameLabel();
        this.playerNameLabel = gui.getPlayerNameLabel();
    }

    public void startButton() {
        playerLabel.setText("0");
        opponentLabel.setText("0");
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

    private void waitForOtherPlayer() {
        buttonPanel.removeAll();
        categoryText.setText("");
        questionText.setText("Wait for opponent to answer questions...");
        buttonPanel.updateUI();
    }

    private void endTheGame(int gameState) {
        sendToServer("Game over");
        try {
            in.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            socketIsClosed(e);
        }
        buttonPanel.removeAll();
        categoryText.setText("");
        int opponentScore = gameUpdater.getOpponentScore();
        int clientScore = gameUpdater.getClientScore();
        if(gameState == CLIENT_DISCONNECTED){
            questionText.setText("Your opponent has left the game. YOU WIN!");

        }
        else if (clientScore > opponentScore) {
            questionText.setText("You win this game! Great job!");
        } else if (clientScore < opponentScore) {
            questionText.setText("Better luck next time...");
        } else {
            questionText.setText("Good job! you both where equally strong!");
        }


        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startButton.setText("Play again");
        buttonPanel.add(startButton);
        buttonPanel.updateUI();
    }

    private void setCategoryText() {
        categoryText.setText(protocol.getCategoryName());
    }

    public void updateQuestion() {

        question = protocol.getQuestion();
        if (question == null) {
            waitForOtherPlayer();
            sendToServer(gameUpdater);
            return;
        }
        List<String> answers = question.getShuffledAnswers();

        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i].setText(answers.get(i));
            gameButtons[i].setBackground(UIManager.getColor("JButton.background"));
            gameButtons[i].setBorder(UIManager.getBorder("Button.border"));
        }
        questionText.setText(question.questionText);

    }

    public void checkAnswer(String input) {
        if (input.equalsIgnoreCase(question.getCorrectAnswer())) {
            gameUpdater.increaseClientScore();
            playerLabel.setText("" + gameUpdater.getClientScore());
        }

        for (int i = 0; i < gameButtons.length; i++) {
            if (gameButtons[i].getText().equals(question.getCorrectAnswer()))
                gameButtons[i].setBackground(Color.green);
            else
                gameButtons[i].setBackground(Color.red);
        }


    }

    public void connectToServer() {
        connectSocket();
        if (socket != null)
            receiveFromServerThread = new Thread(() -> {

                try {
                    in = new ObjectInputStream(socket.getInputStream());
                    Object input;

                    while (true) {
                        input = in.readObject();
                        gameUpdater = (GameUpdater) input;

                        if (gameUpdater.getClientName() == null ){
                            gameUpdater.setClientName(playerNameLabel.getText());
                            sendToServer(gameUpdater);
                            break;
                        }

                    }

                    while (true) {
                        input = in.readObject();
                        System.out.println("Tar emot: " + input);

                        if (input instanceof GameUpdater) {
                            switchToGameButtons();
                            gameUpdater = (GameUpdater) input;
                            opponentNameLabel.setText(gameUpdater.getOpponentName());
                            opponentLabel.setText(String.valueOf(gameUpdater.getOpponentScore()));
                            if (gameUpdater.getCategory() == null) {
                                endTheGame(GAME_FINISHED);
                                return;
                            } else {
                                Category category = gameUpdater.getCategory();
                                protocol = new CategoryProtocol(category);
                                setCategoryText();
                                updateQuestion();
                            }
                        } else if (input instanceof String){
                            if (input.toString().equals("Disconnected")){
                                endTheGame(CLIENT_DISCONNECTED);
                            }
                        }

                    }

                } catch (IOException | ClassNotFoundException e) {
                    socketIsClosed(e);
                }
            });
        receiveFromServerThread.start();
    }

    private void connectSocket() {
        try {
            socket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void socketIsClosed(Exception exception){
        if (exception.getMessage().equals("Socket closed")){
            System.out.println("Disconnected");
        } else exception.printStackTrace();
    }

    private void sendToServer(Object output) {

            sendToServerThread = new Thread(() -> {
                try {
                        outputStream.writeObject(output);
                        System.out.println("Skickar: " + output.toString());
                } catch (IOException e) {
                    socketIsClosed(e);
                }
            });
        if (socket != null)
        sendToServerThread.start();
    }

}
