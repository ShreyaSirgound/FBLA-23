import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginPage {
	char[] enteredPassword;
	String curPassword = "";
	String curUsername = "";
	JPasswordField password;
	JTextField username;
    String auth;

    public LoginPage() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));
        
        JLabel headerLabel = new JLabel("Login to get started!");
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
        headerLabel.setBounds(465, 50, 350, 60);
        frame.add(headerLabel);

        //login input area
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(460, 150, 350, 330);

        //username input
        JPanel filler = new JPanel();
        filler.setPreferredSize(new Dimension(350, 25));
        filler.setBackground(Color.white);
        loginPanel.add(filler);
        JLabel userDesc = new JLabel("Username");
        userDesc.setForeground(Color.gray);
        userDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loginPanel.add(userDesc);
        username = new JTextField(21);
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        loginPanel.add(username);

        JPanel filler2 = new JPanel();
        filler2.setPreferredSize(new Dimension(350, 10));
        filler2.setBackground(Color.white);
        loginPanel.add(filler2);

        //password input
        JLabel passwordDesc = new JLabel("Password");
        passwordDesc.setForeground(Color.gray);
        passwordDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loginPanel.add(passwordDesc);

        password = new JPasswordField(21);
        password.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        password.setEchoChar('*');
        loginPanel.add(password);

        JPanel filler3 = new JPanel();
        filler3.setPreferredSize(new Dimension(350, 10));
        filler3.setBackground(Color.white);
        loginPanel.add(filler3);

        //login button
        JButton login = new JButton("Login");
        login.setBackground(Color.decode("#76BEE8"));
        login.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        login.setPreferredSize(new Dimension(340,45));

        login.addActionListener(e -> {
            try {
            	if(validate()) {
                    if (auth == "student"){
                        new MainFrame();
                    } else if (auth == "admin"){
                        new AdminView();
                    }
            	} else {
            		new LoginPage();
            	}
			} catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e1) {
				e1.printStackTrace();
            }
            frame.dispose();
        });
        loginPanel.add(login);

        JTextField option = new JTextField("or");
        option.setEditable(false);
        option.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        option.setBackground(Color.white);
        option.setForeground(Color.GRAY);
        option.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        option.setPreferredSize(new Dimension(340,25));
        option.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(option);

        JButton newAccount = new JButton("Create an account");
        newAccount.setBackground(Color.decode("#76BEE8"));
        newAccount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        newAccount.setPreferredSize(new Dimension(340,45));
        newAccount.addActionListener(e -> {
            new AccountSetup();
            frame.dispose();
        });
        loginPanel.add(newAccount);
        

        frame.add(loginPanel);
        
        //THIS IS A TEMP BUTTON TO GET TO ADMIN VIEW
        JButton adminView = new JButton("AdminView");
        adminView.setBounds(900, 600, 100, 60);
        adminView.addActionListener(e -> {
            try {
				new AdminView();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
            frame.dispose();
        });
        frame.add(adminView);

        frame.getRootPane().setDefaultButton(login);
        frame.setVisible(true);
        
    }
    	public boolean validate() throws NoSuchAlgorithmException {
            System.out.println("validating");
            curUsername = username.getText().toLowerCase();
    		enteredPassword = password.getPassword();
            curPassword = new String(enteredPassword);
            curPassword.trim();
            
    		System.out.println(curUsername);
    		System.out.println(curPassword + " now");
    		for(Student s : Student.getStudents()) {
    			if(s.getName().toLowerCase().equals(curUsername)){
                    if(s.getPassword().equals(Common.hashPassword(curPassword))){
                        auth = "student";
                        MainFrame.curUser = s;
                        System.out.println("yes student");
                        return true;
                    }
                }
    		}
            for(Admin a : Admin.getAdmins()) {
    			System.out.print(a.getName() + " ");
    			System.out.println(a.getPassword() + " org");
    			if(a.getName().toLowerCase().equals(curUsername)){
                    if(Common.comparePassword(curPassword, Common.hashPassword(curPassword))){
                        auth = "admin";
                    AdminView.curUser = a;
                    System.out.println("yes admin");
    				return true;
                    }
                }
    		}
            curUsername = "";
            curPassword = "";
    		return false;
        }
    
}
