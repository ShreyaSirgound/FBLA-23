import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventsDataFile {

    protected File file;
    
   /**
   * Creates a new data file (if it does not already exist).
   *
   * @param name The name of the file, including the extension
   * @throws IOException If there was an error while creating the file
   */
    public EventsDataFile(String name) throws IOException {
        this.file = new File(name); 

        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
        }
    }

    /**
     * Outputs an event object to the events data file
     * @param fileName the name of the events data file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void output(List<Event> eventsList) throws FileNotFoundException, IOException, ClassNotFoundException {
    	BufferedWriter out = new BufferedWriter(new FileWriter("events.txt"));
    	out.write(""+ eventsList.size()); out.newLine();
    	for(Event e : eventsList) {
    		out.write(e.getName());
    		out.newLine();
    		out.write(e.getDesc());
    		out.newLine();
            out.write(e.getLocation());
            out.newLine();
    		out.write(e.getDate());
    		out.newLine();
    		out.write(e.getTime());
    		out.newLine();
    		out.write("" + e.getPoints());
    		out.newLine();
    	}
    	out.close();
    }

    /**
     * Reads the list of events from the data file 
     * @return the ArrayList of all saved event objects
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Input() throws IOException, ClassNotFoundException {
    	BufferedReader in = new BufferedReader(new FileReader("events.txt"));
    	int numOfEvents = Integer.parseInt(in.readLine());
    	for (int i = 0; i < numOfEvents; i++) {
    		String name = in.readLine();
    		String desc = in.readLine();
            String location = in.readLine();
    		String date = in.readLine();
    		String time = in.readLine();
    		int points = Integer.parseInt(in.readLine());
    		Event.eventList.add(new Event(name, desc, location, date, time, points));
    	}
    	in.close();
    }
}
