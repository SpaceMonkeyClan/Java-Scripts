import javax.swing.*;
import java.util.*;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFontChooser extends JDialog {
	private final List<Integer> styleValues = Arrays.asList(new Integer[]{ Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC });

	private JComboBox<String> familyBox;
	private JComboBox<String> styleBox;
	private JComboBox<Integer> sizeBox;

	private Font selectedFont = null;

	public JFontChooser(JFrame parent, Font defaultFont){
		super(parent, "Font Selection", true);
		setResizable(false);

		JPanel panel = new JPanel();

		JLabel label = new JLabel("Family");
		panel.add(label);

		familyBox = new JComboBox<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		panel.add(familyBox);
		familyBox.setSelectedItem(defaultFont.getFamily());


		label = new JLabel("Style");
		panel.add(label);
		styleBox = new JComboBox<String>(new String[]{ "Normal", "Bold", "Italic", "Bold & Italic" });
		styleBox.setSelectedIndex(styleValues.indexOf(defaultFont.getStyle()));
		panel.add(styleBox);

		label = new JLabel("Size");
		panel.add(label);

		sizeBox = new JComboBox<Integer>(new Integer[]{ 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 28, 32, 36, 48, 72 });
		panel.add(sizeBox, "wrap");
		sizeBox.setSelectedItem(defaultFont.getSize());


		JButton btn = new JButton("Apply");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] fontStyles = new String[]{ "PLAIN", "BOLD",  "ITALIC", "BOLDITALIC" };
				String style = fontStyles[styleValues.get(styleBox.getSelectedIndex())];

				selectedFont = Font.decode(familyBox.getSelectedItem() + "-" + style + "-" + sizeBox.getSelectedItem());
				setVisible(false);
			}
		});
		panel.add(btn, "span, split 2, align right");

		btn = new JButton("Cancel");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFont = null;
				setVisible(false);
			}
		});
		panel.add(btn);

		getContentPane().add(panel);
		pack();
	}

	public static Font showDialog(JFrame parent, Font defaultFont) {
		JFontChooser chooser = new JFontChooser(parent, defaultFont);
		chooser.setVisible(true);
		return chooser.getSelectedFont();
	}

	public Font getSelectedFont(){
		return this.selectedFont;
	}

}

