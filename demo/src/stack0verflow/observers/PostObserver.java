package stack0verflow.observers;

import stack0verflow.entities.Event;

public interface PostObserver {
    void onPostEvent(Event event);
}