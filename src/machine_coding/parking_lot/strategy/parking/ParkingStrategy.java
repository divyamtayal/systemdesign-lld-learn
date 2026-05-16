package machine_coding.parking_lot.strategy.parking;

import java.util.List;
import java.util.Optional;

import machine_coding.parking_lot.entities.ParkingFloor;
import machine_coding.parking_lot.entities.ParkingSpot;
import machine_coding.parking_lot.entities.Vehicle;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}