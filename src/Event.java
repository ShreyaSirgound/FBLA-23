import java.util.ArrayList;
import java.util.List;

//TO DO: add values to hold the date, time, and points of any single event object
public class Event {
    String eventName;
    String eventDesc;
    String eventDate;
    String eventTime;
    int eventPoints;
    
    //creates an event object
    public Event(String name, String desc, String date, String time, int points){
        this.eventName = name;
        this.eventDesc = desc;
        this.eventDate = date;
        this.eventTime = time;
        this.eventPoints = points;
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

    //gets the event date
    public String getDate(){
        return eventDate;
    }

    //sets the event date
    public void setDate(String date){
        this.eventDate = date;
    }

    //gets the event time
    public String getTime(){
        return eventTime;
    }

    //sets the event time
    public void setTime(String time){
        this.eventTime = time;
    }

    //gets the points the event is worth
    public int getPoints(){
        return eventPoints;
    }

    //sets the points the event is worth
    public void setPoints(int points){
        this.eventPoints = points;
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
       * @param event The event to remove
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
