//Import packages as needed
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.Map;

import javax.swing.*;



public class eventDescriptions extends JFrame implements ActionListener {
ImageIcon img, img2, img3;
JLabel event1, event2, event3, event4, event5, event6, event7, event8, event9, event10, event11;
JFrame frame;
JButton engaged;

// This method contains all the GUI elements required to form up the JFrame
public eventDescriptions() throws IOException {
	  
	  // Initializing all variables needed for this frame (Labels, buttons, colours, images), and declaring them as desired
	  frame = new JFrame();
	  frame.setLayout(null);

	  


	  eventNumSetter evNum=new eventNumSetter();

	  switch (evNum.get()) {
	      case 1:
	        event1 = new JLabel("Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points");
	    	  event1.setFont(new Font("Impact", Font.PLAIN, 25));
	    	  event1.setBounds(30,80,800,150);
	    	  event1.setForeground(Color.white);
	    	  frame.add(event1);
	    	  break;
	    	  
	      case 2:
	      	event2 = new JLabel("Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points");
	        event2.setFont(new Font("Impact", Font.PLAIN, 25));
	        event2.setBounds(30,80,800,150);
	       	event2.setForeground(Color.white);
	       	frame.add(event2);
	       	break;
	       	
	      case 3:
		    event3 = new JLabel("Help fund heart research by joining us and participating in jump rope! Help save lives and have fun at the same time!\nMarch 7, 2023 @ 3:15PM\nWORTH 10 Points");
		    event3.setFont(new Font("Impact", Font.PLAIN, 25));
		    event3.setBounds(30,80,800,150);
		    event3.setForeground(Color.white);
		    frame.add(event3);
		    break;
		    
	      case 4:
			    event4 = new JLabel("BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points");
			    event4.setFont(new Font("Impact", Font.PLAIN, 25));
			    event4.setBounds(30,80,800,150);
			    event4.setForeground(Color.white);
			    frame.add(event4);
			    break;
	  	  
	      case 5:
			    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;
		  
	      case 6:
			    event5 = new JLabel("Come down all kings and queens! To the chess tournament of the ages!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;
		  
	      case 7:
			    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;

	      case 8:
			    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;
		  
	      case 9:
			    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;
		  
	      case 10:
	    	    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;
		  
	      case 11:
			    event5 = new JLabel("Do you know what Tchoukball is? If not, don't fret! It is loads of fun, and very physically demanding! Drop in and Join us!\nMarch 13, 2023 @ 3:30PM\nWORTH 10 Points");
			    event5.setFont(new Font("Impact", Font.PLAIN, 25));
			    event5.setBounds(30,80,800,150);
			    event5.setForeground(Color.white);
			    frame.add(event5);
			    break;


	  }
	  
	  engaged = new JButton("Register");
	  engaged.setBounds(30, 400, 250, 60);
	  engaged.addActionListener(this);
	  Color color1 = new Color (0, 210, 0);
	  engaged.setBackground(color1);
	  
	  // Add all elements to this frame
	  
	  frame.add(engaged);
	  
	  // Basic and fundamental additions to get the appropriate JFrame Panel required
	  frame.setSize(1275, 775);
	  Color color3 = new Color (32, 82, 92);
	  frame.getContentPane().setBackground(color3);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}



// If "Survey" button is clicked... go to the next class

public void actionPerformed(ActionEvent e) {
	   
		  if (e.getSource() == engaged) {
			  
			  // WE HAVE TO MAKE SOME SORT OF REMINDER SYSTEM FOR USER TO KNOW THAT THEY HAVE SAID THEY HAVE REGISTERED IN THIS AFTER GOING BACK TO DASHBOARD
			  frame.dispose();
			  
		  }
	   }

// Call the method, with all the various GUI elements on it
public static void main(String[] args) throws Exception {
   new eventDescriptions();
   
}
}	