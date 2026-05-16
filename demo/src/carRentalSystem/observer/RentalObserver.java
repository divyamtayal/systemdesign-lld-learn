package carRentalSystem.observer;

import carRentalSystem.entities.Bill;
import carRentalSystem.entities.Reservation;

public interface RentalObserver {
    void onReservationCreated(Reservation reservation);

    void onVehiclePickedUp(Reservation reservation);

    void onVehicleReturned(Reservation reservation, Bill bill);
}
