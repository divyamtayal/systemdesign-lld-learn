package machine_coding.car_rental_system.observer;

import machine_coding.car_rental_system.entities.Bill;
import machine_coding.car_rental_system.entities.Reservation;

public class InvoiceObserver implements RentalObserver {
    @Override
    public void onReservationCreated(Reservation reservation) {
        // No invoice needed at reservation time
    }

    @Override
    public void onVehiclePickedUp(Reservation reservation) {
        // No invoice needed at pickup time
    }

    @Override
    public void onVehicleReturned(Reservation reservation, Bill bill) {
        System.out.println("[Invoice] Invoice generated for " + reservation.getId()
            + ": Base=$" + String.format("%.2f", bill.getBaseCost())
            + ", Equipment=$" + String.format("%.2f", bill.getEquipmentCost())
            + ", Late Fee=$" + String.format("%.2f", bill.getLateFee())
            + ", Total=$" + String.format("%.2f", bill.getTotalCost()));
    }
}