package client.view;

import client.controller.ClientHandler;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-12 <br>
 * Time: 14:29 <br>
 * Project: QuizCamp <br>
 */
public class QuizCampGUI extends JFrame {

    private JButton[] gameButtons = new JButton[]{new JButton("k1"),new JButton("k2"),
            new JButton("k3"), new JButton("k4")};

    private JButton startButton = new JButton("Play");

    private JPanel topPanel = new JPanel();
    private JPanel questionPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel scorePanel1 = new JPanel();
    private JPanel scorePanel2 = new JPanel();

    private final JTextPane textPane = new JTextPane();
    private final JTextPane textPane2 = new JTextPane();

    private JLabel scoreLabel = new JLabel("Score");
    private JLabel playerLabel = new JLabel("0");
    private JLabel opponentLabel = new JLabel("0");
    private JLabel vsLabel = new JLabel(" VS ");


    public QuizCampGUI(){
        frame();
        ClientHandler clientHandler = new ClientHandler(this);
        startButton.addActionListener(e -> {
            clientHandler.startButton();
            clientHandler.connectToServer();
        });

        ActionListener gameButtonListener = e -> {
            clientHandler.checkAnswer(e.getActionCommand());
        };
        for (JButton e : gameButtons)
            e.addActionListener(gameButtonListener);
    }

    private void frame(){
        setLayout(new BorderLayout());
        setTitle("Quiz Camp");

        add(topPanel, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        topPanel.setLayout(new GridLayout(2,1));
        scorePanel1.setLayout(new FlowLayout());
        scorePanel2.setLayout(new FlowLayout());

        topPanel.add(scorePanel2);
        topPanel.add(scorePanel1);

        scorePanel2.add(new JLabel("Player"));
        scorePanel2.add(vsLabel);
        scorePanel2.add(new JLabel("Opponent"));

        scorePanel1.add(playerLabel);
        scorePanel1.add(scoreLabel);
        scorePanel1.add(opponentLabel);

        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(textPane, BorderLayout.NORTH);
        questionPanel.add(textPane2, BorderLayout.CENTER);
        questionPanel.setBackground(Color.white);
        questionPanel.setPreferredSize(new Dimension(280,100));

        setTextAlignment(textPane);
        setTextAlignment(textPane2);

        textPane.setFont(new Font("Dialog",0,25));
        textPane.setEditable(false);
        textPane.setText("Welcome!");

        textPane2.setFont(new Font("Dialog",0,17));
        textPane2.setEditable(false);
        textPane2.setText("Press play to start.");


        for (JButton b : gameButtons)
            b.setPreferredSize(new Dimension(50,50));

        buttonPanel.add(startButton);
        buttonPanel.setPreferredSize(new Dimension(-1,150));

        setSize(500,600);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setTextAlignment(JTextPane textPane){
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0,doc.getLength(),center,false);
    }

    public JPanel getButtonPanel(){
        return buttonPanel;
    }

    public JButton[] getGameButtons(){
        return gameButtons;
    }

    public JButton getStartButton(){
        return startButton;
    }

    public JTextPane getTextPane(){
        return textPane;
    }

    public JTextPane getTextPane2(){
        return textPane2;
    }

    public JLabel getPlayerLabel(){
        return playerLabel;
    }

    public JLabel getOpponentLabel(){
        return opponentLabel;
    }


    public static void main(String[] args) {
        new QuizCampGUI();
    }
}
