import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
     * @param fileName the name of the events data file file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void output(List<Event> eventsList) throws FileNotFoundException, IOException, ClassNotFoundException {
        /*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(event);*/

        FileOutputStream writeData = new FileOutputStream("events.txt");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

        writeStream.writeObject(eventsList);
        writeStream.flush();
        writeStream.close();
    }

    /**
     * Reads the list of events from the data file 
     * @return the ArrayList of all saved event objects
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Event> input() throws IOException, ClassNotFoundException{
        /*ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Event event = (Event) in.readObject();*/

        FileInputStream readData = new FileInputStream("events.txt");
        ObjectInputStream readStream = new ObjectInputStream(readData);

        List<Event> eventsList = (List<Event>) readStream.readObject();
        readStream.close();

        return eventsList;
    }
}
