package machine_coding.car_rental_system.entities;

public class Bill {
    private final Reservation reservation;
    private final double baseCost;
    private final double equipmentCost;
    private final double lateFee;
    private final double totalCost;

    public Bill(Reservation reservation, double baseCost,
            double equipmentCost, double lateFee) {
        this.reservation = reservation;
        this.baseCost = baseCost;
        this.equipmentCost = equipmentCost;
        this.lateFee = lateFee;
        this.totalCost = baseCost + equipmentCost + lateFee;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getEquipmentCost() {
        return equipmentCost;
    }

    public double getLateFee() {
        return lateFee;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return String.format("Bill{base=$%.2f, equipment=$%.2f, lateFee=$%.2f, total=$%.2f}",
                baseCost, equipmentCost, lateFee, totalCost);
    }
}