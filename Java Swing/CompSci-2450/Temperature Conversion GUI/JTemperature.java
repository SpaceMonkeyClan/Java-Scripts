// Name: Dena, Rene
// Project: Temperature Converter
// Due: Wednesday September 19, 2018
// Course: CS-2450-01-F18
//
// Description:
//      This program will essentially let a user enter a degree in Fahrenheit and then convert it into Celsius.


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class JTemperature {
    private JPanel panelMain;
    // North plane
    private JTextField cReneDenaTextField;
    // West plane
    private JTextField enterTextField;
    // Center plane
    private JTextField textField1;
    // East plane
    private JTextField degressFTextField;
    // South plane
    private JTextField degreesCTextField;

    // The event listener in which wil allow the user to enter degrees and hit enter to begin execution
    public JTemperature() {
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
    }

    // The class in which executes the fahrenheit to celsius function

    public class JTemperatures {

        /**
         * converts Fahrenheit temperatures to Celsius
         * @param temp temperature in degrees Fahrenheit
         * @returns temperature in degrees Celsius
         */

        public double f2c(double temp) {
            return .556 * (temp - 32);
        }
    }

    // The call to main for running the script

    public static void main(String[] args) {
        // The base window/frame for in which all functions will be held
        JFrame frame = new JFrame("Temperature Converter");
        frame.setContentPane(new JTemperature().panelMain);
        // The call to function when window is closed thus producing our exit code
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

