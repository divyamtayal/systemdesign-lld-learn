package elevatorsystem.observer;

import elevatorsystem.entities.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator);
}