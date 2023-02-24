import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UnsupportedLookAndFeelException;

//TODO: create homepage layout
public class MainFrame {
	JButton button;
	
    public MainFrame() {
        JFrame frame = new JFrame("Home Page");
        setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        
        JButton button = new JButton("Events");
        button.setBounds(240, 460, 160, 40);
        //button.addActionListener(this);
        button.setBackground(Color.LIGHT_GRAY);
        frame.add(button);
        
        JButton button2 = new JButton("History");
        button2.setBounds(40, 60, 160, 40);
        button2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        button2.setBackground(Color.LIGHT_GRAY);
        frame.add(button2);
        button2.addActionListener(e -> {
            new Events();
            frame.dispose();
        });
        
        JButton button3 = new JButton("Points");
        button3.setBounds(40, 60, 160, 40);
        button3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        button3.setBackground(Color.LIGHT_GRAY);
        frame.add(button3);
        button3.addActionListener(e -> {
            new Events();
            frame.dispose();
        });
        
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
