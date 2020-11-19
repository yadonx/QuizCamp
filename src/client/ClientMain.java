package client;

import view.QuizCampGUI;

public class ClientMain {
    public static void main(String[] args) {
        QuizCampGUI gui = new QuizCampGUI();
        ClientHandler clientHandler = new ClientHandler(gui);
    }
}
