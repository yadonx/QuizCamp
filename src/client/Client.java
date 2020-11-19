package client;

import client.controller.Controller;
import client.controller.LobbyController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.model.Game;


import java.io.*;
import java.net.Socket;

public class Client extends Application  {

    private int clientID;
    Controller controller;
    LobbyController lobbyController;
    private ClientConnection clientConnection;
    private Game game;
    private Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {

            this.stage = stage;
            Parent panel;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/lobby.fxml"));
            panel = loader.load();
            Scene scene = new Scene(panel);

            lobbyController = loader.getController();
            lobbyController.setClient(this);

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

    public void loadGameGUI (){

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/gui.fxml"));
                Parent panel = loader.load();
                Scene scene = new Scene(panel);

                controller = loader.getController();
                controller.setClient(this);

                stage.setScene(scene);

            }catch (IOException ex) {
                ex.printStackTrace();
            }
       });

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
                System.out.println("[CLIENT] ClientID " + clientID);

                /* TODO fix crash that happens when second player connects too fast */
                // receives initial Game object
                game = (Game) objectIn.readObject();

                // sets given player names in Game
                Platform.runLater(() -> {
                    if (clientID == 1) {
                        game.setPlayer1(lobbyController.getPlayerName());
                    } else if (clientID == 2) {
                        game.setPlayer2(lobbyController.getPlayerName());
                    }
                });

                // sends updated Game to server
                sendGame();

                // receives updated Game (particularly both players names)
                game = (Game) objectIn.readObject();

                loadGameGUI();

                // populates the gui with game data
                Platform.runLater(() -> {
                    stage.setTitle("Quiz Game - Player #" + clientID);
                    controller.setGame(game, clientID);
                    controller.populate();
                });
                System.out.println("[CLIENT] Game started. First question is displayed.");

                // send has been called when an answer tile was selected.
                // receives updated Game (eg new scores)
                game = (Game) objectIn.readObject();

                // displays correct answer and updates score in gui
                Platform.runLater(() -> {
                    controller.showCorrectAnswer();
                    controller.updateScore(game.getScore1(), game.getScore2());
                });
                System.out.println("[CLIENT] displaying correct answer and updating the score.");

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        private void sendGame() {
            try {
                objectOut.reset();
               if (clientID == 1) {
                   System.out.println("[CLIENT] Player #1 sent game data.");
                } else if (clientID == 2) {
                    System.out.println("[CLIENT] Player #2 sent game data.");
               }
                objectOut.writeObject(game);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}