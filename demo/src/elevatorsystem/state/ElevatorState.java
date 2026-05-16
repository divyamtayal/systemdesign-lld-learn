package elevatorsystem.state;

import elevatorsystem.entities.Elevator;
import elevatorsystem.entities.Request;
import elevatorsystem.enums.Direction;

public interface ElevatorState {
    void move(Elevator elevator);

    void addRequest(Elevator elevator, Request request);

    Direction getDirection();
}
