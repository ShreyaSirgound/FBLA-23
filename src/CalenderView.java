import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.EventObject;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

  public CalenderView() {
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

    JLabel title1 = new JLabel("Calender View");
    title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
    title1.setForeground(Color.gray);
    title1.setBounds(325, 70, 250, 50);
    frame.add(title1);

    //the panel to hold the calender
    JPanel mainPanel = new JPanel();
    mainPanel.setBounds(325, 115, 920, 560);
    mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    mainPanel.setLayout(new BorderLayout());
 
    JPanel header = new JPanel(new BorderLayout());
    header.setBorder(new EmptyBorder(10, 5, 5, 5));

    this.headerLabel = new JLabel();
    headerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
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

  /**private List<Reminder> getRemindersFromDay(int targetYear, int targetMonth, int targetDay) {

    LocalDate targetDate = LocalDate.of(targetYear, targetMonth, targetDay);
    // The start of the day (12am).
    long min = targetDate.atStartOfDay(ZoneId.systemDefault())
        .toInstant().toEpochMilli();
    // The end of the day (11:59pm).
    long max = targetDate.atTime(23, 59).atZone(ZoneId.systemDefault())
        .toInstant().toEpochMilli();

    // Collect all reminders with a due date between the start and end of the day.
    return RemindersManager.getReminders(SortBy.DUE_DATE).stream()
        .filter(reminder -> reminder.getDueDate() >= min && reminder.getDueDate() <= max)
        .collect(Collectors.toList());
  }*/

  /**
   * The custom table cell renderer to change the display of table cells.
   */
  private class CalendarRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocused, int row, int column) {
      super.getTableCellRendererComponent(table, value, isSelected, isFocused, row, column);

      setBorder(null);
      setForeground(Color.BLACK);

      if (value == null) {
        // the "day" is outside the current month.
        setBackground(Color.LIGHT_GRAY);
        return this;
      }

      JPanel panel = new JPanel(new BorderLayout(0, 0));
      int day = Integer.parseInt(((String) value).trim());

      // The label displaying the current day.
      JLabel dayLabel = new JLabel(" " + day);
      dayLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
      dayLabel.setOpaque(true);

      // The panel containing all events for the day.
      JPanel eventsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

      // Collect all reminders with a due date between the start and end of the day.
      //List<Reminder> reminders = getRemindersFromDay(year, month + 1, day);

      // Add a label for each reminder. Maximum of 3 (others won't fit).
      /**reminders.stream().limit(3)
          .forEachOrdered(reminder -> tasksPanel.add(reminderLabel(reminder)));

      // Add a note saying there are more reminders if the number exceeds 3.
      if (reminders.size() > 3) {
        JLabel moreLabel = new JLabel("...and " + (reminders.size() - 3) + " more");
        moreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        moreLabel.setPreferredSize(new Dimension(165, 18));
        moreLabel.setForeground(Color.DARK_GRAY);
        tasksPanel.add(moreLabel);
      }*/

      //highlights the cell only if it shows the current date
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

    /**
     * Creates a JLabel for the given reminder.
     *
     * @param reminder The reminder to create a label for
     * @return The label for the reminder
     */
    /**private JLabel reminderLabel(Reminder reminder) {
      JLabel label = new JLabel("- " + reminder.getTask());
      label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
      label.setPreferredSize(new Dimension(165, 18));
      label.setForeground(reminder.getPriority().getColor());
      return label;
    }*/
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
  public class DayView {//implements IRemindersView {

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
      dialog.setSize(1200, 650);
      dialog.setLocationRelativeTo(null);

      //addListPanel();

      // Refresh the main calendar view when the window is close.
      /**dialog.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          super.windowClosed(e);
          CalenderView.this.refresh();
        }
      });*/

      dialog.setVisible(true);
    }

    /**
     * Adds the panel containing a list of reminders to the frame.
     */
    /**private void addListPanel() {
      dialog.add(new RemindersListPanel(DayView.this,
          getRemindersFromDay(year, month + 1, day), () ->
          // Open the editor with the date already selected.
          new ReminderEditorFrame(DayView.this, new Reminder(UUID.randomUUID().toString(),
              null, null, dateMillis, null, Collections.emptyList()))));
    }*/

    //@Override
    public void refresh() {
      dialog.getContentPane().removeAll();
      //addListPanel();
      dialog.revalidate();
      dialog.repaint();
    }
  }
}