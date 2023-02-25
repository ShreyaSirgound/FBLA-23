import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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

		//panel to create a new event
		//TO DO: get entered text in the textfields to disappear after the event has been created
		//TO DO: create fields to enter date, time, and points for each event
		JPanel eventCreate = new JPanel();
		eventCreate.setBounds(20, 20, 500, 500);
		
		JTextField evName = new JTextField();
		evName.setColumns(50);
		evName.setBounds(30, 30, 200, 50);
		
		JTextField evDesc = new JTextField();
		evDesc.setColumns(50);
		evDesc.setBounds(30, 60, 200, 50);
		
		JButton newEvent = new JButton("Create event");
		newEvent.setBounds(30, 450, 60, 40);
		newEvent.addActionListener(e -> {
			String eventName = evName.getText();
			String eventDesc = evDesc.getText();
			Event event = new Event(eventName, eventDesc);
			Event.addEvent(event);
		});

		eventCreate.add(evName);
		eventCreate.add(evDesc);
		eventCreate.add(newEvent);
		frame.add(eventCreate);

		//button to switch to student view (this feature should only accesible for admins)
		JButton toStudentView = new JButton("View as student");
		toStudentView.setBounds(600, 600, 60, 60);
		toStudentView.addActionListener(e -> {
			new MainFrame();
			frame.dispose();
		});
		frame.add(toStudentView);

		frame.setVisible(true);
	}
}
