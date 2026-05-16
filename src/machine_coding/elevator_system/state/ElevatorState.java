package machine_coding.elevator_system.state;

import machine_coding.elevator_system.entities.Elevator;
import machine_coding.elevator_system.entities.Request;
import machine_coding.elevator_system.enums.Direction;

public interface ElevatorState {
    void move(Elevator elevator);

    void addRequest(Elevator elevator, Request request);

    Direction getDirection();
}
