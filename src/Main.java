import java.awt.EventQueue;
import java.awt.Frame;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    private static Frame mainFrame;
    public static void main(String[] args) {
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
