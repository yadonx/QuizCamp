package client;

import model.Answer;
import model.QuestionOfCategory;
import view.QuizCampGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:48
 * Project: QuizCamp
 */
public class ClientHandler {

    private JPanel buttonPanel;
    private JButton[] gameButtons;
    private JButton startButton;
    private JTextPane textPane;
    private JLabel playerLabel;
    private JLabel opponentLabel;
    private Client client;

    ClientHandler() {
    } // temp

    public ClientHandler(QuizCampGUI gui) {
        this.gameButtons = gui.getGameButtons();
        this.buttonPanel = gui.getButtonPanel();
        this.startButton = gui.getStartButton();
        this.textPane = gui.getTextPane();
        this.playerLabel = gui.getPlayerLabel();
        this.opponentLabel = gui.getOpponentLabel();

        startButton.addActionListener(e -> {//*doesn't connect to server until play is clicked
            startButton();
            connectToServer();
        });

        ActionListener gameButtonListener = e -> {
            System.out.println(e.getActionCommand());
            client.sendAnswer(new Answer(e.getActionCommand()));//calling method sendAnswer when button is clicked we SEND it to server/game as answer
        };
        for (JButton e : gameButtons)
            e.addActionListener(gameButtonListener);
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
        //create client
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (client != null && client.isPaired()) {//to check if clients are connected n able to receive stuff
            QuestionOfCategory category = client.receiveQuestion();
            for (int i = 0; i < gameButtons.length; i++) {
                gameButtons[i].setText(category.getCategories().get(i));
            }//76-79 temporarily code
            switchToGameButtons();
        }
        //TODO: something went wrong

    }
}
