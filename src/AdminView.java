import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AdminView {
	public AdminView() {
		JFrame frame = new JFrame("Admin View");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.white);

		//titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        titlebar.add(Common.getImage("logo_small.png"), BorderLayout.EAST);
        frame.add(titlebar);

		//section that presents all the events + event info
        JLabel title1 = new JLabel("Current Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(25, 70, 150, 50);
        frame.add(title1);

		//TO DO: get the scroll thing to stay at the top when first running the program
        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(25, 110, 490, 555);
		eventsPane.getVerticalScrollBar().setUnitIncrement(15);
        
        //gets information from each event in the events list and adds it all to a single panel
        List<Event> eventsList = new ArrayList<Event>(); //use Event.getEvents(); after testing

		//events used for testing
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));

		for (int i = 0; i < eventsList.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            
            eventInfoPane.setMaximumSize(new Dimension(480, 200));

			//holds event points
            JTextField eventPointsInfo = new JTextField("WORTH " + eventsList.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
            eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
            eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventPointsInfo.setEditable(false);
            eventPointsInfo.setBackground(Color.decode("#76BEE8"));
			eventPointsInfo.setForeground(Color.white);
            eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

            //holds event name
            JTextField eventNameInfo = new JTextField(eventsList.get(i).getName()); //gets the event name
            eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameInfo.setEditable(false);
            eventNameInfo.setBackground(Color.white);
            eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
            
            //holds the event description
            JTextArea eventDescInfo = new JTextArea(eventsList.get(i).getDesc());
            eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventDescInfo.setForeground(Color.gray);
            eventDescInfo.setEditable(false);
            eventDescInfo.setLineWrap(true);
            eventDescInfo.setWrapStyleWord(true);
            eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

			//holds the event date, and time
			JTextField eventSetting = new JTextField(eventsList.get(i).getDate() //gets the event date
													+ "  @ " + eventsList.get(i).getTime()); //gets the event time
			eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
			eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventSetting.setEditable(false);
            eventSetting.setBackground(Color.white);
            eventSetting.setBorder(new EmptyBorder(5, 5, 3, 0));

			//panel that holds the buttons to edit/delete any event
			JPanel eventChange = new JPanel();
			int eventIndex = i; //the index at which the event is in the events list
	
			JButton edit = new JButton("Edit");
			edit.setBackground(Color.blue);
			edit.addActionListener(e -> {
				new EventEditPage(eventIndex);
			});

			JButton delete = new JButton("Delete");
			delete.setBackground(Color.red);

			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this event?", 
														"",
															JOptionPane.YES_OPTION,
															JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
					Event.removeEvent(Event.getEvents().get(eventIndex)); //removes the event from the events list if the user selects YES
					}
				}
			});
			/* 
			delete.addActionListener(e -> {
				new EventDeletePage(eventIndex);
			});*/

			eventChange.add(delete, FlowLayout.LEFT);
			eventChange.add(edit, FlowLayout.LEFT);
			
			eventInfoPane.add(eventPointsInfo);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventSetting);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventChange);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);


		//panel to create a new event
		//TO DO: get entered text in the textfields to disappear after the event has been created
		//TO DO: update gui to look more professional
		JLabel title2 = new JLabel("New Event");
        title2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2.setForeground(Color.gray);
        title2.setBounds(550, 70, 150, 50);
        frame.add(title2);

		JPanel eventCreate = new JPanel();
		eventCreate.setLayout(new BoxLayout(eventCreate, BoxLayout.Y_AXIS));
		eventCreate.setBorder(new EmptyBorder(10, 10, 10, 30));
		eventCreate.setBounds(550, 110, 450, 300);
		
		// add an event name
		JLabel nameLbl = new JLabel("Event Name");
		nameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evName = new JTextField(50);
		evName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
		
		JLabel descLbl = new JLabel("Description");
		descLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evDesc = new JTextField(50);
		evDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		JLabel dateLbl = new JLabel("Date");
		dateLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evDate = new JTextField(50);
		evDate.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		JLabel timeLbl = new JLabel("Time");
		timeLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evTime = new JTextField(10);
		evTime.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		JLabel pointsLbl = new JLabel("Points");
		pointsLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evPoints = new JTextField(50);
		evPoints.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		//button to create an instance of the event object
		JButton newEvent = new JButton("Create event");
		newEvent.setBounds(30, 450, 60, 40);
		newEvent.addActionListener(e -> {
			String eventName = evName.getText();
			String eventDesc = evDesc.getText();
			String eventDate = evDate.getText();
			String eventTime = evTime.getText();
			int eventPoints = Integer.parseInt(evPoints.getText());
			Event event = new Event(eventName, eventDesc, eventDate, eventTime, eventPoints);
			Event.addEvent(event);

			//outputs the new event to the events data file
			try {
				EventsDataFile.output(Event.getEvents());

			} catch (IOException ex) {
				System.err.println("Failed to load data (IO): " + ex.getMessage());
				System.out.println("Cause: " + ex.getCause());

			} catch (ClassNotFoundException ex) {
				System.err.println("Failed to load data (Class): " + ex.getMessage());
				System.out.println("Cause: " + ex.getCause());
			}
			
		});

		eventCreate.add(nameLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(evName);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(descLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(evDesc);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(dateLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(evDate);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(timeLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(evTime);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(pointsLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(evPoints);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 5)));
		eventCreate.add(newEvent);
		frame.add(eventCreate);

		//button to switch to student view (this feature should only accesible for admins)
		JButton toStudentView = new JButton("View as student");
		toStudentView.setBounds(1100, 600, 150, 60);
		toStudentView.addActionListener(e -> {
			new MainFrame();
			frame.dispose();
		});
		frame.add(toStudentView);

		frame.setVisible(true);
	}
}
