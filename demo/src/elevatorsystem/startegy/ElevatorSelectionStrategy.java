package elevatorsystem.startegy;

import java.util.List;
import java.util.Optional;

import elevatorsystem.entities.Elevator;
import elevatorsystem.entities.Request;

public interface ElevatorSelectionStrategy {
    Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
}