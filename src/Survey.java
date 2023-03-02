
// Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;
   
   // This method contains all the GUI elements required to form up the JFrame

	  
	  // Initializing all variables needed for this frame (Labels, buttons, colours, images), and declaring them as desired
	  


class Survey extends JFrame implements ItemListener {

	// frame
	frame = new JFrame();
	frame.setLayout(null);

	// label
	static JLabel l, l1;

	// combobox
	static JComboBox c1;

	// main class
	public static void main(String[] args)
	{
		// create a new frame
		JFrame f1 = new JFrame("frame");

		// create a object
		Survey s = new Survey();

		// set layout of frame
		f1.setLayout(new FlowLayout());

		// array of string containing cities
		String s1[] = { "Strongly Interested", "Interested", "Neutral stance", "Not Interested", "Absolutely not!" };

		// create checkbox
		c1 = new JComboBox(s1);

		// add ItemListener
		c1.addItemListener(s);

		// create labels
		l = new JLabel("Are you into sports events?");
		l1 = new JLabel("Strongly Interested");

		// set color of text
		l.setForeground(Color.red);
		l1.setForeground(Color.blue);

		// create a new panel
		JPanel p = new JPanel();

		p.add(l);

		// add combobox to panel
		p.add(c1);

		p.add(l1);

		// add panel to frame
		f1.add(p);

		// set the size of frame
		f1.setSize(400, 300);

		f1.show();
		f1.setVisible(true);
	}
	public void itemStateChanged(ItemEvent e)
	{
		// if the state combobox is changed
		if (e.getSource() == c1) {

			l1.setText(c1.getSelectedItem() + " selected");
		}
	}
}