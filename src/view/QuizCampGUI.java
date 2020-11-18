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

    JButton gameButton1 = new JButton("Button 1");
    JButton gameButton2 = new JButton("Button 2");
    JButton gameButton3 = new JButton("Button 3");
    JButton gameButton4 = new JButton("Button 4");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JTextArea textArea = new JTextArea();

    JTextPane textPane = new JTextPane();

    JButton start = new JButton("Play");


    public QuizCampGUI(){
        frame();
        ClientHandler clientHandler = new ClientHandler(this);
        start.addActionListener(e -> {
            panel3.removeAll();
            panel3.setLayout(new GridLayout(2,2));
            panel3.add(gameButton1); panel3.add(gameButton2); panel3.add(gameButton3); panel3.add(gameButton4);
            panel3.updateUI();
        });
    }

    private void frame(){
        setLayout(new BorderLayout());
        setTitle("Quiz Camp");
        panel2.setBackground(Color.white);
        add(panel1, BorderLayout.NORTH);
        panel1.add(new JLabel("QuizCamp"));

        add(panel2, BorderLayout.CENTER);
//        panel2.add(textArea);

        panel2.add(textPane);
        setTextAlignment();

        textPane.setEditable(false);
        textPane.setText("Welcome!\nPress play to start.\n HEJ HEJ\nsad\n adf \n jg  \n HEJ HEJ HEJ HEJ");
        panel2.setPreferredSize(new Dimension(280,100));
//        textArea.setSize(new Dimension(280,100));
//        textArea.setLineWrap(true);
//        textArea.setEditable(false);
//        textArea.setText("\tWelcome!\nPress play to start.\n HEJ HEJ\nsad\n adf \n jg");

        add(panel3, BorderLayout.SOUTH);

        panel3.add(start);

        setSize(300,300);
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

    public static void main(String[] args) {
        new QuizCampGUI();
    }
}
