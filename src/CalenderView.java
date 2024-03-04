import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * The panel displaying reminders in a calendar.
 */
public class CalenderView {

  private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"};

  private static final String[] DAYS_OF_WEEK = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

  private final LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

  /**
   * The calendar header label, containing month and year information.
   */
  private final JLabel headerLabel;

  /**
   * The button to return to the previous month.
   */
  private final JButton previousButton;

  /**
   * The button to advance to the next month.
   */
  private final JButton nextButton;

  /**
   * The table model for the calendar.
   */
  private final DefaultTableModel tableModel;

  /**
   * The actual calendar component.
   */
  private final JTable table;

  /**
   * The current month being viewed.
   */
  private int month;

  /**
   * The current year being viewed.
   */
  private int year;

  /**
   * The authority of the user accessing the CalenderView
   */
  private static String auth;

  /**
   * The set of colors to be used within the calender events.
   */
  private Color[] colors = {Color.decode("#6EA6D0"), Color.decode("#DCC4E7"), Color.decode("#A6D59D")};
  private int colorCount = 0;

  public CalenderView(String auth) {
    this.auth = auth;
    
    //setup the frame
    JFrame frame = new JFrame("CalenderView");
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

    if(auth.equals("admin")){
      //button to switch to admin view (this feature is only accessible for admins)
      JButton adminView = new JButton("Access Admin View");
      adminView.setBounds(50, 300, 180, 75);
      adminView.addActionListener(e -> {
          try {
              new AdminView();
              frame.dispose();
          } catch (IOException | ClassNotFoundException | NullPointerException e1) {
              e1.printStackTrace();
          }
      });
      sidebar.add(adminView);
    }
    //button to switch to student view (this feature is only accessible for admins)
    JButton mainMenu = new JButton();
    if(auth.equals("admin")){
      mainMenu.setText("Access Student View");
    } else if (auth.equals("student")){
      mainMenu.setText("Homepage");
    }
    mainMenu.setBounds(50, 100, 180, 75);
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

    JLabel title1 = new JLabel("Calender View");
    title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
    title1.setForeground(Color.gray);
    title1.setBounds(310, 70, 250, 50);
    frame.add(title1);

    //the panel to hold the calender
    JPanel mainPanel = new JPanel();
    mainPanel.setBounds(310, 115, 945, 560);
    mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    mainPanel.setLayout(new BorderLayout());
 
    JPanel header = new JPanel(new BorderLayout());
    header.setBorder(new EmptyBorder(10, 5, 5, 5));
    header.setBackground(Color.decode("#1A2371"));

    this.headerLabel = new JLabel();
    headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    headerLabel.setForeground(Color.WHITE);
    headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

    this.previousButton = new JButton("<<");
    this.nextButton = new JButton(">>");

    // Allows moving between months.
    previousButton.addActionListener(e -> {
      if (month == 0) {
        // Go back by 1 year.
        month = 11;
        year -= 1;
      } else {
        // Go back by 1 month.
        month -= 1;
      }

      refresh();
    });

    nextButton.addActionListener(e -> {
      if (month == 11) {
        // Go forwards by 1 year.
        month = 0;
        year += 1;
      } else {
        // Go forwards by 1 month.
        month += 1;
      }

      refresh();
    });

    header.add(previousButton, BorderLayout.LINE_START);
    header.add(headerLabel, BorderLayout.CENTER);
    header.add(nextButton, BorderLayout.LINE_END);

    this.tableModel = new DefaultTableModel();

    // Create a new table with the custom model.
    this.table = new JTable(tableModel);

    for (String dayOfWeek : DAYS_OF_WEEK) {
      tableModel.addColumn(dayOfWeek);
    }

    // Set rows and columns.
    table.setRowHeight(96);
    tableModel.setColumnCount(7);
    tableModel.setRowCount(6);

    mainPanel.add(header, BorderLayout.NORTH);
    mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

    this.month = now.getMonthValue() - 1;
    this.year = now.getYear();

    // Fill the calendar.
    refresh();

  
    frame.add(mainPanel);
    frame.setVisible(true);
  }

  //@Override
  public void refresh() {

    GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int startOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

    // Disable the "previous" button if the year is more than a year from now.
    previousButton.setEnabled(!(month == 0 && year < now.getYear() - 1));
    // Disable the "next" button if the year is more than 5 years from now.
    nextButton.setEnabled(!(month == 11 && year >= now.getYear() + 5));

    // Refresh the header label.
    headerLabel.setText(MONTHS[month] + " - " + year);
    headerLabel.setBackground(Color.decode("#1A2371"));
    headerLabel.setOpaque(true);

    // Clear the current table.
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        tableModel.setValueAt(null, i, j);
      }
    }

    // Add updated calendar dates.
    for (int i = 1; i <= daysInMonth; i++) {
      int row = (i + startOfMonth - 2) / 7;
      int column = (i + startOfMonth - 2) % 7;
      tableModel.setValueAt(" " + i, row, column);
    }

    // Apply the custom renderer.
    table.setDefaultRenderer(table.getColumnClass(0), new CalendarRenderer());
    table.setDefaultEditor(table.getColumnClass(0), new CalendarEditor());
  }

  /**
   * The custom table cell renderer to change the display of table cells.
   */
  private class CalendarRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocused, int row, int column) {
      super.getTableCellRendererComponent(table, value, isSelected, isFocused, row, column);

      List<Event> dayEvents = new ArrayList<Event>();
      setBorder(null);
      setForeground(Color.BLACK);

      if (value == null) {
        // the "day" is outside the current month.
        setBackground(Color.LIGHT_GRAY);
        return this;
      }

      JPanel panel = new JPanel(new BorderLayout(0, 0));
      int day = Integer.parseInt(((String) value).trim());
      String m = (MONTHS[month]).toUpperCase();


      // The label displaying the current day.
      JLabel dayLabel = new JLabel(" " + day);
      dayLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
      dayLabel.setOpaque(true);

      // The panel containing all events for the day.
      JPanel eventsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

      // Get all events with a date between the start and end of the day
      for(Event e : Event.getEvents()){
        String[] eventDate = e.getDate().split(",");
        int evDay = Integer.parseInt(eventDate[0].split(" ")[1]);
        String evMonth = eventDate[0].split(" ")[0].toUpperCase();
        int evYear = Integer.parseInt(eventDate[1].trim());

        if(evDay == day && evMonth.equals(m) && evYear == year) {
          dayEvents.add(e);
        }
      }

      // Add a label for each event. Maximum of three (others won't fit).
      dayEvents.stream().limit(3).forEachOrdered(event -> eventsPanel.add(eventLabel(event)));

      // Add a note saying there are more reminders if the number exceeds three.
      if (dayEvents.size() > 3) {
        JLabel moreLabel = new JLabel("...and " + (dayEvents.size() - 3) + " more");
        moreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        moreLabel.setPreferredSize(new Dimension(165, 18));
        moreLabel.setForeground(Color.DARK_GRAY);
        eventsPanel.add(moreLabel);
      }

      //highlights the calender cell only if it shows the current date
      if(day == now.getDayOfMonth() && MONTHS[month].toUpperCase().equals(String.valueOf(now.getMonth()).trim()) && year == now.getYear()){
        dayLabel.setBackground(Color.decode("#DFF1F3"));
        eventsPanel.setBackground(Color.decode("#DFF1F3"));
      } else {
        dayLabel.setBackground(Color.WHITE);
        eventsPanel.setBackground(Color.WHITE);
      }

      panel.add(dayLabel, BorderLayout.PAGE_START);
      panel.add(eventsPanel, BorderLayout.CENTER);

      return panel;
    }

    //Creates a JLabel for the given event
    private JLabel eventLabel(Event e) {
      JLabel label = new JLabel(" " + e.getName());
      label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
      label.setPreferredSize(new Dimension(120, 18));
      label.setForeground(Color.BLACK);
      label.setBackground(colors[colorCount]);
      if(colorCount == 2){
        colorCount = 0;
      } else {
        colorCount++;
      }
      label.setOpaque(true);
      return label;
    }
  }

  /**
   * The custom table cell editor to change the action on click.
   */
  private class CalendarEditor implements TableCellEditor {

    //@Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
        int row, int column) {

      if (value == null) {
        return null;
      }

      // Open a custom "cell editor" instead of Java's default.
      new DayView(Integer.parseInt(((String) value).trim()));
      return null;
    }

    //@Override
    public Object getCellEditorValue() {
      return "";
    }

    //@Override
    public boolean isCellEditable(EventObject anEvent) {
      return true;
    }

    //@Override
    public boolean shouldSelectCell(EventObject anEvent) {
      return false;
    }

    //@Override
    public boolean stopCellEditing() {
      return true;
    }

    //@Override
    public void cancelCellEditing() {
    }

    //@Override
    public void addCellEditorListener(CellEditorListener l) {
    }

    //@Override
    public void removeCellEditorListener(CellEditorListener l) {
    }
  }

  /**
   * The reminders view displaying only the reminders for a specific day. Opened when the day is
   * clicked on in the calendar.
   */
  public class DayView {

    /**
     * The day of the month that is being viewed.
     */
    private final int day;

    /**
     * The date being viewed, in milliseconds.
     */
    private final long dateMillis;

    /**
     * The dialog of the view.
     */
    private final JDialog dialog;

    public DayView(int day) {
      this.day = day;
      this.dateMillis = LocalDate.of(year, month + 1, day).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

      this.dialog = new JDialog(Main.getMainFrame(), MONTHS[month] + " " + day + ", " + year, true);
      dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      dialog.setResizable(false);
      dialog.setSize(510, 575);
      dialog.setLocationRelativeTo(null);

      JPanel allEvents = new JPanel();
      allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
      allEvents.setBackground(Color.white);
      allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
      JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      eventsPane.setBounds(320, 110, 490, 555);
      eventsPane.getVerticalScrollBar().setUnitIncrement(15);

      //gets all events with the same date as the calender cell
      List<Event> dayEvents = new ArrayList<Event>();
      for(Event e : Event.getEvents()){
        String[] eventDate = e.getDate().split(",");
        int evDay = Integer.parseInt(eventDate[0].split(" ")[1]);
        String evMonth = eventDate[0].split(" ")[0];
        int evYear = Integer.parseInt(eventDate[1].trim());

        if(evDay == day && evMonth.equals(MONTHS[month]) && evYear == year) {
          dayEvents.add(e);
        }
      }
      
      //gets information from each event for that day and adds it all to a single panel
      for (int i = 0; i < dayEvents.size(); i++) {
          //creates a mini panel to hold all the info about a specific event
          JPanel eventInfoPane = new JPanel();
          eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
          eventInfoPane.setBackground(Color.white);
          eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
          eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
          eventInfoPane.setMaximumSize(new Dimension(480, 245));

          //holds event points
          JTextField eventPointsInfo = new JTextField("WORTH " + dayEvents.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
          eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
          eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          eventPointsInfo.setEditable(false);
          eventPointsInfo.setBackground(Color.decode("#76BEE8"));
          eventPointsInfo.setForeground(Color.white);
          eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

          //holds event name
          JTextField eventNameInfo = new JTextField(dayEvents.get(i).getName()); //gets the event name
          eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
          eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          eventNameInfo.setEditable(false);
          eventNameInfo.setBackground(Color.white);
          eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
          
          //holds the event description
          JTextArea eventDescInfo = new JTextArea(dayEvents.get(i).getDesc());
          eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
          eventDescInfo.setForeground(Color.gray);
          eventDescInfo.setEditable(false);
          eventDescInfo.setLineWrap(true);
          eventDescInfo.setWrapStyleWord(true);
          eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

          //holds the event location
          JTextField eventLocation = new JTextField(dayEvents.get(i).getLocation());
          eventLocation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
          eventLocation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          eventLocation.setEditable(false);
          eventLocation.setBackground(Color.white);
          eventLocation.setBorder(new EmptyBorder(0, 5, 1, 0));

          //holds the event date, and time
          JTextField eventSetting = new JTextField(dayEvents.get(i).getDate() //gets the event date
                                                  + "  @ " + dayEvents.get(i).getTime()); //gets the event time
          eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
          eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          eventSetting.setEditable(false);
          eventSetting.setBackground(Color.white);
          eventSetting.setBorder(new EmptyBorder(0, 5, 5, 0));

          JTextPane registerPane = new JTextPane();
          if(CalenderView.auth.equals("student")){
            //register for an event (only if the user is a student)
            JButton register = new JButton("Register"); 
            register.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            register.setBackground(Color.decode("#76BEE8"));
            register.setOpaque(true);
            register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            registerPane.insertComponent(register);
            registerPane.setBackground(Color.white);
            registerPane.setBorder(new EmptyBorder(0, 5, 8, 0));
            int idx = i;

            register.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(dialog, "Would you like to register for this event?", 
                                  "",
                                    JOptionPane.YES_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                  if(MainFrame.curUser == null){
                    JOptionPane.showMessageDialog(dialog, "Registration unsuccessful because the user is not a student.");
                  } else {
                    if(MainFrame.curUser.getMyEvents().contains(Event.eventList.get(idx))){
                        JOptionPane.showMessageDialog(dialog, "You have already registered for this event.");
                    } else {
                          int curPoints = MainFrame.curUser.getPoints();
                          curPoints += Event.eventList.get(idx).getPoints();
                          MainFrame.curUser.setPoints(curPoints);
                          MainFrame.curUser.addEvent(Event.eventList.get(idx));
                          try {
                              MainFrame.saveRegEvents();
                          } catch (IOException e1) {
                              e1.printStackTrace();
                          }
                          JOptionPane.showMessageDialog(dialog, "Successfully registered!");
                    }
                  }
                }
              }
            });
        }

        //panel that holds the buttons to edit/delete any event
        JPanel eventChange = new JPanel();
        if(CalenderView.auth.equals("admin")){
          int eventIndex = i; //the index at which the event is in the events list
      
          JButton edit = new JButton("Edit");
          edit.setBackground(Color.blue);
          edit.addActionListener(e -> {
            dialog.dispose();
            new EventEditPage(eventIndex);
          });

          JButton delete = new JButton("Delete");
          delete.setBackground(Color.red);

          delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to delete this event?", 
                                "",
                                  JOptionPane.YES_OPTION,
                                  JOptionPane.QUESTION_MESSAGE);
              if (result == JOptionPane.YES_OPTION) {
              Event.removeEvent(Event.getEvents().get(eventIndex)); //removes the event from the events list if the user selects YES
              }
            }
          });

          eventChange.add(delete, FlowLayout.LEFT);
          eventChange.add(edit, FlowLayout.LEFT);
        }

        eventInfoPane.add(eventPointsInfo);
        eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
        eventInfoPane.add(eventNameInfo);
        eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
        eventInfoPane.add(eventDescInfo);
        eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
        eventInfoPane.add(eventLocation);
        eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
        eventInfoPane.add(eventSetting);
        eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
        if(CalenderView.auth.equals("student")){
          eventInfoPane.add(registerPane);
        }
        else if(CalenderView.auth.equals("admin")){
          eventInfoPane.add(eventChange);
        }
        eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        allEvents.add(eventInfoPane);
        allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
        allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
      };
      dialog.add(eventsPane);

      dialog.setVisible(true);
    }

    //@Override
    public void refresh() {
      dialog.getContentPane().removeAll();
      dialog.revalidate();
      dialog.repaint();
    }
  }
}