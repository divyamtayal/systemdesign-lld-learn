package machine_coding.parking_lot.entities;

import machine_coding.parking_lot.enums.VehicleSize;

public class Bike extends Vehicle {
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleSize.SMALL);
    }

}
