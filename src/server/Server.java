package server;

import server.model.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newFixedThreadPool(1000);
    private LinkedList<GameServer> gameServers = new LinkedList<>();

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {

            serverSocket = new ServerSocket(PORT);

            gameServers.add(new GameServer());


            System.out.println("[SERVER] Waiting for connections...");
            // loop, waiting for incoming connections and distributes them to a game server.
            // creates a new game server if the most recent one is full.
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("[SERVER] Client connected. Awaiting allocation...");
                if (gameServers.getLast().clients.size() >= 2) {
                    gameServers.add(new GameServer());
                }
                gameServers.getLast().acceptConnections(socket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class GameServer {
        private ArrayList<ClientHandler> clients = new ArrayList<>();
        private Game game = new Game();

        void acceptConnections(Socket socket) {
            ClientHandler clientHandler = new ClientHandler(socket, (clients.size() + 1));
            clients.add(clientHandler);
            executorService.execute(clientHandler);
            game.setServerName("#" + gameServers.indexOf(gameServers.getLast()));
            System.out.println("[GAME SERVER " + game.getServerName() + "] Player #" + clients.size() + " has connected.");
            if (clients.size() == 2) {
                System.out.println("[GAME SERVER " + game.getServerName() + "] 2/2 players. Launching game.");
            }
        }

        private class ClientHandler implements Runnable {

            private Socket socket;
            private ObjectOutputStream objectOut;
            private ObjectInputStream objectIn;
            private DataOutputStream dataOut;

            private int clientID;

            public ClientHandler(Socket socket, int clientID) {
                this.socket = socket;
                this.clientID = clientID;
            }

            @Override
            public void run() {
                try {
                    objectOut = new ObjectOutputStream(socket.getOutputStream());
                    dataOut = new DataOutputStream((socket.getOutputStream()));
                    objectIn = new ObjectInputStream(socket.getInputStream());

                    // send client ID
                    dataOut.writeInt(clientID);
                    dataOut.flush();

                    // waiting for two players to connect
                    while (clients.size() < 2) { }

                    // send Game
                    for (ClientHandler c : clients) {
                        c.objectOut.reset();
                        c.objectOut.writeObject(game);
                    }

                    // receive player names from clients
                    game.setPlayer1(((Game) clients.get(0).objectIn.readObject()).getPlayer1());
                    System.out.println("[GAME SERVER " + game.getServerName() + "] Player 1 name is " + game.getPlayer1());

                    game.setPlayer2(((Game) clients.get(1).objectIn.readObject()).getPlayer2());
                    System.out.println("[GAME SERVER " + game.getServerName() + "] Player 2 name is " + game.getPlayer2());

                    // send updated Game
                    for (ClientHandler c : clients) {
                        c.objectOut.reset();
                        c.objectOut.writeObject(game);
                    }

                    // receive selected answers from clients
                    for (ClientHandler c : clients) {
                        if (c.clientID == 1) {
                            game.setSelected1(((Game) clients.get(0).objectIn.readObject()).getSelected1());
                            System.out.println("[GAME SERVER " + game.getServerName() + "] Player 1 picked " + game.getSelected1());
                        } else if (c.clientID == 2) {
                            game.setSelected2(((Game) clients.get(1).objectIn.readObject()).getSelected2());
                            System.out.println("[GAME SERVER " + game.getServerName() + "] Player 2 picked " + game.getSelected2());
                        }
                    }

                    game.gradeAnswers();
                    System.out.println("[GAME SERVER " + game.getServerName() + "] The answers from the players are graded.");

                    // send updated Game
                    for (ClientHandler c : clients) {
                        c.objectOut.reset();
                        c.objectOut.writeObject(game);
                    }
                    System.out.println("[GAME SERVER " + game.getServerName() + "] Points are set.");

                } catch (IOException | NullPointerException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}