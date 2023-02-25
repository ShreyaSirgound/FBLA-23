import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UnsupportedLookAndFeelException;

//TODO: create homepage layout
public class MainFrame {
	JButton button;
	
    public MainFrame() {
        //setup the frame
        JFrame frame = new JFrame("Home Page");
        setMainFrame(frame);
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

        JScrollPane eventsPane = new JScrollPane();
        eventsPane.getViewport().setBackground(Color.white);
        eventsPane.setBounds(325, 95, 500, 585);
        eventsPane.setBorder( new EmptyBorder(15, 0, 15, 15));
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

    /**
     * The main GUI frame of the application
     */
    private static Frame mainFrame;

    public static Frame getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(Frame newMainFrame) {
		mainFrame = newMainFrame;
	}

    public static void main(String[] args) {

		// Set the look and feel of the GUI.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			System.err.println("Unsupported operating system.");
		}

        // Create and open the title GUI.
		EventQueue.invokeLater(TitleFrame::new);
    }
}
