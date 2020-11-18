package client;

import client.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;


import java.io.*;
import java.net.Socket;

public class Client extends Application  {

    private int clientID;
    Controller controller;
    private ClientConnection clientConnection;
    private Game game;
    private Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {

            connect();

            this.stage = stage;
            Parent panel;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/gui.fxml"));
            panel = loader.load();
            Scene scene = new Scene(panel);

            controller = loader.getController();
            controller.setClient(this);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        clientConnection = new ClientConnection();
    }

    public void sendGame() {
        clientConnection.sendGame();
    }

    private class ClientConnection implements Runnable {
        private Socket socket;
        private ObjectInputStream objectIn;
        private ObjectOutputStream objectOut;
        private DataInputStream dataIn;

        public ClientConnection () {
            Thread thread = new Thread(this);
            thread.start();
        }
        @Override
        public void run() {
            try {
                socket = new Socket("localhost", 12345);
                objectIn = new ObjectInputStream(socket.getInputStream());
                objectOut = new ObjectOutputStream(socket.getOutputStream());
                dataIn = new DataInputStream(socket.getInputStream());

                clientID = dataIn.readInt();

                /* TODO fix crash that happens when second player connects too fast */
                game = (Game) objectIn.readObject();

                Platform.runLater(() -> {
                    stage.setTitle("Quiz Game - Player #" + clientID);
                    controller.setGame(game, clientID);
                    controller.populate();
                });
                System.out.println("[CLIENT] Game started. First question is displayed.");

                game = (Game) objectIn.readObject();

                Platform.runLater(() -> {
                    controller.showCorrectAnswer();
                    controller.updateScore(game.getScore1(), game.getScore2());
                });
                System.out.println("[CLIENT] displaying correct answer and updating the score.");

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        public void sendGame() {
            try {
                objectOut.reset();
                if (clientID == 1) {
                    System.out.println("[CLIENT] Player #1 sent game data.");
                    objectOut.writeObject(game);
                    objectOut.flush();
                } else if (clientID == 2) {
                    System.out.println("[CLIENT] Player #2 sent game data.");
                    objectOut.writeObject(game);
                    objectOut.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}