package machine_coding.elevator_system.observer;

import machine_coding.elevator_system.entities.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator);
}