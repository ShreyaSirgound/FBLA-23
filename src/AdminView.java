import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
public class AdminView {
	static BufferedReader in; 
	static BufferedWriter out;
	static FileOutputStream fos;
	static Admin curUser;
	static String[][] quarterDates = new String[4][3];
	static String[][] quarterlyDates = new String[4][1];
	

	public AdminView() throws ClassNotFoundException, IOException {
		JFrame frame = new JFrame("Admin View");

		if(Event.eventList.isEmpty()){
			EventsDataFile.Input(); 
		}

		try {
			in = new BufferedReader(new FileReader("data\\quarterlyDates.txt"));
			for(int i = 0; i < 4; i++){
				quarterlyDates[i][0] = in.readLine();
			}
		} catch (NullPointerException e1) {
			JOptionPane.showConfirmDialog(frame, "  Quarterly dates incorrectly entered or not saved. Please input quarterly end dates. ", 
								                    "Information Input Error",
															JOptionPane.OK_CANCEL_OPTION);
			new AdminView();
		}
	
		//set up the frame
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

		//sidebar
        JPanel sidebar = new JPanel();
		sidebar.setLayout(null);
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 60, 275, 720);
		
		//button to access search records (this feature is only accessible for admins)
		JButton search = new JButton("Data Records");
		search.setBounds(50, 100, 180, 75);
		search.addActionListener(e -> {
			try {
				new SearchRecords();
				frame.dispose();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		});
		sidebar.add(search);
		
		//button to switch to student view (this feature is only accessible for admins)
		JButton mainMenu = new JButton("Access Student View");
		mainMenu.setBounds(50, 300, 180, 75);
		mainMenu.addActionListener(e -> {
			try {
				new MainFrame();
				frame.dispose();
			} catch (IOException | ClassNotFoundException | NullPointerException e1) {
				e1.printStackTrace();
			}
		});
		sidebar.add(mainMenu);
		
		//button to switch to calender view
		JButton calenderView = new JButton("Access Calender");
		calenderView.setBounds(50, 500, 180, 75);
		calenderView.addActionListener(e -> {
			try {
				new CalenderView("admin");
				frame.dispose();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		});
		sidebar.add(calenderView);
        frame.add(sidebar);

		//section that presents all the events + event info
        JLabel title1 = new JLabel("Current Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(290, 70, 150, 50);
        frame.add(title1);

        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(290, 110, 490, 555);
		eventsPane.getVerticalScrollBar().setUnitIncrement(15);
        
        //gets information from each event in the events list and adds it all to a single panel
		for (int i = 0; i < Event.eventList.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            eventInfoPane.setMaximumSize(new Dimension(480, 220));

			//holds event points
            JTextField eventPointsInfo = new JTextField("WORTH " + Event.eventList.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
            eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
            eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventPointsInfo.setEditable(false);
            eventPointsInfo.setBackground(Color.decode("#76BEE8"));
			eventPointsInfo.setForeground(Color.white);
            eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

            //holds event name
            JTextField eventNameInfo = new JTextField(Event.eventList.get(i).getName()); //gets the event name
            eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameInfo.setEditable(false);
            eventNameInfo.setBackground(Color.white);
            eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
            
            //holds the event description
            JTextArea eventDescInfo = new JTextArea(Event.eventList.get(i).getDesc());
            eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventDescInfo.setForeground(Color.gray);
            eventDescInfo.setEditable(false);
            eventDescInfo.setLineWrap(true);
            eventDescInfo.setWrapStyleWord(true);
            eventDescInfo.setBorder(new EmptyBorder(5, 5, 5, 0));

			//holds the event location
            JTextField eventLocation = new JTextField(Event.eventList.get(i).getLocation());
            eventLocation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventLocation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventLocation.setEditable(false);
            eventLocation.setBackground(Color.white);
            eventLocation.setBorder(new EmptyBorder(0, 5, 1, 0));

			//holds the event date, and time
			JTextField eventSetting = new JTextField(Event.eventList.get(i).getDate() //gets the event date
													+ "  @ " + Event.eventList.get(i).getTime()); //gets the event time
			eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
			eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventSetting.setEditable(false);
            eventSetting.setBackground(Color.white);
            eventSetting.setBorder(new EmptyBorder(0, 5, 5, 0));

			//panel that holds the buttons to edit/delete any event
			JPanel eventChange = new JPanel();
			int eventIndex = i; //the index at which the event is in the events list
	
			JButton edit = new JButton("Edit");
			edit.setBackground(Color.blue);
			edit.addActionListener(e -> {
				new EventEditPage(eventIndex);
				JOptionPane.showMessageDialog(frame, "Event successfully edited!");
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
						Event.evAttendance.remove(Event.getEvents().get(eventIndex).getName()); //removes the event from the attendance hashmap
						Event.removeEvent(Event.getEvents().get(eventIndex)); //removes the event from the events list if the user selects YES
						JOptionPane.showMessageDialog(frame, "Event successfully deleted!");
						try {
							new AdminView();
						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});

			JButton genReport = new JButton("Generate Report");
			genReport.setBackground(Color.black);
			genReport.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					genEvReport(Event.eventList.get(eventIndex).getName());
					JOptionPane.showMessageDialog(frame, "Report successfully generated!");
				}
			});
			
			eventChange.add(delete, FlowLayout.LEFT);
			eventChange.add(edit, FlowLayout.LEFT);
			eventChange.add(genReport, FlowLayout.RIGHT);
			
			eventInfoPane.add(eventPointsInfo);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventLocation);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 4)));
            eventInfoPane.add(eventSetting);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 3)));
			eventInfoPane.add(eventChange);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);


		//panel to create a new event
		JLabel title2 = new JLabel("New Event");
        title2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2.setForeground(Color.gray);
        title2.setBounds(800, 70, 150, 50);
        frame.add(title2);

		JPanel eventCreate = new JPanel();
		eventCreate.setLayout(new BoxLayout(eventCreate, BoxLayout.Y_AXIS));
		eventCreate.setBorder(new EmptyBorder(10, 10, 10, 30));
		eventCreate.setBounds(800, 110, 450, 300);
		
		// add an event name
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

		//button to create an instance of the event object
		JButton newEvent = new JButton("Create event");
		newEvent.setBounds(30, 450, 60, 40);
		newEvent.addActionListener(e -> {
			String eventName = evName.getText();
			String eventDesc = evDesc.getText();
			String eventLocation = evLocation.getText();
			String eventDate = evDate.getText();
			String eventTime = evTime.getText();
			int eventPoints = Integer.parseInt(evPoints.getText());
			Event event = new Event(eventName, eventDesc, eventLocation, eventDate, eventTime, eventPoints);
			Event.addEvent(event);

			//outputs the new event to the events data file and to the attendance hashmap
			try {
				EventsDataFile.output(Event.getEvents());
				Event.evAttendance.put(event.getName(), null);
			} catch (IOException ex) {
				System.err.println("Failed to load data (IO): " + ex.getMessage());
				System.out.println("Cause: " + ex.getCause());

			} catch (ClassNotFoundException ex) {
				System.err.println("Failed to load data (Class): " + ex.getMessage());
				System.out.println("Cause: " + ex.getCause());
			}

			//refreshes the page
			try {
				new AdminView();
				JOptionPane.showMessageDialog(frame, "Event successfully created!");
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			frame.dispose();
		});

		eventCreate.add(nameLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evName);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(descLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evDesc);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(locationLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evLocation);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(dateLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evDate);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(timeLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evTime);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(pointsLbl);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(evPoints);
		eventCreate.add(Box.createRigidArea(new Dimension(0, 4)));
		eventCreate.add(newEvent);
		frame.add(eventCreate);

		//section that allows admin to choose when their quarters end (dates on which winners are generated)
        JLabel title3 = new JLabel("Quarterly End Dates");
        title3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title3.setForeground(Color.gray);
        title3.setBounds(800, 405, 200, 50);
        frame.add(title3);

		JPanel datesPane = new JPanel();
		datesPane.setLayout(new BoxLayout(datesPane, BoxLayout.Y_AXIS));
		datesPane.setBorder(new EmptyBorder(10, 10, 10, 30));
		datesPane.setBounds(800, 445, 450, 220);
		datesPane.setBackground(Color.decode("#283880"));

		//array of days
		String[] day = new String[31];
		for (int i = 0; i <= 30; i++) {
			day[i] = Integer.toString(i + 1);
		}

		//array of months
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		//array of years
		String[] year = new String[1001];
		
		for (int i = 0; i <= 1000; i++) {
			year[i] = Integer.toString(i+2024);
		}
		
		//quarter 1
		JPanel q1Pane = new JPanel();
		q1Pane.setBackground(Color.decode("#283880"));
		JLabel q1Lbl = new JLabel("Quarter 1:");
		q1Lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		q1Lbl.setForeground(Color.white);

		JLabel d1Lbl = new JLabel("DD");
		d1Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		d1Lbl.setForeground(Color.white);
		JComboBox d1 = new JComboBox(day);   

		JLabel m1Lbl = new JLabel("MM");
		m1Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		m1Lbl.setForeground(Color.white);
		JComboBox m1 = new JComboBox(months);

		JLabel y1Lbl = new JLabel("YY");
		y1Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		y1Lbl.setForeground(Color.white);
		JComboBox y1 = new JComboBox(year);

		JButton save1 = new JButton("Save");
		save1.setPreferredSize(new Dimension(60, 20));
		save1.addActionListener(e -> {
			String q1d = String.valueOf(d1.getSelectedItem());
			String q1day = String.format("%02d", Integer.parseInt(q1d));
			String q1month = String.format("%02d", m1.getSelectedIndex()+1); 
			String q1year = String.valueOf(y1.getSelectedItem());
			try {
				saveQDates(1, q1day, q1month, q1year);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		q1Pane.add(q1Lbl);
		q1Pane.add(d1Lbl);
		q1Pane.add(d1);
		q1Pane.add(m1Lbl);
		q1Pane.add(m1);
		q1Pane.add(y1Lbl);
		q1Pane.add(y1);
		q1Pane.add(save1);
		datesPane.add(q1Pane);

		//quarter 2
		JPanel q2Pane = new JPanel();
		q2Pane.setBackground(Color.decode("#283880"));
		JLabel q2Lbl = new JLabel("Quarter 2:");
		q2Lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		q2Lbl.setForeground(Color.white);
		
		JLabel d2Lbl = new JLabel("DD");
		d2Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		d2Lbl.setForeground(Color.white);
		JComboBox d2 = new JComboBox(day); 

		JLabel m2Lbl = new JLabel("MM");
		m2Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		m2Lbl.setForeground(Color.white);
		JComboBox m2 = new JComboBox(months); 
		
		JLabel y2Lbl = new JLabel("YY");
		y2Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		y2Lbl.setForeground(Color.white);
		JComboBox y2 = new JComboBox(year); 

		JButton save2 = new JButton("Save");
		save2.setPreferredSize(new Dimension(60, 20));
		save2.addActionListener(e -> {
			String q2d = String.valueOf(d2.getSelectedItem());
			String q2day = String.format("%02d", Integer.parseInt(q2d)); 
			String q2month = String.format("%02d", m2.getSelectedIndex()+1);
			String q2year = String.valueOf(y2.getSelectedItem());
			try {
				saveQDates(2, q2day, q2month, q2year);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		q2Pane.add(q2Lbl);
		q2Pane.add(d2Lbl);
		q2Pane.add(d2);
		q2Pane.add(m2Lbl);
		q2Pane.add(m2);
		q2Pane.add(y2Lbl);
		q2Pane.add(y2);
		q2Pane.add(save2);
		datesPane.add(q2Pane);

		//quarter 3
		JPanel q3Pane = new JPanel();
		q3Pane.setBackground(Color.decode("#283880"));
		JLabel q3Lbl = new JLabel("Quarter 3:");
		q3Lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		q3Lbl.setForeground(Color.white);
		
		JLabel d3Lbl = new JLabel("DD");
		d3Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		d3Lbl.setForeground(Color.white);
		JComboBox d3 = new JComboBox(day); 

		JLabel m3Lbl = new JLabel("MM");
		m3Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		m3Lbl.setForeground(Color.white);
		JComboBox m3 = new JComboBox(months); 

		JLabel y3Lbl = new JLabel("YY");
		y3Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		y3Lbl.setForeground(Color.white);
		JComboBox y3 = new JComboBox(year); 
		
		JButton save3 = new JButton("Save");
		save3.setPreferredSize(new Dimension(60, 20));
		save3.addActionListener(e -> {
			String q3d = String.valueOf(d3.getSelectedItem());
			String q3day = String.format("%02d", Integer.parseInt(q3d)); 
			String q3month = String.format("%02d", m3.getSelectedIndex()+1);
			String q3year = String.valueOf(y3.getSelectedItem());
			try {
				saveQDates(3, q3day, q3month, q3year);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		q3Pane.add(q3Lbl);
		q3Pane.add(d3Lbl);
		q3Pane.add(d3);
		q3Pane.add(m3Lbl);
		q3Pane.add(m3);
		q3Pane.add(y3Lbl);
		q3Pane.add(y3);
		q3Pane.add(save3);
		datesPane.add(q3Pane);

		//quarter 4
		JPanel q4Pane = new JPanel();
		q4Pane.setBackground(Color.decode("#283880"));
		JLabel q4Lbl = new JLabel("Quarter 4:");
		q4Lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		q4Lbl.setForeground(Color.white);
		
		JLabel d4Lbl = new JLabel("DD");
		d4Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		d4Lbl.setForeground(Color.white);
		JComboBox d4 = new JComboBox(day);  

		JLabel m4Lbl = new JLabel("MM");
		m4Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		m4Lbl.setForeground(Color.white);
		JComboBox m4 = new JComboBox(months);

		JLabel y4Lbl = new JLabel("YY");
		y4Lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		y4Lbl.setForeground(Color.white);
		JComboBox y4 = new JComboBox(year);

		JButton save4 = new JButton("Save");
		save4.setPreferredSize(new Dimension(60, 20));
		save4.addActionListener(e -> {
			String q4d = String.valueOf(d4.getSelectedItem());
			String q4day = String.format("%02d", Integer.parseInt(q4d));
			String q4month = String.format("%02d", m4.getSelectedIndex()+1);
			String q4year = String.valueOf(y4.getSelectedItem());
			try {
				saveQDates(4, q4day, q4month, q4year);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		q4Pane.add(q4Lbl);
		q4Pane.add(d4Lbl);
		q4Pane.add(d4);
		q4Pane.add(m4Lbl);
		q4Pane.add(m4);
		q4Pane.add(y4Lbl);
		q4Pane.add(y4);
		q4Pane.add(save4);
		datesPane.add(q4Pane);

		frame.add(datesPane);
		
		frame.setVisible(true);
	}

	protected static void saveUser() throws IOException {
		out = new BufferedWriter(new FileWriter("data\\admins.txt"));
		
		for(Admin a : Admin.getAdmins()) {
			out.write(a.getName() + " ");
		}
		out.newLine();
		for(Admin a : Admin.getAdmins()) {
			out.write(a.getEmail() + " ");
		}
		out.newLine();
		for(Admin a : Admin.getAdmins()) {
			out.write(a.getPassword() + " ");
		}
		out.newLine();
		out.close();
    }

	public void saveQDates(int quarter, String day, String month, String year) throws IOException {
		String file = ("data" + File.separator + "quarterlyDates.txt");
		boolean shouldAppendFile = true; 
		OutputStreamWriter writerOutputStream = new OutputStreamWriter(new FileOutputStream(file, shouldAppendFile), "UTF-8"); 

		quarterDates[quarter-1][0] = day;
		quarterDates[quarter-1][1] = month + "-";
		quarterDates[quarter-1][2] = year + "-";
 
		out = new BufferedWriter(writerOutputStream); 
		out.write(quarterDates[quarter-1][2] + quarterDates[quarter-1][1] + quarterDates[quarter-1][0] + "|" + quarter + "\n");
		out.close();
	}

	public static void genEvReport(String evName){
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
		String fileName = evName + "_" + now.getMonth() + "-" + now.getDayOfMonth() + "-" + now.getYear();
		File file = new File(fileName + ".txt");

		try {
			if(!file.exists()){
				if (file.createNewFile()) {
					System.out.println("File created: " + file.getName());

					ArrayList<String> regStudents = Event.evAttendance.get(evName);
					PrintWriter out =new PrintWriter(file.getName());

					//write value of the event key and all registered students)
					out.println(evName + " - " + now.getMonth() + " " + now.getDayOfMonth() + ", " + now.getYear() + " " + now.getHour() + ":" + now.getMinute());
					for(String student : regStudents){
						out.println(student);
					}
					out.flush();
					out.close();

					System.out.println("Record successfully generated");
				}
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}
