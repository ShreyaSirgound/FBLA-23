import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//TODO: create login page
public class LoginPage {
    public LoginPage() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));

        JPanel panel = new JPanel();
        JButton newAccount = new JButton("Create an account");
        newAccount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        newAccount.setBounds(502, 360, 275, 75);
        newAccount.addActionListener(e -> {
            new AccountSetup();
            frame.dispose();
        });
        panel.add(newAccount);
        frame.add(panel);

        frame.setVisible(true);
    }
}
