//
// Name: Dena, Rene
// Homework: #2
// Due: 11/21/18
// Course: CS-2450-01-F18
//
// Description:
// A simple program that will read a java source file and display it using the text area component.
//

import java.io.File;
import java.awt.Image;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
//
import javax.swing.text.*;
import java.awt.FlowLayout;


public class JavaViewer{                
	Image icon;
	JFrame frame;
	JLabel displayText;
	public static void main(String[] args){
		new JavaViewer();
	}

	public JavaViewer(){
		//icon = new ImageIcon(this.getClass().getResource("JavaViewer.png")).getImage();

		frame = new JFrame("JavaViewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(icon);
                //
                frame.setBackground(Color.BLUE);
               
                //
                JPanel middlePanel = new JPanel ();
                //
                middlePanel.setLayout(new FlowLayout());

                JTextArea textField = new JTextArea(15, 30);
                textField.setEditable(true);
                JScrollPane scroll = new JScrollPane(textField);
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
                middlePanel.add ( scroll );
                frame.add(middlePanel);
                displayText = new JLabel("R. Dena");
                displayText.setHorizontalAlignment(JLabel.CENTER);
		displayText.setOpaque(true);
		displayText.setFont(new Font("Courier New", Font.PLAIN, 12));
                
                
                JPopupMenu menu = new JPopupMenu();
                Action cut = new DefaultEditorKit.CutAction();
                cut.putValue(Action.NAME, "Cut");
                cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
                menu.add(cut);

                Action copy = new DefaultEditorKit.CopyAction();
                copy.putValue(Action.NAME, "Copy");
                copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
                menu.add(copy);

                Action paste = new DefaultEditorKit.PasteAction();
                paste.putValue(Action.NAME, "Paste");
                paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
                menu.add(paste);

                Action selectAll = new SelectAll();
                menu.add(selectAll);

                textField.setComponentPopupMenu(menu);
                //
                
                
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(this.createFileMenu());
		menuBar.add(this.createHelpMenu());
		frame.setJMenuBar(menuBar);

		frame.setPreferredSize(new Dimension(400, 300));
		frame.pack();
		frame.setVisible(true);
	}
        
        //
        static class SelectAll extends TextAction
        {
            public SelectAll()
            {
                super("Select All");
                putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
            }

            public void actionPerformed(ActionEvent e)
            {
                JTextComponent component = getFocusedComponent();
                component.selectAll();
                component.requestFocusInWindow();
            }
        }
        //
        
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);


		JMenuItem openItem = new JMenuItem("Open...");
		fileMenu.add(openItem);

		openItem.setMnemonic(KeyEvent.VK_F);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
