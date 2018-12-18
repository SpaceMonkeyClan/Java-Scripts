import javax.swing.*;
import java.awt.*;

public class App {
    private JTextField;
    private JButton;
    private JPanel MyPanel;
    private JLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyApp");
        frame.setContentPane(new App().MyPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
