
// Name: Dena, Rene
// Project: #1
// Due: 9/19/2018
// Course: CS-2450-01-F18
// Description: This program is used to convert the temperature from Celsius to Fahrenheit and vice versa
// Note:  Type in a value and press enter
package jtempetature;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Create and set up the window.
public class JTemperature extends JFrame
{
    //The initial creation of all my Labels
    private JLabel nameLabel;
    private JLabel celsiusLabel;
    private JLabel fahrenheitLabel;
    private JLabel resultLabel;

    //The initial creation of my text fields
    private JTextField celsiusTF;
    private JTextField fahrenheitTF;

    //The initial creation of my handlers
    private CelsHandler celsiusHandler;
    private FahrHandler fahrenheitHandler;

    //Base set for size of window/frame (Height, WIDTH)
    private static final int WIDTH = 500;
    private static final int HEIGHT = 75;
    private static final double FTOC = 5.0/9.0;
    private static final double CTOF = 9.0/5.0;
    private static final int OFFSET = 32;


    //Set up the content pane.
    public JTemperature()
    {
        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        setTitle("Temperature Converter");
        //Creating our container for our main panel
        Container c = getContentPane();
        //Row x Column initialization
        c.setLayout(new GridLayout(1,4));

        //Implementation of our Labels
        nameLabel = new JLabel("  (C) R. Dena ", SwingConstants.LEADING);
        celsiusLabel = new JLabel("Enter #: ", SwingConstants.RIGHT);
        fahrenheitLabel = new JLabel("degrees C = ", SwingConstants.RIGHT);
        resultLabel = new JLabel(" degrees F  ", SwingConstants.RIGHT);

        celsiusTF = new JTextField(7);
        fahrenheitTF = new JTextField(7);

        //Adding our Labels to to the container within the main panel
        c.add(nameLabel);
        c.add(celsiusLabel);
        c.add(celsiusTF);
        c.add(fahrenheitLabel);
        c.add(fahrenheitTF);
        c.add(resultLabel);

        // Creation of our main conversion functions
        celsiusHandler = new CelsHandler();
        fahrenheitHandler = new FahrHandler();

        // Adding Action Listeners to our functions
        celsiusTF.addActionListener(celsiusHandler);
        fahrenheitTF.addActionListener(fahrenheitHandler);

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event dispatch thread.
         */
        // Setting up default size fro frame/window
        setSize (WIDTH, HEIGHT);
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Call for action listener in Celsius
    private class CelsHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double celsius, fahrenheit;

            celsius =
                    Double.parseDouble(celsiusTF.getText());
            fahrenheit = celsius * CTOF + OFFSET;
            fahrenheitTF.setText(String.format("%.2f", fahrenheit));
        }
    }

    // call for action listener in Fahrenheit
    private class FahrHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double celsius, fahrenheit;
            fahrenheit =
                    Double.parseDouble(fahrenheitTF.getText());
            celsius = (fahrenheit - OFFSET) * FTOC;
            celsiusTF.setText(String.format("%.2f", celsius));
        }
    }

    // the call for the main function class
    public static void main(String[] args)
    {
        JTemperature tempConv = new JTemperature();
        tempConv.setLocationRelativeTo(null);
    }
}


//END OF CODE
