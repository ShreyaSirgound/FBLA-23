import java.awt.EventQueue;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
    static String[] names, emails, passwords, grades, points, salts;
	static String fileName1 = "data\\admins.txt";
	static String fileName2 = "data\\students.txt";
	static Student curUser;
    public static void main(String[] args) throws IOException {
    	names = new String[2*MAX];
    	emails = new String[MAX];
    	passwords = new String[MAX];
    	grades = new String[MAX];
    	points = new String[MAX]; 
		salts = new String[MAX];

		//reads in all existing administrator accounts
		try {
			in = new BufferedReader(new FileReader("data\\admins.txt"));
			names = in.readLine().split(" ");
			System.out.println(names + "*");
			emails = in.readLine().split(" ");
			System.out.println(emails + "**");
			passwords = in.readLine().split(" ");
			System.out.println(passwords + "***");
			salts = in.readLine().split(" ");
			numOfUsers = emails.length;
			for(int i = 0, n = 0; i < numOfUsers; i++) {
				if(n < names.length){
					fullName = names[n] + " " + names[n+1];
					n+=2;

					byte[] uSalt = new byte[(int) (new File("data\\salts\\adminSalts.txt").length())];
					try (FileInputStream in = new FileInputStream("data\\salts\\adminSalts.txt")) {
						in.read(uSalt);
					}
					
					Admin.addAdmin(new Admin(fullName, emails[i], passwords[i], uSalt));
				}
			}
			in.close();
			for(Admin s : Admin.getAdmins()){
				System.out.println(Admin.toString(s));
			}
		} catch (NullPointerException e){
			e.printStackTrace();
		}

		//reads in all existing student accounts
		try {
			in = new BufferedReader(new FileReader("data\\students.txt"));
			/**names = new String[2*MAX];
			emails = new String[MAX];
			passwords = new String[MAX];
			grades = new String[MAX];
			points = new String[MAX];*/
			names = in.readLine().split(" ");
			emails = in.readLine().split(" ");
			passwords = in.readLine().split(" ");
			grades = in.readLine().split(" ");
			points = in.readLine().split(" ");
			salts = in.readLine().split(" ");
			numOfUsers = points.length;
			for(int i = 0, n = 0; i < numOfUsers; i++) {
				fullName = names[n] + " " + names[n+1]; 
				n+=2;

				byte[] uSalt = new byte[(int) (new File("data\\salts\\studentSalts.txt").length())];
				try (FileInputStream in = new FileInputStream("data\\salts\\studentSalts.txt")) {
					in.read(uSalt);
				}

				Student s = new Student(fullName, emails[i], passwords[i], Integer.parseInt(grades[i]), Integer.parseInt(points[i]), uSalt);
				Student.addStudent(s);
				if(grades[i].equals("9") && (Student.getNineStudents().contains(s) == false)){
					Student.addNineStudent(s);
				} else if(grades[i].equals("10") && (Student.getTenStudents().contains(s) == false)){
					Student.addTenStudent(s);
				} else if(grades[i].equals("11") && (Student.getElevenStudents().contains(s) == false)){
					Student.addElevenStudent(s);
				} else if (grades[i].equals("12") && (Student.getTwelveStudents().contains(s) == false)){
					Student.addTwelveStudent(s);
				}
			}
			in.close();
			for(Student s : Student.getStudents()){
				System.out.println(Student.toString(s));
			}
		} catch (NullPointerException e){
			e.printStackTrace();
		}
    	
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
