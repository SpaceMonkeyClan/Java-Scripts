//
// Name: Dena, Rene
// Project: #4
// Due: 11/9/18
// Course: CS-2450-01-F18
//
// Description:
// A simple program that will showcase different type of dialogs.
//
import java.io.File;
import java.awt.Image;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyDialogs{
	Image icon;

	JFrame frame;
	JLabel displayText;
	public static void main(String[] args){
		new MyDialogs();
	}

	public MyDialogs(){
		//icon = new ImageIcon(this.getClass().getResource("MyDialogs.png")).getImage();

		frame = new JFrame("My Dialogs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(icon);

		displayText = new JLabel("R. Dena");
		displayText.setHorizontalAlignment(JLabel.CENTER);
		displayText.setOpaque(true);
		displayText.setFont(new Font("Courier New", Font.PLAIN, 12));
		frame.getContentPane().add(displayText);


		JMenuBar menuBar = new JMenuBar();
		menuBar.add(this.createFileMenu());
		menuBar.add(this.createFormatMenu());
		menuBar.add(this.createHelpMenu());
		frame.setJMenuBar(menuBar);

		frame.setPreferredSize(new Dimension(200, 100));
		frame.pack();
		frame.setVisible(true);
	}

	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);


		JMenuItem openItem = new JMenuItem("Open...");
		fileMenu.add(openItem);

		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JFileChooser fc = new JFileChooser();

				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					File selected = fc.getSelectedFile();
					displayText.setText(selected.getAbsolutePath());
				}
			}
		});

		fileMenu.addSeparator();

		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (JOptionPane.showConfirmDialog(frame, "Are you SURE you want to exit?") == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});

		return fileMenu;
	}

	private JMenu createFormatMenu(){
		JMenu formatMenu = new JMenu("Format");
		formatMenu.setMnemonic(KeyEvent.VK_O);

		JMenuItem fontItem = new JMenuItem("Font...");
		formatMenu.add(fontItem);

		fontItem.setMnemonic(KeyEvent.VK_F);
		fontItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Font newFont = JFontChooser.showDialog(frame, displayText.getFont());
				if (newFont != null){
					displayText.setFont(newFont);
				}
			}
		});

		formatMenu.addSeparator();


		JMenu colorMenu = new JMenu("Color");
		formatMenu.add(colorMenu);
		colorMenu.setMnemonic(KeyEvent.VK_C);

		JMenuItem foregroundItem = new JMenuItem("Set Foreground...");
		colorMenu.add(foregroundItem);

		foregroundItem.setMnemonic(KeyEvent.VK_F);
		foregroundItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK+KeyEvent.ALT_MASK));
		foregroundItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Color newColor = JColorChooser.showDialog(frame, "Foreground Color", displayText.getForeground());
				if (newColor != null){
					displayText.setForeground(newColor);
				}
			}
		});


		JMenuItem backgroundItem = new JMenuItem("Set Background...");
		colorMenu.add(backgroundItem);

		backgroundItem.setMnemonic(KeyEvent.VK_B);
		backgroundItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Color newColor = JColorChooser.showDialog(frame, "Background Color", displayText.getBackground());
				if (newColor != null){
					displayText.setBackground(newColor);
				}
			}
		});

		return formatMenu;
	}

	private JMenu createHelpMenu(){
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);

		aboutItem.setMnemonic(KeyEvent.VK_A);
		aboutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				AboutDialog ad = new AboutDialog();
				ad.setIconImage(icon);
				ad.setVisible(true);
			}
		});
		return helpMenu;
	}
	
	class AboutDialog extends JDialog{
		public AboutDialog(){
			setTitle("About");
			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

			JLabel info = new JLabel("<html>Created with Java Swing<br>by R. Dena (c) 2018</html>");
			add(info);
			info.setHorizontalAlignment(JLabel.CENTER);
			info.setAlignmentX(0.5f);

			JButton close = new JButton("Close");
			add(close);
			close.setAlignmentX(0.5f);
			close.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					dispose();
				}
			});

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(250, 100);
		}
	}
}
