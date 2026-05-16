package machine_coding.car_rental_system.observer;

import machine_coding.car_rental_system.entities.Bill;
import machine_coding.car_rental_system.entities.Reservation;

public interface RentalObserver {
    void onReservationCreated(Reservation reservation);

    void onVehiclePickedUp(Reservation reservation);

    void onVehicleReturned(Reservation reservation, Bill bill);
}
