//Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;



public class Survey extends JFrame implements ActionListener {

JLabel label1;
JRadioButton choices[] = new JRadioButton[5];
JFrame frame;
JButton buttonNext, survey;
ButtonGroup bg;

//"Tell us how interested you are in the following school excursion activities"

// This method contains all the GUI elements required to form up the JFrame
public Survey() throws IOException {
	  
	  // Initializing all variables needed for this frame (Labels, buttons, colours, images), and declaring them as desired
	  frame = new JFrame();
	  frame.setLayout(null);
}
	  
	  		Survey (String s) {
	  			
		  		super(s);
		  		label1 = new JLabel();
		  		frame.add(label1);
		  		bg = new ButtonGroup();
		  		for (int i = 0; i < 5; i++) {
		  			choices[i] = new JRadioButton();
		  			add(choices[i]);
		  			
		  		}
	  	}

	  

	  button = new JButton("Proceed");
	  button.setBounds(530, 400, 250, 60);
	  button.addActionListener(this);
	  Color color1 = new Color (0, 210, 0);
	  button.setBackground(color1);
	  
	  // Add all elements to this frame
	  frame.add(futureEvents);
	  frame.add(image);
	  frame.add(image2);
	  frame.add(image3);
	  frame.add(button);
	  frame.add(survey);
	  
	  // Basic and fundamental additions to get the appropriate JFrame Panel required
	  frame.setSize(1275, 775);
	  Color color3 = new Color (32, 82, 92);
	  frame.getContentPane().setBackground(color3);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);




// If "Survey" button is clicked... go to the next class
public void actionPerformed(ActionEvent e) {
	   
	  if (e.getSource() == survey) {
		  
		  frame.dispose();
		  
		  try {
			// Main2 surveyResponse = new Main2();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
	  }
}

// Call the method, with all the various GUI elements on it
public static void main(String[] args) throws Exception {
   new Main2();
   
}


