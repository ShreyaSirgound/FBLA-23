import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TitleFrame {
    public TitleFrame() {
        JFrame frame = new JFrame("SchoolSync (v" + Main.VERSION + ")");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.blue);

        //App logo
        //TODO: create and add logo image
        JPanel logoPanel = new JPanel();
        logoPanel.add(Common.getImage("logo.png"));
        logoPanel.setBounds(390, 190, 500, 100);
        frame.add(logoPanel);

        //Developers
        final JPanel infoPanel = new JPanel();
        final JLabel infoLabel = new JLabel("<html>" 
        + "<p> Developers: Ian Tang, Shreya Sirgound, Nischae Tiwari</p>"
        + "</html>");
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        infoPanel.add(infoLabel);
        infoPanel.setBounds(0, 595, 380, 90);
        infoPanel.setBackground(Color.blue);
        frame.add(infoPanel);

        //Start button
        JButton button = new JButton("Start");
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        button.setBounds(502, 360, 275, 75);
        button.addActionListener(e -> {
            new MainFrame();
            frame.dispose();
        });
        frame.add(button);

        frame.setVisible(true);
    }
}