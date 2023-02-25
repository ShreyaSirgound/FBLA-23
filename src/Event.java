import java.util.ArrayList;
import java.util.List;

//TO DO: add values to hold the date, time, and points of any single event object
public class Event {
    String eventName;

    String eventDesc;
    
    //creates an event object
    public Event(String name, String desc){
        this.eventName = name;
        this.eventDesc = desc;
    }

    //gets the name of the event
    public String getName(){
        return eventName;
    }

    //sets the name of an event
    public void setName(String name){
        this.eventName = name;
    }

    //gets the event description
    public String getDesc(){
        return eventDesc;
    }

    //sets the event description
    public void setDesc(String desc){
        this.eventDesc = desc;
    }

    //creates a list of all the events
    private static List<Event> eventList = new ArrayList<Event>();

    //add an event to the events list
    public static void addEvent(Event event) {
        eventList.add(event);
      }
    
      /**
       * Unregisters an existing event. Does nothing if the event does not exist.
       *
       * @param label The event to remove
       */
      public static void removeEvent(Event event) {
        eventList.remove(event);
      }
    
      /**
       * Gets a list of all active events.
       *
       * @return A list of events
       */
      public static List<Event> getEvents() {
        return eventList;
      }
    
}
