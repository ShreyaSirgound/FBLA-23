import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UnsupportedLookAndFeelException;

//TODO: create homepage layout
public class MainFrame {
    public MainFrame() {
        JFrame frame = new JFrame("HomePage");
        setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

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
