import java.util.ArrayList;
import java.util.List;

public  class EventQueue {
    private List<Event> events;
    private int maxCars;

    public EventQueue(int maxCars) {
        this.events = new ArrayList<>();
        this.maxCars = maxCars;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void processEvents() {
        for (int i = 0; i < maxCars; i++) {
            if (i >= events.size()) {
                break;
            }
            Event event = events.get(i);
            event.process();
        }
    }
}