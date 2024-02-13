import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class EventEditPage {
    public EventEditPage(int index) {
        JFrame frame = new JFrame("Event Edit Page");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(510, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);

        JLabel title = new JLabel("Edit Event");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title.setForeground(Color.gray);
        title.setBounds(20, 0, 150, 50);
        frame.add(title);

        JPanel eventEdit = new JPanel();
        eventEdit.setBounds(20, 40, 450, 350);
		eventEdit.setLayout(new BoxLayout(eventEdit, BoxLayout.Y_AXIS));
		eventEdit.setBorder(new EmptyBorder(10, 10, 10, 30));
		
		// edit an event name
		JLabel nameLbl = new JLabel("Event Name");
		nameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evName = new JTextField(50);
		evName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
		
		JLabel descLbl = new JLabel("Description");
		descLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evDesc = new JTextField(50);
		evDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		JLabel locationLbl = new JLabel("Location");
		locationLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField evLocation = new JTextField(50);
		evLocation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

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

		//button to update an instance of the event object
	    JButton updateEvent = new JButton("Update event");
		updateEvent.setBounds(30, 450, 60, 40);
		updateEvent.addActionListener(e -> {
            //get inputs from textfields and update the event
            Event.getEvents().get(index).setName(evName.getText());
            Event.getEvents().get(index).setDesc(evDesc.getText());
			Event.getEvents().get(index).setLocation(evLocation.getText());
            Event.getEvents().get(index).setDate(evDate.getText());
            Event.getEvents().get(index).setTime(evTime.getText());
            Event.getEvents().get(index).setPoints(Integer.parseInt(evPoints.getText()));
        });

        eventEdit.add(nameLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evName);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(descLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evDesc);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(locationLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evLocation);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(dateLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evDate);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(timeLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evTime);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(pointsLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(evPoints);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(updateEvent);

        frame.add(eventEdit);

        frame.setVisible(true);
    }
}
