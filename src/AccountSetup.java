import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AccountSetup {
    //TODO: backend for setting up an account (student/admin, personal info)
    //create user class to store all users, student info will be put into student objects from there
   
    public AccountSetup() {
        JFrame frame = new JFrame("Create an account");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Let's get to know you a bit");
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
        headerPanel.add(headerLabel);
        headerPanel.setBounds(385, 50, 500, 60);
        headerPanel.setBackground(Color.decode("#76BEE8"));
        frame.add(headerPanel);

        //input fields
        //authority input
        JPanel panel1 = new JPanel();
        panel1.setBounds(425, 135, 400, 60);
        panel1.setBackground(Color.decode("#76BEE8"));
        JLabel label1 = new JLabel("Are you an administrator or a student?", SwingConstants.RIGHT);
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label1.setBackground(Color.decode("#76BEE8"));
        label1.setForeground(Color.white);
        panel1.add(label1);
        JTextField input1 = new JTextField();
        input1.setColumns(30);
        panel1.add(input1);
        frame.add(panel1);

        //name input
        JPanel panel2 = new JPanel();
        panel2.setBounds(425, 225, 400, 60);
        panel2.setBackground(Color.decode("#76BEE8"));
        JLabel label2 = new JLabel("What is your name? (First Last)", SwingConstants.RIGHT);
        label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label2.setBackground(Color.decode("#76BEE8"));
        label2.setForeground(Color.white);
        panel2.add(label2);
        JTextField input2 = new JTextField();
        input2.setColumns(30);
        panel2.add(input2);
        frame.add(panel2);

        //grade input
        JPanel panel3 = new JPanel();
        panel3.setBounds(425, 315, 400, 60);
        panel3.setBackground(Color.decode("#76BEE8"));
        JLabel label3 = new JLabel("What grade are you in?", SwingConstants.RIGHT);
        label3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label3.setBackground(Color.decode("#76BEE8"));
        label3.setForeground(Color.white);
        panel3.add(label3);
        String grades[] = {"", "9", "10", "11", "12"};
        JComboBox<String> gradeDropdown = new JComboBox(grades);
        panel3.add(gradeDropdown);
        frame.add(panel3);

        //email input
        JPanel panel4 = new JPanel();
        panel4.setBounds(425, 405, 400, 60);
        panel4.setBackground(Color.decode("#76BEE8"));
        JLabel label4 = new JLabel("Enter your school email:", SwingConstants.RIGHT);
        label4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label4.setBackground(Color.decode("#76BEE8"));
        label4.setForeground(Color.white);
        panel4.add(label4);
        JTextField studentNumber = new JTextField();
        studentNumber.setColumns(30);
        panel4.add(studentNumber);
        frame.add(panel4);

        //password input
        JPanel panel5 = new JPanel();
        panel5.setBounds(425, 495, 400, 60);
        panel5.setBackground(Color.decode("#76BEE8"));
        JLabel label5 = new JLabel("Enter your password:", SwingConstants.RIGHT);
        label5.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label5.setBackground(Color.decode("#76BEE8"));
        label5.setForeground(Color.white);
        panel5.add(label5);
        JPasswordField password = new JPasswordField(30);
        password.setEchoChar('*');
        char[] enteredPassword = password.getPassword();
        panel5.add(password);
        frame.add(panel5);

        JButton submit = new JButton("Submit"); //530
        submit.setBounds(1000, 550, 165, 25);
        submit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        submit.addActionListener(e -> {
            new MainFrame();
            frame.dispose();
        });
        frame.add(submit);

        frame.setVisible(true);
    }
}
