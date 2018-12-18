// Name: Dena, Rene
// Project: #2
// Due: 10/1/2018
// Course: CS-2450-01-F18
/* Description: Below is a simple calculator program in java using swing.
It is a simple calculator in Java which can perform basic arithmetic operations
like addition, subtraction, multiplication and division of two numbers.
*/


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
 
public class JCalculator extends JPanel implements ActionListener {
 
  private JTextField display = new JTextField("0");
  private int result = 0;
  private String operator = "=";
  private boolean calculating = true;
 
  public JCalculator() {
    setLayout(new BorderLayout());
 
    display.setEditable(false);
    add(display, "North");
 
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 4));
 
    String buttonLabels = "789/456X123-0C=+";
    for (int i = 0; i < buttonLabels.length(); i++) {
      JButton b = new JButton(buttonLabels.substring(i, i + 1));
      panel.add(b);
      b.addActionListener(this);
    }
    add(panel, "Center");
  }
 
  public void actionPerformed(ActionEvent evt) {
    String cmd = evt.getActionCommand();
    if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
      if (calculating)
        display.setText(cmd);
      else
        display.setText(display.getText() + cmd);
      calculating = false;
    } else {
      if (calculating) {
        if (cmd.equals("-")) {
          display.setText(cmd);
          calculating = false;
        } else
          operator = cmd;
      } else {
        int x = Integer.parseInt(display.getText());
        calculate(x);
        operator = cmd;
        calculating = true;
      }
    }
  }
 
  private void calculate(int n) {
    if (operator.equals("+"))
      result += n;
    else if (operator.equals("-"))
      result -= n;
    else if (operator.equals("*"))
      result *= n;
    else if (operator.equals("/"))
      result /= n;
    else if (operator.equals("="))
      result = n;
    display.setText("" + result);
  }
 
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame();
    frame.setTitle("Calculator");
    frame.setSize(200, 200);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
 
    Container contentPane = frame.getContentPane();
    contentPane.add(new JCalculator());
    frame.show();
  }
}
