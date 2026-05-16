package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private final VehicleSize spotSize;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {

        if (isOccupied) {
            return false;
        }

        switch (vehicle.getSize()) {
            case SMALL:
                return spotSize == VehicleSize.SMALL;
            case MEDIUM:
                return spotSize == VehicleSize.LARGE || spotSize == VehicleSize.MEDIUM;
            case LARGE:
                return spotSize == VehicleSize.LARGE;
            default:
                return false;
        }
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public String getSpotId() {
        return spotId;
    }

}
