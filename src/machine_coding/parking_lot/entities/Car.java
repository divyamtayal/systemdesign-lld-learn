package machine_coding.parking_lot.entities;

import machine_coding.parking_lot.enums.VehicleSize;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleSize.MEDIUM);
    }

}
