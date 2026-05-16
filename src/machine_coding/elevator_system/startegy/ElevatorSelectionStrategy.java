package machine_coding.elevator_system.startegy;

import java.util.List;
import java.util.Optional;

import machine_coding.elevator_system.entities.Elevator;
import machine_coding.elevator_system.entities.Request;

public interface ElevatorSelectionStrategy {
    Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
}