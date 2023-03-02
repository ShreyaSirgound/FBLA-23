import java.awt.EventQueue;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    private static Frame mainFrame;
    final static int MAX = 10000;
    static BufferedReader in; 
	static BufferedWriter out;
	static int numOfUsers;
	static String fullName;
    static String[] name, emails, passwords, grades, points;
	static String fileName = "accounts.txt";
	static Student curUser;
    public static void main(String[] args) throws IOException {
    	in = new BufferedReader(new FileReader(fileName));
    	name = new String[2*MAX];
    	emails = new String[MAX];
    	passwords = new String[MAX];
    	grades = new String[MAX];
    	points = new String[MAX];
    	name = in.readLine().split(" ");
    	emails = in.readLine().split(" ");
    	passwords = in.readLine().split(" ");
    	grades = in.readLine().split(" ");
    	points = in.readLine().split(" ");
    	numOfUsers = points.length;
    	for(int i = 0, n = 0; i < numOfUsers; i++) {
    		fullName = name[n] + " " + name[n+1]; n+=2;
    		Student.getStudents().add(new Student(fullName, emails[i], passwords[i], Integer.parseInt(grades[i]), Integer.parseInt(points[i])));
    	}
    	in.close();
    	
    	
    	// Set the look and feel of the GUI.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			System.err.println("Unsupported operating system.");
		}

		
		try {
			// Load data files
			EventsDataFile eventsDataFile = new EventsDataFile("events.txt");
	  
		  } catch (IOException ex) {
			System.err.println("Failed to load data: " + ex.getMessage());
		  }
		
	
        // Create and open the title GUI.
		EventQueue.invokeLater(TitleFrame::new);
    }

    public static Frame getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(Frame newMainFrame) {
		mainFrame = newMainFrame;
	}
}
