package machine_coding.car_rental_system.entities;

import machine_coding.car_rental_system.enums.VehicleStatus;
import machine_coding.car_rental_system.enums.VehicleType;

public class Vehicle {
    private final String id;
    private final String licensePlate;
    private final VehicleType vehicleType;
    private final double dailyRate;
    private VehicleStatus status;
    private String locationId;

    public Vehicle(String id, String licensePlate, VehicleType vehicleType,
            double dailyRate, String locationId) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.dailyRate = dailyRate;
        this.status = VehicleStatus.AVAILABLE;
        this.locationId = locationId;
    }

    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Vehicle{id=" + id + ", plate=" + licensePlate
                + ", type=" + vehicleType + ", rate=$" + dailyRate + "/day}";
    }
}