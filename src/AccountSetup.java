import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AccountSetup {
    //create user class to store all users, student info will be put into student objects from there
	List<Student> studentsList = Student.getStudents();
    List<Admin> adminsList = Admin.getAdmins();
	String auth, name, grade, email;
	String enteredPassword = "";
	char[] p;
    public AccountSetup() {
        System.out.println();
        for(Student s : studentsList){
            System.out.println(Student.toString(s));
        }

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

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(400, 120, 465, 540);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 25));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //input fields
        //authority input
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(Color.white);
        JLabel label1 = new JLabel("Are you an administrator or a student?", SwingConstants.RIGHT);
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label1.setBackground(Color.decode("#76BEE8"));
        label1.setForeground(Color.gray);
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        label1.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        String roles[] = {"", "Student", "Administrator"};
        JComboBox<String> authority = new JComboBox(roles);
        panel1.add(label1);
        panel1.add(authority);
        mainPanel.add(panel1);

        //name input
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Color.white);
        JLabel label2 = new JLabel("What is your name? (First Last)", SwingConstants.RIGHT);
        label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label2.setBackground(Color.decode("#76BEE8"));
        label2.setForeground(Color.gray);
        label2.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        JTextField input2 = new JTextField();
        
        input2.setColumns(30);
        panel2.add(label2);
        panel2.add(input2);
        mainPanel.add(panel2);

        //grade input
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBackground(Color.white);
        JLabel label3 = new JLabel("What grade are you in?", SwingConstants.RIGHT);
        label3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label3.setBackground(Color.decode("#76BEE8"));
        label3.setForeground(Color.gray);
        label3.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        String grades[] = {"", "9", "10", "11", "12", "N/A"};
        JComboBox<String> gradeDropdown = new JComboBox(grades);
        
        panel3.add(label3);
        panel3.add(gradeDropdown);
        panel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(panel3);

        //email input
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.setBackground(Color.white);
        JLabel label4 = new JLabel("Enter your school email:", SwingConstants.RIGHT);
        
        label4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label4.setBackground(Color.decode("#76BEE8"));
        label4.setForeground(Color.gray);
        label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        JTextField studentNumber = new JTextField();
        studentNumber.setColumns(30);
        panel4.add(label4);
        panel4.add(studentNumber);
        mainPanel.add(panel4);

        //password input
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel5.setBackground(Color.white);
        JLabel label5 = new JLabel("Enter your password:", SwingConstants.RIGHT);
        label5.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label5.setBackground(Color.decode("#76BEE8"));
        label5.setForeground(Color.gray);
        label5.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        JPasswordField password = new JPasswordField(30);
        password.setEchoChar('*');
        panel5.add(label5);
        panel5.add(password);
        mainPanel.add(panel5);

        JButton createAcc = new JButton("Create my account");
        createAcc.setBackground(Color.decode("#76BEE8"));
        createAcc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        createAcc.setPreferredSize(new Dimension(435,45));
        createAcc.addActionListener(e -> {
        	String auth = authority.getSelectedItem().toString();
        	name = input2.getText();
        	grade = gradeDropdown.getSelectedItem().toString();
        	email = studentNumber.getText();
        	p = password.getPassword();
        	enteredPassword = "";
        	for(int i = 0; i < p.length; i++) {
            	enteredPassword += p[i];
            }
        	enteredPassword.trim();

            try {
                if(auth == "Student"){
                    Student newStudent = new Student(name, email, enteredPassword, Integer.parseInt(grade), 0);
                    System.out.println(studentsList.contains(newStudent) + " auth1 good");
                    if(studentsList.contains(newStudent) == false){
                        System.out.println("first 1");
                        MainFrame.curUser = newStudent;
                        Student.addStudent(MainFrame.curUser);
                        MainFrame.saveUser();
                    } else {
                        System.out.println("first 2");
                        JOptionPane.showConfirmDialog(frame, "  An account with this data has already been created. Please log in to use Schoolsync services. ", 
								                    "Duplicate Input Error",
															JOptionPane.OK_CANCEL_OPTION);
                        new LoginPage();
                        frame.dispose();
                    }
                    
                } else if (auth == "Administrator"){
                    Admin newAdmin = new Admin(name, email, enteredPassword);
                    System.out.println(adminsList.contains(newAdmin) + " auth2 good");
                    if(adminsList.contains(newAdmin) == false){
                        System.out.println("second 1");
                        AdminView.curUser = newAdmin;
                        Admin.addAdmin(AdminView.curUser);
                        AdminView.saveUser();
                    } else {
                        System.out.println("second 2");
                        JOptionPane.showConfirmDialog(frame, "  An account with this data has already been created. Please log in to use Schoolsync services. ", 
								                    "Duplicate Input Error",
															JOptionPane.OK_CANCEL_OPTION);
                        new LoginPage();
                        frame.dispose();
                    }
                }
            } catch (IOException e1) {
				e1.printStackTrace();
            }
            
			new LoginPage();

            frame.dispose();
        });
        mainPanel.add(createAcc);

        frame.add(mainPanel);

        frame.getRootPane().setDefaultButton(createAcc);
        frame.setVisible(true);
    }
}
