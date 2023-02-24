//Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;



public class academicEvents extends JFrame implements ActionListener {
ImageIcon img, img2, img3;
JLabel image, image2, image3, futureEvents;
JFrame frame;
JButton event1, event2, event3, event4;

// This method contains all the GUI elements required to form up the JFrame
public academicEvents() throws IOException {
	  
	  // Initializing all variables needed for this frame (Labels, buttons, colours, images), and declaring them as desired
	  frame = new JFrame();
	  frame.setLayout(null);
	  //img = new ImageIcon(this.getClass().getResource("ImagesFolder1/3dDollarSign.png"));
	  //img2 = new ImageIcon(this.getClass().getResource("ImagesFolder1/fatcapitalist.png"));
	  //img3 = new ImageIcon(this.getClass().getResource("ImagesFolder1/3dDollarSign.png"));
	  futureEvents = new JLabel("Click on the events for more details");
	  futureEvents.setFont(new Font("Impact", Font.PLAIN, 25));
	  futureEvents.setBounds(10,0,800,50);
	  Font font = futureEvents.getFont();
	  Map attributes = font.getAttributes();
	  attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	  futureEvents.setFont(font.deriveFont(attributes));
	  futureEvents.setForeground(Color.white);
	  
	  event1 = new JButton("Chess Tournament");
	  event1.setBounds(30, 50, 250, 60);
	  event1.addActionListener(this);
	  Color color1 = new Color (0, 210, 0);
	  event1.setBackground(color1);
	  
	  event2 = new JButton("Science Fair");
	  event2.setBounds(30, 200, 250, 60);
	  event2.addActionListener(this);
	  event2.setBackground(color1);
	  
	  event3 = new JButton("Debate Tournament");
	  event3.setBounds(30, 350, 250, 60);
	  event3.addActionListener(this);
	  event3.setBackground(color1);
	  
	  event4 = new JButton("Entrepreneurship Festival");
	  event4.setBounds(30, 500, 250, 60);
	  event4.addActionListener(this);
	  event4.setBackground(color1);
	  
	  // Add all elements to this frame
	  frame.add(futureEvents);
	  frame.add(event1);
	  frame.add(event2);
	  frame.add(event3);
	  frame.add(event4);
	  
	  // Basic and fundamental additions to get the appropriate JFrame Panel required
	  frame.setSize(1275, 775);
	  Color color3 = new Color (32, 82, 92);
	  frame.getContentPane().setBackground(color3);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}



// If "Survey" button is clicked... go to the next class

public void actionPerformed(ActionEvent e) {
	   
		  if (e.getSource() == event1) {
			  
			  eventNumSetter evNum=new eventNumSetter();
			  evNum.set(6);
			  frame.dispose();
			  
			  try {
				 eventDescriptions sports = new eventDescriptions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		  }
		  
		  if (e.getSource() == event2) {
			  
			  eventNumSetter evNum2 = new eventNumSetter();
			  evNum2.set(7);
			  frame.dispose();
			  
			  try {
				  eventDescriptions sports = new eventDescriptions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		  }
		  
		  if (e.getSource() == event3) {
			  
			  eventNumSetter evNum3 = new eventNumSetter();
			  evNum3.set(8);
			  frame.dispose();
			  
			  try {
				  eventDescriptions sports = new eventDescriptions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		  }
		  
		  if (e.getSource() == event4) {
			  
			  eventNumSetter evNum4 = new eventNumSetter();
			  evNum4.set(9);
			  frame.dispose();
			  
			  try {
				  eventDescriptions sports = new eventDescriptions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		  }
		  
	   }

// Call the method, with all the various GUI elements on it
public static void main(String[] args) throws Exception {
   new academicEvents();
   
}
}	
