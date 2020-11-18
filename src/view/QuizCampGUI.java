package view;

import controller.ClientHandler;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-12 <br>
 * Time: 14:29 <br>
 * Project: QuizCamp <br>
 */
public class QuizCampGUI extends JFrame {

    private JButton gameButton1 = new JButton("Button 1");
    private JButton gameButton2 = new JButton("Button 2");
    private JButton gameButton3 = new JButton("Button 3");
    private JButton gameButton4 = new JButton("Button 4");
    private JButton startButton = new JButton("Play");

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();

    private JTextPane textPane = new JTextPane();

    private JLabel scoreLabel = new JLabel("Score");
    private JLabel playerLabel = new JLabel("Player: ");
    private JLabel opponentLabel = new JLabel("Opponent: ");
    private JLabel vsLabel = new JLabel("VS");



    public QuizCampGUI(){
        frame();
        ClientHandler clientHandler = new ClientHandler(this);
        startButton.addActionListener(e -> {
            panel3.removeAll();
            panel3.setLayout(new GridLayout(2,2));
            panel3.add(gameButton1); panel3.add(gameButton2); panel3.add(gameButton3); panel3.add(gameButton4);
            panel3.updateUI();
        });
    }

    private void frame(){
        setLayout(new BorderLayout());
        setTitle("Quiz Camp");


        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);


        panel1.setLayout(new GridLayout(2,1));
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());

        panel1.add(panel5);
        panel1.add(panel4);

        panel5.add(scoreLabel);

        panel4.add(playerLabel);
        panel4.add(vsLabel);
        panel4.add(opponentLabel);

        panel2.add(textPane);
        panel2.setBackground(Color.white);
        setTextAlignment();

        textPane.setEditable(false);
        textPane.setText("Welcome!\nPress play to start.\n HEJ HEJ\nsad\n adf \n jg  \n HEJ HEJ HEJ HEJ");
        panel2.setPreferredSize(new Dimension(280,100));


        panel3.add(startButton);

        setSize(300,500);
//        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setTextAlignment(){
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0,doc.getLength(),center,false);
    }

    public JPanel getPanel3(){
        return panel3;
    }

    public JButton[] getGameButtons(){
        return new JButton[]{gameButton1,gameButton2,gameButton3,gameButton4};
    }

    public JButton getStartButton(){
        return startButton;
    }

    public JTextPane getTextPane(){
        return textPane;
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
