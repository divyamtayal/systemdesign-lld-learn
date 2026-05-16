package machine_coding.parking_lot.strategy.fee;

import machine_coding.parking_lot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}