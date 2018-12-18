//
// Name: Dena, Rene
// Project: 3
// Due: 10/15/2018
// Course: CS-2450-01-F18
//
// Description:
// This program is called the memory game. Goal is to match a set of cards using your memory. 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

class MemoryGame implements ActionListener {
    JFrame frame;
    ImageIcon[] icons;
    JButton[] buttons;
    ImageIcon[] hiddenIcons;
    int numClicks;
    ImageIcon MemoryGame;

    boolean[] completed;
    int firstClicked;

    MemoryGame() {
    	frame = new JFrame("Memory Game");
    	frame.setSize(500, 500);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));

        JMenuBar menu = new JMenuBar();
        
        JMenu action = new JMenu("Action");
        action.setMnemonic(KeyEvent.VK_A);
        action.getAccessibleContext().setAccessibleDescription("The menu in this program that has game timer and reveal.");
       menu.add(action);

        JMenu gameTimer = new JMenu("Game Timer");
        ButtonGroup groupI = new ButtonGroup();
        JRadioButtonMenuItem pauseI = new JRadioButtonMenuItem("Pause");
        pauseI.setSelected(true);
        groupI.add(pauseI);
        gameTimer.add(pauseI);
        JRadioButtonMenuItem resume = new JRadioButtonMenuItem("Resume");
        groupI.add(resume);
        gameTimer.add(resume);
        action.add(gameTimer);
        
        JMenuItem reveal = new JMenuItem("Reveal");
        reveal.setMnemonic(KeyEvent.VK_R);
        action.add(reveal);
        
        action.addSeparator();
        JMenuItem exitMenu = new JMenuItem("Exit");
        action.add(exitMenu);
        
        action.addSeparator();
        JMenuItem resetMenu = new JMenuItem("Reset");
        action.add(resetMenu);
        
        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        help.getAccessibleContext().setAccessibleDescription("The menu in this program that has view help and about.");
        menu.add(help);
       
        JMenuItem aboutMenu = new JMenuItem("View Help...");
        aboutMenu.setMnemonic(KeyEvent.VK_H);
        help.add(aboutMenu);
        
        action.addSeparator();
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        help.add(about);
        
        JMenuItem timer = new JMenuItem("Timer: 0:00:00");
        menu.add(timer);
        
        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                switch (ae.getActionCommand()) {
                    case "Reveal": showTiles();
                    break;
                    case "Reset": newGame();
                    break;
                    case "Exit": System.exit(0);
                    break;
                    case "View Help...": JOptionPane.showMessageDialog(frame, "1) Turn over any two cards."
                            + "\n2) If the two cards match, they stay fliped."
                            + "\n3) If they don't match, turn them back over."
                            + "\n4) Remember what was on each card and where it was."
                            + "\n5) The game is over when all the cards have been matched.", "Instructions: ", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    case "About": JOptionPane.showMessageDialog(frame, "(C) Rene Dena. 2018", "Instructions: ", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        };

        //action.addActionListener(menuListener);
        
        reveal.addActionListener(menuListener);
        resetMenu.addActionListener(menuListener);
        exitMenu.addActionListener(menuListener);
        aboutMenu.addActionListener(menuListener);
        about.addActionListener(menuListener);

        MemoryGame = new ImageIcon("MemoryGame.png");

        icons = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            icons[i] = new ImageIcon(Integer.toString(i+1) + ".png");
        }

        hiddenIcons = new ImageIcon[12];

        buttons = new JButton[12];
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton(MemoryGame);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setActionCommand(Integer.toString(i));
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }

        completed = new boolean[12];
        numClicks = 0;
        firstClicked = -1;

        newGame();

        frame.setJMenuBar(menu);
    	frame.setVisible(true);
    }

    private void newGame() {
        ArrayList<Integer> availablePlaces = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++)
            availablePlaces.add(i);

        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int pos = rand.nextInt(availablePlaces.size());
            hiddenIcons[availablePlaces.get(pos)] = icons[i];
            availablePlaces.remove(pos);
            pos = rand.nextInt(availablePlaces.size());
            hiddenIcons[availablePlaces.get(pos)] = icons[i];
            availablePlaces.remove(pos);
        }
        for (int i = 0; i < 12; i++) {
            completed[i] = false;
        }
        clearTiles();
    }

    private void clearTiles() {
        for (int i = 0; i < 12; i++) {
            if (!completed[i]) {
                buttons[i].setIcon(MemoryGame);
                buttons[i].setEnabled(true);

                buttons[i].setBorderPainted(true);
            }
        }
    }

    private void showTiles() {
        for (int i = 0; i < 12; i++) {
            buttons[i].setIcon(hiddenIcons[i]);
            buttons[i].setRolloverIcon(hiddenIcons[i]);
            buttons[i].setBorderPainted(false);
            completed[i] = true;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (numClicks < 2) {
            if (buttons[Integer.parseInt(ae.getActionCommand())].getIcon() == MemoryGame) {
                buttons[Integer.parseInt(ae.getActionCommand())].setIcon(hiddenIcons[Integer.parseInt(ae.getActionCommand())]);
                buttons[Integer.parseInt(ae.getActionCommand())].setRolloverIcon(hiddenIcons[Integer.parseInt(ae.getActionCommand())]);
                buttons[Integer.parseInt(ae.getActionCommand())].setBorderPainted(false);
                numClicks++;
                if (numClicks == 1) {
                    firstClicked = Integer.parseInt(ae.getActionCommand());
                }
                else if (numClicks == 2) {
                    if (buttons[firstClicked].getIcon() == buttons[Integer.parseInt(ae.getActionCommand())].getIcon()) {
                        completed[firstClicked] = true;
                        completed[Integer.parseInt(ae.getActionCommand())] = true;
                    }
                }
            }
        }
        else {
            clearTiles();
            buttons[Integer.parseInt(ae.getActionCommand())].setIcon(hiddenIcons[Integer.parseInt(ae.getActionCommand())]);
            buttons[Integer.parseInt(ae.getActionCommand())].setRolloverIcon(hiddenIcons[Integer.parseInt(ae.getActionCommand())]);
            buttons[Integer.parseInt(ae.getActionCommand())].setBorderPainted(false);
            numClicks = 1;
            firstClicked = Integer.parseInt(ae.getActionCommand());
        }
    }

    public static void main(String args[]) { 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MemoryGame();
            }
        });
    }
}

