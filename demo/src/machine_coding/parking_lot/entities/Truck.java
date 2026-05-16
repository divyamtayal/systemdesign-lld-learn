package machine_coding.parking_lot.entities;

import machine_coding.parking_lot.enums.VehicleSize;

public class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleSize.LARGE);
    }

}
