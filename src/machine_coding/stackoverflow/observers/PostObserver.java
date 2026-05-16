package machine_coding.stackoverflow.observers;

import machine_coding.stackoverflow.entities.Event;

public interface PostObserver {
    void onPostEvent(Event event);
}