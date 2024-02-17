import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SearchRecords {
    DefaultTableModel model;
    JTable table;
    TableRowSorter<TableModel> rowSorter;
    JScrollPane sp;

    public SearchRecords() {
    //setup the frame
        JFrame frame = new JFrame("Data Records");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.white);

        //titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        titlebar.add(Common.getImage("logo_small.png"), BorderLayout.EAST);
        frame.add(titlebar);

        //sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 60, 300, 720);
        //button to switch to admin view (this feature is only accessible for admins)
		JButton adminView = new JButton("Access Admin View");
		adminView.setBounds(50, 400, 180, 75);
		adminView.addActionListener(e -> {
			try {
				new AdminView();
				frame.dispose();
			} catch (IOException | ClassNotFoundException | NullPointerException e1) {
				e1.printStackTrace();
			}
		});
        sidebar.add(adminView);
        //button to switch to student view (this feature is only accessible for admins)
		JButton mainMenu = new JButton("Access Student View");
		mainMenu.setBounds(50, 500, 180, 75);
		mainMenu.addActionListener(e -> {
			try {
				new MainFrame();
				frame.dispose();
			} catch (IOException | ClassNotFoundException | NullPointerException e1) {
				e1.printStackTrace();
			}
		});
        sidebar.add(mainMenu);
        frame.add(sidebar);

        JLabel title1 = new JLabel("Data Records");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(325, 70, 250, 50);
        frame.add(title1);

        //data to be displayed in the JTable
        String[][] data = new String[Student.getStudents().size()][5]; //2D array to hold information on all active students
        int studentNum = 0;
        for(Student s : Student.getStudents()){
            data[studentNum][0] = String.valueOf(studentNum);
            data[studentNum][1] = s.getName();
            data[studentNum][2] = s.getEmail();
            data[studentNum][3] = String.valueOf(s.getGrade());
            data[studentNum][4] = String.valueOf(s.getPoints());
            studentNum++;
        }
        String[] columnNames = {"Student #", "Name", "Email", "Grade", "Points"}; //array to hold column names of the data table
       
        // initializing the data table
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowHeight(25);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        sp = new JScrollPane(table);
        sp.setBounds(320, 170, 900, 500);
        frame.add(sp);

        //creating the search filter feature
        JLabel lbl = new JLabel("Enter Search:");
        lbl.setBackground(Color.white);
        lbl.setForeground(Color.gray);
        lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        lbl.setBounds(355, 120, 150, 50);
        JTextField tf = new JTextField();
        tf.setEditable(true);
        tf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tf.setBounds(480, 125, 400, 35);
        frame.add(lbl);
        frame.add(tf);

        //dynamically searching the table
        table.setRowSorter(rowSorter);
        tf.getDocument().addDocumentListener(new DocumentListener(){

            //if user enters a search entry
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tf.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            //if user deletes a character sequence from their search entry
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tf.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            //if user changes a character sequence from their search entry
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        frame.setVisible(true);
    }
}
