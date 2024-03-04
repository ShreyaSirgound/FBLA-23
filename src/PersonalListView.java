import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

public class PersonalListView {
    public PersonalListView() throws ClassNotFoundException, IOException {
        //setup the frame
        JFrame frame = new JFrame("My Events");
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
        sidebar.setBounds(0, 60, 300, 720);

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

        //button to switch to general student view 
		JButton studentView = new JButton("Homepage");
		studentView.setBounds(50, 300, 180, 75);
		studentView.addActionListener(e -> {
			try {
				new MainFrame();
				frame.dispose();
			} catch (IOException | ClassNotFoundException | NullPointerException e1) {
				e1.printStackTrace();
			}
		});
        sidebar.add(studentView);
        frame.add(sidebar);

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
        JLabel title1 = new JLabel("My Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(325, 70, 150, 50);
        frame.add(title1);

        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(320, 110, 490, 555);
        eventsPane.getVerticalScrollBar().setUnitIncrement(15);
        
        //gets information from each event in the student's personal events list and adds it all to a single panel
        ArrayList<Event> userEvents = MainFrame.curUser.getMyEvents();
        for (int i = 0; i < userEvents.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            eventInfoPane.setMaximumSize(new Dimension(480, 245));

            //holds event points
            JTextField eventPointsInfo = new JTextField("WORTH " + userEvents.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
            eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
            eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventPointsInfo.setEditable(false);
            eventPointsInfo.setBackground(Color.decode("#76BEE8"));
			eventPointsInfo.setForeground(Color.white);
            eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

            //holds event name
            JTextField eventNameInfo = new JTextField(userEvents.get(i).getName()); //gets the event name
            eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameInfo.setEditable(false);
            eventNameInfo.setBackground(Color.white);
            eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
            
            //holds the event description
            JTextArea eventDescInfo = new JTextArea(userEvents.get(i).getDesc());
            eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventDescInfo.setForeground(Color.gray);
            eventDescInfo.setEditable(false);
            eventDescInfo.setLineWrap(true);
            eventDescInfo.setWrapStyleWord(true);
            eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

            //holds the event location
            JTextField eventLocation = new JTextField(userEvents.get(i).getLocation());
            eventLocation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventLocation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventLocation.setEditable(false);
            eventLocation.setBackground(Color.white);
            eventLocation.setBorder(new EmptyBorder(0, 5, 1, 0));

            //holds the event date, and time
			JTextField eventSetting = new JTextField(userEvents.get(i).getDate() //gets the event date
                                                    + "  @ " + userEvents.get(i).getTime()); //gets the event time
            eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventSetting.setEditable(false);
            eventSetting.setBackground(Color.white);
            eventSetting.setBorder(new EmptyBorder(0, 5, 5, 0));

            //unregister from an event
            JButton unregister = new JButton("Unregister"); 
            unregister.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            unregister.setBackground(Color.decode("#76BEE8"));
            unregister.setOpaque(true);
            unregister.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JTextPane unregisterPane = new JTextPane();
            unregisterPane.insertComponent(unregister);
            unregisterPane.setBackground(Color.white);
            unregisterPane.setEditable(false);
            unregisterPane.setBorder(new EmptyBorder(0, 5, 8, 0));
            int idx = i;
            unregister.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to unregister from this event?", 
														"",
															JOptionPane.YES_OPTION,
															JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
                        int curPoints = MainFrame.curUser.getPoints();
                        curPoints -= Event.eventList.get(idx).getPoints();
                        MainFrame.curUser.setPoints(curPoints);
                        MainFrame.curUser.removeEvent(idx);
                        try {
                            new PersonalListView();
                        } catch (ClassNotFoundException | IOException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Successfully unregistered.");
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
            eventInfoPane.add(unregisterPane);
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
        if(MainFrame.quarter1 == null || MainFrame.quarter2 == null || MainFrame.quarter3 == null || MainFrame.quarter4 == null){
            msg.append("Could not find winners. Check again later.");
            leaderboardFinal.add(msg);
        } else if(MainFrame.quarter1 != null && MainFrame.quarter2 != null && MainFrame.quarter3 != null && MainFrame.quarter4 != null) {
            System.out.println("in here");
            if(String.valueOf(MainFrame.currDate).equals(MainFrame.q1) || (MainFrame.currDate.isAfter(MainFrame.quarter1) && MainFrame.currDate.isBefore(MainFrame.quarter2))) {
                System.out.println("in q1");
                s = MainFrame.readWinners("quarter1.txt");
                MainFrame.writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(MainFrame.currDate).equals(MainFrame.q2) || (MainFrame.currDate.isAfter(MainFrame.quarter2) && MainFrame.currDate.isBefore(MainFrame.quarter3))) {
                System.out.println("in q2");
                s = MainFrame.readWinners("quarter2.txt");
                MainFrame.writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(MainFrame.currDate).equals(MainFrame.q3) || (MainFrame.currDate.isAfter(MainFrame.quarter3) && MainFrame.currDate.isBefore(MainFrame.quarter4))) {
                System.out.println("in q3");
                s = MainFrame.readWinners("quarter3.txt");
                System.out.println("ouii");
                MainFrame.writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(MainFrame.currDate).equals(MainFrame.q4)) {
                System.out.println("in q4");
                s = MainFrame.readWinners("quarter4.txt");
                MainFrame.writeWinners(msg, s);
                leaderboardFinal.add(msg);
            
            } else if(MainFrame.currDate.isAfter(MainFrame.quarter4)) {
                msg.append("All winners have been selected for this year.");
                leaderboardFinal.add(msg);
            } else if(MainFrame.currDate.isBefore(MainFrame.quarter1)) {
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
}
