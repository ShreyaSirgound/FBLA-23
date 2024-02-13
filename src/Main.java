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
    static String[] names, emails, passwords, grades, points;
	static String fileName1 = "C:\\Users\\Shreya S\\Documents\\GitHub\\FBLACP\\src\\data\\admins.txt"; //"accounts.txt";
	static String fileName2 = "C:\\Users\\Shreya S\\Documents\\GitHub\\FBLACP\\src\\data\\students.txt";
	static Student curUser;
    public static void main(String[] args) throws IOException {
    	names = new String[2*MAX];
    	emails = new String[MAX];
    	passwords = new String[MAX];
    	grades = new String[MAX];
    	points = new String[MAX]; 

		//reads in all existing administrator accounts
		in = new BufferedReader(new FileReader(fileName1));
		names = in.readLine().split(" ");
		System.out.println(names + "*");
    	emails = in.readLine().split(" ");
		System.out.println(emails + "**");
    	passwords = in.readLine().split(" ");
		System.out.println(passwords + "***");
		numOfUsers = emails.length;
		for(int i = 0, n = 0; i < numOfUsers; i++) {
			//System.out.println(emails.length);
			if(n < names.length){
				//System.out.println(n);
				fullName = names[n] + " " + names[n+1];
				//System.out.println(fullName);
				n+=2;
				Admin.addAdmin(new Admin(fullName, emails[i], passwords[i]));
			}
    	}
    	in.close();

		//reads in all existing student accounts
		in = new BufferedReader(new FileReader(fileName2));

		names = in.readLine().split(" ");
		System.out.println(names);
    	emails = in.readLine().split(" ");
		System.out.println(emails);
    	passwords = in.readLine().split(" ");
		System.out.println(passwords);
    	grades = in.readLine().split(" ");
		System.out.println(grades);
    	points = in.readLine().split(" ");
		System.out.println(points);
    	for(int i = 0, n = 0; i < numOfUsers; i++) {
			if(n < names.length){
				fullName = names[n] + " " + names[n+1]; 
				n+=2;
				Student.getStudents().add(new Student(fullName, emails[i], passwords[i], Integer.parseInt(grades[i]), Integer.parseInt(points[i])));
			}
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
