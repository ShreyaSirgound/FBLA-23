import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

public class MainFrame {
	JButton button;
	static BufferedReader in; 
	static BufferedWriter out;
	static Student curUser;
    static String q1 = "", q2 = "", q3 = "", q4 = ""; 
    static LocalDate quarter1 = null, quarter2 = null, quarter3 = null, quarter4 = null;
    static LocalDate currDate = LocalDate.now();
    public MainFrame() throws ClassNotFoundException, IOException {
    	//reads in events
        if(Event.eventList.isEmpty()){
            EventsDataFile.Input();
        }

        //defining the quarter-end dates
        System.out.println();
        //String q1 = "", q2 = "", q3 = "", q4 = ""; 
        String[] date = new String[2];
        //LocalDate quarter1 = null, quarter2 = null, quarter3 = null, quarter4 = null;
        in = new BufferedReader(new FileReader("data\\quarterlyDates.txt"));
        for(int i = 0; i < 4; i++){
            date = in.readLine().split("\\|");
            if(date[1].equals("1")){
                q1 = date[0];
            } else if (date[1].equals("2")){
                q2 = date[0];
            } else if (date[1].equals("3")){
                q3 = date[0];  
            } else if (date[1].equals("4")){
                q4 = date[0];
            }
        }
        
        quarter1 = LocalDate.parse(q1, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter2 = LocalDate.parse(q2, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter3 = LocalDate.parse(q3, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter4 = LocalDate.parse(q4, DateTimeFormatter.ISO_LOCAL_DATE);

        //LocalDate currDate = LocalDate.now();

        //finds and saves the quarterly winners as required
        if(String.valueOf(currDate).equals(q1)){
            saveWinners(PointSystem.quarterWinners(), "quarter1.txt");
        } else if (String.valueOf(currDate).equals(q2)){
            saveWinners(PointSystem.quarterWinners(), "quarter2.txt");
        } else if (String.valueOf(currDate).equals(q3)){
            saveWinners(PointSystem.quarterWinners(), "quarter3.txt");
        } else if (String.valueOf(currDate).equals(q4)){
            saveWinners(PointSystem.quarterWinners(), "quarter4.txt");
        }
   
        //setup the frame
        JFrame frame = new JFrame("Home Page");
        Main.setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.white);

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
		//button to view current prizes
		JButton prizes = new JButton("View Prizes");
		prizes.setBounds(50, 100, 180, 75);
		prizes.addActionListener(e -> {
			try {
				new ViewPrizes();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		});
		sidebar.add(prizes);

		//button to view events list that the student is registered in
		JButton personalView = new JButton("My Registered Events");
        personalView.setBounds(50, 300, 180, 75);
        personalView.addActionListener(e -> {
			try {
				new PersonalListView();
				frame.dispose();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
                JOptionPane.showMessageDialog(frame, "You have not registered for any events yet.");
			} catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
		});
		sidebar.add(personalView);
		//button to switch to calender view
		JButton calenderView = new JButton("Access Calender");
		calenderView.setBounds(50, 500, 180, 75);
		calenderView.addActionListener(e -> {
			try {
				new CalenderView("student");
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
        title1.setBounds(305, 70, 150, 50);
        frame.add(title1);

        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(300, 110, 490, 555);
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
            eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

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

            //register in an event
            JButton register = new JButton("Register"); 
            register.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            register.setBackground(Color.decode("#76BEE8"));
            register.setOpaque(true);
            register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JTextPane registerPane = new JTextPane();
            registerPane.insertComponent(register);
            registerPane.setBackground(Color.white);
            registerPane.setBorder(new EmptyBorder(0, 5, 8, 0));
            int idx = i;
            register.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "Would you like to register for this event?", 
														"",
															JOptionPane.YES_OPTION,
															JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
                        if(MainFrame.curUser == null){
                            JOptionPane.showMessageDialog(frame, "Registration unsuccessful because the user is not a student.");
                          } else {
                            if(curUser.getMyEvents().contains(Event.eventList.get(idx))){
                                JOptionPane.showMessageDialog(frame, "You have already registered for this event.");
                            } else {
                                int curPoints = curUser.getPoints();
                                curPoints += Event.eventList.get(idx).getPoints();
                                curUser.setPoints(curPoints);
                                curUser.addEvent(Event.eventList.get(idx));
                                try {
                                    saveRegEvents();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(frame, "Successfully registered!");
                            }
                          }
					}
				}
			});
            eventInfoPane.add(eventPointsInfo);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventLocation);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventSetting);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(registerPane);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);

        //leaderboard panel that shows an updated leaderboard everytime the page is opened
        JLabel title2 = new JLabel("Current Leaderboard");
        title2. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2. setForeground(Color.gray);
        title2.setBounds(825, 70, 200, 50);
        frame.add(title2);

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new BoxLayout(leaderboard, BoxLayout.Y_AXIS));
        leaderboard.setBackground(Color.decode("#F66845"));
        leaderboard.setBounds(825, 110, 420, 250);
        leaderboard.setBorder( new EmptyBorder(15, 15, 15, 15));

        JTextArea nineWinner = new JTextArea("Grade 9: " + PointSystem.nineWinners().get(0).getName());
        for (int i = 1; i < PointSystem.nineWinners().size(); i++) {
            if (PointSystem.nineWinners().size() == 1) {
                continue;
            } else {
                nineWinner.setText(nineWinner.getText() + ", " + (PointSystem.nineWinners().get(i)).getName());
            } 
        }
        nineWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        nineWinner.setBackground(Color.decode("#F66845"));
        nineWinner.setForeground(Color.white);
        nineWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        nineWinner.setEditable(false);
        nineWinner.setLineWrap(true);
        nineWinner.setWrapStyleWord(true);
        leaderboard.add(nineWinner);

        JTextArea tenWinner = new JTextArea("Grade 10: " + PointSystem.tenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.tenWinners().size(); i++) {
            if (PointSystem.tenWinners().size() == 1) {
                continue;
            } else {
                tenWinner.setText(tenWinner.getText() + ", " + (PointSystem.tenWinners().get(i)).getName());
            } 
        }
        tenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tenWinner.setBackground(Color.decode("#F66845"));
        tenWinner.setForeground(Color.white);
        tenWinner.setEditable(false);
        tenWinner.setLineWrap(true);
        tenWinner.setWrapStyleWord(true);
        tenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(tenWinner);

        JTextArea elevenWinner = new JTextArea("Grade 11: " + PointSystem.elevenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.elevenWinners().size(); i++) {
            if (PointSystem.elevenWinners().size() == 1) {
                continue;
            } else {
                elevenWinner.setText(elevenWinner.getText() + ", " + (PointSystem.elevenWinners().get(i)).getName());
            } 
        }
        elevenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        elevenWinner.setBackground(Color.decode("#F66845"));
        elevenWinner.setForeground(Color.white);
        elevenWinner.setEditable(false);
        elevenWinner.setLineWrap(true);
        elevenWinner.setWrapStyleWord(true);
        elevenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(elevenWinner);

        JTextArea twelveWinner = new JTextArea("Grade 12: " + PointSystem.twelveWinners().get(0).getName());
        for (int i = 1; i < PointSystem.twelveWinners().size(); i++) {
            if (PointSystem.twelveWinners().size() == 1) {
                continue;
            } else {
                twelveWinner.setText(twelveWinner.getText() + ", " + (PointSystem.twelveWinners().get(i)).getName());
            } 
        }
        twelveWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        twelveWinner.setBackground(Color.decode("#F66845"));
        twelveWinner.setForeground(Color.white);
        twelveWinner.setEditable(false);
        twelveWinner.setLineWrap(true);
        twelveWinner.setWrapStyleWord(true);
        twelveWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(twelveWinner);

        JTextArea randomWinner = new JTextArea("Random Winners: " + PointSystem.randomWinners().get(0).getName());
        for (int i = 1; i < PointSystem.randomWinners().size(); i++) {
            if (PointSystem.randomWinners().size() == 1) {
                continue;
            } else {
                randomWinner.setText(randomWinner.getText() + ", " + (PointSystem.randomWinners().get(i)).getName());
            } 
        }
        randomWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        randomWinner.setBackground(Color.decode("#F66845"));
        randomWinner.setForeground(Color.white);
        randomWinner.setEditable(false);
        randomWinner.setLineWrap(true);
        randomWinner.setWrapStyleWord(true);
        randomWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(randomWinner);

        frame.add(leaderboard);

        //leaderboard panel that shows the final winners at the end of the quarter or from the previous quarter
        JLabel title3 = new JLabel("Quarterly Leaderboard");
        title3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title3.setForeground(Color.gray);
        title3.setBounds(825, 375, 300, 50);
        frame.add(title3);

        JPanel leaderboardFinal = new JPanel();
        leaderboardFinal.setLayout(new BoxLayout(leaderboardFinal, BoxLayout.Y_AXIS));
        leaderboardFinal.setBackground(Color.decode("#F66845"));
        leaderboardFinal.setBounds(825, 415, 420, 250); 
        leaderboardFinal.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        //only generate winners for this leaderboard if it is the end of the quarter
        JTextArea msg = new JTextArea();
        msg.setBackground(Color.decode("#F66845"));
        msg.setForeground(Color.white);
        msg.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        msg.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        msg.setEditable(false);
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        @SuppressWarnings("unchecked")
        ArrayList<String>[] s = new ArrayList[4];
        if(quarter1 == null || quarter2 == null || quarter3 == null || quarter4 == null){
            msg.append("Could not find winners. Check again later.");
            leaderboardFinal.add(msg);
        } else if(quarter1 != null && quarter2 != null && quarter3 != null && quarter4 != null) {
            System.out.println("in here");
            if(String.valueOf(currDate).equals(q1) || (currDate.isAfter(quarter1) && currDate.isBefore(quarter2))) {
                System.out.println("in q1");
                s = readWinners("quarter1.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q2) || (currDate.isAfter(quarter2) && currDate.isBefore(quarter3))) {
                System.out.println("in q2");
                s = readWinners("quarter2.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q3) || (currDate.isAfter(quarter3) && currDate.isBefore(quarter4))) {
                System.out.println("in q3");
                s = readWinners("quarter3.txt");
                System.out.println("ouii");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q4)) {
                System.out.println("in q4");
                s = readWinners("quarter4.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);
            
            } else if(currDate.isAfter(quarter4)) {
                msg.append("All winners have been selected for this year.");
                leaderboardFinal.add(msg);
            } else if(currDate.isBefore(quarter1)) {
                msg.append("No one winners have been selected yet. Check again later.");
                leaderboardFinal.add(msg);
            }
           
        } else {
            JOptionPane.showConfirmDialog(frame, "  Quarterly dates incorrectly entered or not saved. Please input quarterly end dates. ", 
								                    "Information Input Error",
															JOptionPane.OK_CANCEL_OPTION);
            new AdminView();
        }

        frame.add(leaderboardFinal);
        frame.setVisible(true);
    }
    
    protected static void saveUser() throws IOException {
		out = new BufferedWriter(new FileWriter("data\\students.txt"));
		
		for(Student s : Student.getStudents()) {
			out.write(s.getName() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getEmail() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPassword() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getGrade() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPoints() + " ");
		}
		out.newLine();
		out.close();
    }

    protected static void saveRegEvents() throws IOException {
        out = new BufferedWriter(new FileWriter("data\\registeredEvents.txt"));

        System.out.println("saving personal events");
        for(Student s : Student.getStudents()) {
            System.out.println(s.getName() + s.getMyEvents().toString());
            if(s.getMyEvents().isEmpty()){
                out.write("null" + "*");
            } else {
                for(Event e : s.getMyEvents()){
                    out.write(Event.toString(e) + "*");
                }
            }
            out.newLine();
		}
        out.close();
    }

    protected static void writeWinners(JTextArea ta, ArrayList<String>[] w){
        for(int i = 0; i < 4; i++){
            ta.append("Grade " + (i+9) + ": ");
            for(String winners : w[i]){
                ta.append(winners);
            }
            ta.append("\n\n");
        }
    }

    protected static void saveWinners(java.util.List<Student>[] winners, String f) throws IOException {
        out = new BufferedWriter(new FileWriter("data\\quarterlyWinners" + File.separator + f));
        for(int i = 0; i < 4; i++){ //iterates through all the possible grades of the students
            for(int j = 0; j < winners[i].size(); j++){
                out.write(Student.toString((winners[i].get(j))));
                out.newLine();
            }
        }
        out.close();
    }

    protected static ArrayList<String>[] readWinners(String f) throws IOException {
        in = new BufferedReader(new FileReader("data\\quarterlyWinners" + File.separator + f));
        @SuppressWarnings("unchecked")
        ArrayList<String>[] winners = new ArrayList[4];
        String[] student;
        String stud = new String();

        //initializing winners array
        for (int i = 0; i < 4; i++) { 
            winners[i] = new ArrayList<String>(); 
        }
        ArrayList<String> wins9 = new ArrayList<String>();
        ArrayList<String> wins10 = new ArrayList<String>();
        ArrayList<String> wins11 = new ArrayList<String>();
        ArrayList<String> wins12 = new ArrayList<String>();

        while((stud = in.readLine()) != null){
            try {
                student = stud.split("\\|");
                if(student[1].equals("9")){
                    if(wins9.size() > 0){
                        wins9.add(", " + student[0]);
                    } else {
                        wins9.add(student[0]);
                    }
                } else if(student[1].equals("10")){
                    if(wins10.size() > 0){
                        wins10.add(", " + student[0]);
                    } else {
                        wins10.add(student[0]);
                    }
                } else if(student[1].equals("11")){
                    if(wins11.size() > 0){
                        wins11.add(", " + student[0]);
                    } else {
                        wins11.add(student[0]);
                    }
                } else if(student[1].equals("12")){
                    if(wins12.size() > 0){
                        wins12.add(", " + student[0]);
                    } else {
                        wins12.add(student[0]);
                    }
                } else {
                    System.out.println("Student does not have a registered grade.");
                }
            } catch (NullPointerException e1){
                e1.printStackTrace();
            }
        }
        
        for(String h : wins9){
            winners[0].add(h);
        }
        for(String h : wins10){
            winners[1].add(h);
        }
        for(String h : wins11){
            winners[2].add(h);
        }
        for(String h : wins12){
            winners[3].add(h);
        }

        in.close();
        return winners;
    }
}

class ViewPrizes {
    public ViewPrizes() {
        JFrame frame = new JFrame("Prizes List");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 430);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);

        JLabel title = new JLabel("Current Prizes");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title.setForeground(Color.GRAY);
        title.setBounds(20, 0, 150, 50);
        frame.add(title);

        JTextArea prize1 = new JTextArea("Minimum 20 Points: \n$15 Starbucks Giftcard!");
        prize1.setWrapStyleWord(true);
        prize1.setLineWrap(true);
        prize1. setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        prize1. setForeground(Color.white);
        prize1.setBounds(25, 50, 240, 55);
        prize1.setBackground(Color.decode("#6EA6D0"));
        prize1.setEditable(false);
        prize1.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(prize1);

        JTextArea prize2 = new JTextArea("Minimum 60 Points: \n$20 Starbucks Giftcard!");
        prize2.setWrapStyleWord(true);
        prize2.setLineWrap(true);
        prize2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        prize2.setForeground(Color.white);
        prize2.setBackground(Color.decode("#DCC4E7"));
        prize2.setBounds(25, 135, 240, 55);
        prize2.setBorder(BorderFactory.createLineBorder(Color.black));
        prize2.setEditable(false);
        frame.add(prize2);

        JTextArea prize3 = new JTextArea("Minimum 100 Points: \nFree School Stationary!");
        prize3.setWrapStyleWord(true);
        prize3.setLineWrap(true);
        prize3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        prize3.setForeground(Color.white);
        prize3.setBounds(25, 220, 240, 55);
        prize3.setBackground(Color.decode("#A6D59D"));
        prize3.setBorder(BorderFactory.createLineBorder(Color.black));
        prize3.setEditable(false);
        frame.add(prize3);

        JTextArea prize4 = new JTextArea("Minimum 200+ Points: \nFree pizza day!");
        prize4.setWrapStyleWord(true);
        prize4.setLineWrap(true);
        prize4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        prize4.setForeground(Color.white);
        prize4.setBackground(Color.decode("#3E3F40"));
        prize4.setBounds(25, 315, 230, 55);
        prize4.setBorder(BorderFactory.createLineBorder(Color.black));
        prize3.setEditable(false);
        frame.add(prize4);

        frame.setVisible(true);

    }
}

