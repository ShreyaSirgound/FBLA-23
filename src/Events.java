
// Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;

public class Events extends JFrame implements ActionListener {
   ImageIcon img, img2, img3;
   JLabel image, image2, image3, futureEvents, event1, event2, event3, event4, event5;
   JFrame frame;
   JButton button1, button2, button3, survey;
   
   // This method contains all the GUI elements required to form up the JFrame
   public Events() throws IOException {
	  
	  // Initializing all variables needed for this frame (Labels, buttons, colours, images), and declaring them as desired
	  frame = new JFrame();
	  frame.setLayout(null);
	  //img = new ImageIcon(this.getClass().getResource("ImagesFolder1/3dDollarSign.png"));
	  //img2 = new ImageIcon(this.getClass().getResource("ImagesFolder1/fatcapitalist.png"));
	  //img3 = new ImageIcon(this.getClass().getResource("ImagesFolder1/3dDollarSign.png"));
	  futureEvents = new JLabel("Events This Week:");
	  futureEvents.setFont(new Font("Impact", Font.PLAIN, 25));
	  futureEvents.setBounds(80,10,800,150);
	  Font font = futureEvents.getFont();
	  Map attributes = font.getAttributes();
	  attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	  futureEvents.setFont(font.deriveFont(attributes));
	  futureEvents.setForeground(Color.white);
	  
	  
	  button1 = new JButton("SPORTS EVENTS");
	  button1.setBounds(80, 150, 250, 60);
	  button1.addActionListener(this);
	  Color color1 = new Color (0, 210, 0);
	  button1.setBackground(color1);
	  
	  button2 = new JButton("ACADEMIC EVENTS");
	  button2.setBounds(80, 350, 250, 60);
	  button2.addActionListener(this);
	  button2.setBackground(color1);
	  
	  button3 = new JButton("ART EVENTS");
	  button3.setBounds(80, 550, 250, 60);
	  button3.addActionListener(this);
	  button3.setBackground(color1);
	  
	  survey = new JButton("Survey to personalize your results!");
	  survey.setBounds(930, 50, 250, 40);
	  survey.addActionListener(this);
	  Color color2 = new Color (0, 210, 0);
	  survey.setBackground(color1);
	  
	  // Add all elements to this frame
	  frame.add(futureEvents);
	  frame.add(button1);
	  frame.add(button2);
	  frame.add(button3);
	  frame.add(survey);
	  
	  // Basic and fundamental additions to get the appropriate JFrame Panel required
	  frame.setSize(1275, 775);
	  Color color3 = new Color (32, 82, 92);
	  frame.getContentPane().setBackground(color3);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   
   
   // If "Survey" button is clicked... go to the next class
   public void actionPerformed(ActionEvent e) {
	   

	   	 
	   	if (e.getSource() == survey) {
			  
			  frame.dispose();
			  
			  Survey surveyResponse = new Survey();
			  
		  }
	   }
   
   
   
   // Call the method, with all the various GUI elements on it
   public static void main(String[] args) throws Exception {
      new Events();
      
   }
}	
