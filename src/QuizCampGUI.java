import javax.swing.*;
import java.awt.*;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-12 <br>
 * Time: 14:29 <br>
 * Project: IntelliJ IDEA <br>
 */
public class QuizCampGUI extends JFrame {

    JButton button1 = new JButton("Button 1");
    JButton button2 = new JButton("Button 2");
    JButton button3 = new JButton("Button 3");
    JButton button4 = new JButton("Button 4");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JTextArea textArea = new JTextArea();

    QuizCampGUI(){
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.NORTH);
        panel1.add(textArea);
        panel1.setPreferredSize(new Dimension(280,100));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setText("adsads adwadwawdgearg kuaegr\n pkuwafrpiuarfpköaklwgjnr \npijkuwaefklhjwafe");

        add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new GridLayout(2,2));
        panel2.add(button1); panel2.add(button2); panel2.add(button3); panel2.add(button4);

        add(panel3, BorderLayout.SOUTH);

        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new QuizCampGUI();
    }
}
