//Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;



public class sportsEvents extends JFrame implements ActionListener {
ImageIcon img, img2, img3;
JLabel image, image2, image3, futureEvents;
JFrame frame;
JButton event1, event2, event3, event4, event5;

// This method contains all the GUI elements required to form up the JFrame
public sportsEvents() throws IOException {
	  
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
	  
	  event1 = new JButton("Cross-Country Meet");
	  event1.setBounds(30, 50, 250, 60);
	  event1.addActionListener(this);
	  Color color1 = new Color (0, 210, 0);
	  event1.setBackground(color1);
	  
	  event2 = new JButton("Soccer Intramural");
	  event2.setBounds(30, 200, 250, 60);
	  event2.addActionListener(this);
	  event2.setBackground(color1);
	  
	  event3 = new JButton("Jump Rope for Heart");
	  event3.setBounds(30, 350, 250, 60);
	  event3.addActionListener(this);
	  event3.setBackground(color1);
	  
	  event4 = new JButton("School Volleyball Team");
	  event4.setBounds(30, 500, 250, 60);
	  event4.addActionListener(this);
	  event4.setBackground(color1);
	  
	  event5 = new JButton("Tchoukball Intamural");
	  event5.setBounds(30, 650, 250, 60);
	  event5.addActionListener(this);
	  event5.setBackground(color1);
	  
	  // Add all elements to this frame
	  frame.add(futureEvents);
	  frame.add(event1);
	  frame.add(event2);
	  frame.add(event3);
	  frame.add(event4);
	  frame.add(event5);
	  
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
			  evNum.set(1);
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
			  evNum2.set(2);
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
			  evNum3.set(3);
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
			  evNum4.set(4);
			  frame.dispose();
			  
			  try {
				  eventDescriptions sports = new eventDescriptions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		  }
		  
		  if (e.getSource() == event5) {
			  
			  eventNumSetter evNum5 = new eventNumSetter();
			  evNum5.set(5);
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
   new sportsEvents();
   
}
}	
