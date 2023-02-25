import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

//TODO: create homepage layout
public class MainFrame {
	JButton button;
	
    public MainFrame() {
        //setup the frame
        JFrame frame = new JFrame("Home Page");
        Main.setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.white); //get this to work

        //titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        titlebar.add(Common.getImage("logo_small.png"), BorderLayout.EAST);
        frame.add(titlebar);

        //sidebar
        JPanel sidebar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 60, 300, 720);
        JLabel stats = new JLabel("Quick Stats");
        stats.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 23));
        stats.setForeground(Color.white);
        sidebar.add(stats);
        frame.add(sidebar);

        //section that presents all the events + event info
        JLabel title1 = new JLabel("Current Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(325, 70, 150, 50);
        frame.add(title1);

        //TO DO: get the scroll thing to stay at the top when first running the program
        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        //allEvents.setSize(490, 555);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(325, 110, 490, 555);
        
        //gets information from each event in the events list and adds it all to a single panel
        List<Event> eventsList = Event.getEvents();//new ArrayList<Event>();

        /*
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points"));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points"));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points"));

        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points"));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points"));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points"));
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points"));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points"));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points"));
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points"));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points"));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points"));
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!\nMarch 6, 2023 @ 12:30PM\nWORTH 10 Points"));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!\nMarch 9, 2023 @ 3:30PM\nWORTH 20 Points"));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!\nMarch 7, 2023 @ 3:30PM\nWORTH 15 Points"));
        */

        for (int i = 0; i < eventsList.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            
            eventInfoPane.setMaximumSize(new Dimension(480, 170));

            //holds event name
            JTextField eventNameInfo = new JTextField(eventsList.get(i).getName());
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

            //register in an event
            JButton register = new JButton("Register"); //TO DO: create actionListener
            register.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            register.setBackground(Color.decode("#76BEE8"));
            register.setOpaque(true);
            JTextPane registerPane = new JTextPane();
            registerPane.insertComponent(register);
            registerPane.setBackground(Color.white);
            registerPane.setBorder(new EmptyBorder(0, 5, 8, 0));

            eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 8)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(registerPane);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);

        //leaderboard panel
        JLabel title2 = new JLabel("Leaderboard");
        title2. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2. setForeground(Color.gray);
        title2.setBounds(825, 360, 170, 50);
        frame.add(title2);

        JPanel leaderboard = new JPanel();
        leaderboard.setBackground(Color.decode("#F66845"));
        leaderboard.setBounds(825, 400, 420, 265);
        leaderboard.setBorder( new EmptyBorder(10, 0, 10, 10));
        frame.add(leaderboard);


        /**
        JButton button = new JButton("Events");
        button.setBounds(240, 460, 160, 40);
        //button.addActionListener(this);
        button.setBackground(Color.LIGHT_GRAY);
        frame.add(button);
        button.addActionListener(e -> {
            try {
				new Events();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            frame.dispose();
        });
        
        JButton button2 = new JButton("History");
        button2.setBounds(40, 60, 160, 40);
        button2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        button2.setBackground(Color.LIGHT_GRAY);
        frame.add(button2);
        button2.addActionListener(e -> {
            //new Events();
            frame.dispose();
        });
        
        JButton button3 = new JButton("Points");
        button3.setBounds(70, 60, 160, 40);
        button3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        button3.setBackground(Color.LIGHT_GRAY);
        frame.add(button3);
        button3.addActionListener(e -> {
            //new Events();
            frame.dispose();
        });
        */
        
        frame.setVisible(true);
    }
}
