package carRentalSystem.entities;

import carRentalSystem.enums.EquipmentType;

public class Equipment {
    private final EquipmentType type;
    private final double dailyRate;

    public Equipment(EquipmentType type, double dailyRate) {
        this.type = type;
        this.dailyRate = dailyRate;
    }

    public EquipmentType getType() {
        return type;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    @Override
    public String toString() {
        return type + " ($" + dailyRate + "/day)";
    }
}