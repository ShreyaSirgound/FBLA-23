import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TitleFrame {
    public TitleFrame() {
        JFrame frame = new JFrame("SchoolSync (v" + Common.VERSION + ")");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));

        //App logo
        //TODO: create and add a better logo
        JPanel logoPanel = new JPanel();
        logoPanel.add(Common.getImage("logo.png"));
        logoPanel.setBounds(270, 180, 760, 155);
        frame.add(logoPanel);

        //Developers
        final JPanel infoPanel = new JPanel();
        final JLabel infoLabel = new JLabel("<html>" 
        + "<p> Developers: Ian Tang, Shreya Sirgound, Nischae Tiwari</p>"
        + "</html>");
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        infoLabel.setForeground(Color.white);
        infoPanel.add(infoLabel);
        infoPanel.setBounds(0, 615, 500, 90);
        infoPanel.setBackground(Color.decode("#76BEE8"));
        frame.add(infoPanel);

        // Version information.
		JPanel versionPanel = new JPanel();
		JLabel versionLabel = new JLabel("Version " + Common.VERSION);
		versionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		versionLabel.setForeground(Color.white);
        versionPanel.add(versionLabel);
		versionPanel.setBounds(1010, 615, 250, 30);
		versionPanel.setBackground(Color.decode("#76BEE8"));
		frame.add(versionPanel);

        //Start button
        JButton button = new JButton("Start");
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        button.setBounds(502, 360, 275, 75);
        button.addActionListener(e -> {
            new LoginPage();
            frame.dispose();
        });
        frame.add(button);

        frame.setVisible(true);
    }
}