package carRentalSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import carRentalSystem.entities.Bill;
import carRentalSystem.entities.Customer;
import carRentalSystem.entities.Equipment;
import carRentalSystem.entities.Location;
import carRentalSystem.entities.Reservation;
import carRentalSystem.entities.Vehicle;
import carRentalSystem.enums.ReservationStatus;
import carRentalSystem.enums.VehicleStatus;
import carRentalSystem.enums.VehicleType;
import carRentalSystem.observer.RentalObserver;
import carRentalSystem.strategy.PricingStrategy;
import carRentalSystem.strategy.StandardPricingStrategy;

public class CarRentalSystem {
    private static volatile CarRentalSystem instance;

    private final ConcurrentHashMap<String, Location> locations;
    private final ConcurrentHashMap<String, Vehicle> vehicles;
    private final ConcurrentHashMap<String, Reservation> reservations;
    // Maps location ID to list of vehicles at that location
    private final ConcurrentHashMap<String, List<Vehicle>> locationVehicles;
    private final CopyOnWriteArrayList<RentalObserver> observers;
    private PricingStrategy pricingStrategy;
    private final AtomicInteger reservationCounter;
    private static final double LATE_FEE_PER_DAY = 50.0;

    private CarRentalSystem() {
        this.locations = new ConcurrentHashMap<>();
        this.vehicles = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.locationVehicles = new ConcurrentHashMap<>();
        this.observers = new CopyOnWriteArrayList<>();
        this.pricingStrategy = new StandardPricingStrategy();
        this.reservationCounter = new AtomicInteger(0);
    }

    public static CarRentalSystem getInstance() {
        if (instance == null) {
            synchronized (CarRentalSystem.class) {
                if (instance == null) {
                    instance = new CarRentalSystem();
                }
            }
        }
        return instance;
    }

    public synchronized void addLocation(Location location) {
        locations.put(location.getId(), location);
        locationVehicles.putIfAbsent(location.getId(), new ArrayList<>());
    }

    public synchronized void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        locationVehicles.computeIfAbsent(vehicle.getLocationId(),
                k -> new ArrayList<>()).add(vehicle);
    }

    public synchronized Reservation makeReservation(Customer customer,
            VehicleType vehicleType, String pickupLocationId,
            String returnLocationId, LocalDate pickupDate,
            LocalDate returnDate, List<Equipment> equipment) {
        // Check if any vehicles of this type are available at the pickup location
        List<Vehicle> vehiclesAtLocation = locationVehicles
                .getOrDefault(pickupLocationId, Collections.emptyList());
        boolean hasAvailable = vehiclesAtLocation.stream()
                .anyMatch(v -> v.getVehicleType() == vehicleType
                        && v.getStatus() == VehicleStatus.AVAILABLE);

        if (!hasAvailable) {
            throw new CarRentalException("No " + vehicleType
                    + " vehicles available at location " + pickupLocationId);
        }

        // Create the reservation
        String reservationId = "RES-" + reservationCounter.incrementAndGet();
        Reservation reservation = new Reservation(reservationId, customer,
                vehicleType, pickupLocationId, returnLocationId,
                pickupDate, returnDate, equipment);

        reservations.put(reservationId, reservation);

        // Notify observers
        notifyReservationCreated(reservation);

        return reservation;
    }

    public synchronized Vehicle pickupVehicle(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new CarRentalException("Reservation not found: " + reservationId);
        }

        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new CarRentalException(
                    "Reservation is not in CONFIRMED status: " + reservation.getStatus());
        }

        // Find an available vehicle of the right type at the pickup location
        List<Vehicle> vehiclesAtLocation = locationVehicles
                .getOrDefault(reservation.getPickupLocationId(), Collections.emptyList());
        Vehicle vehicle = vehiclesAtLocation.stream()
                .filter(v -> v.getVehicleType() == reservation.getVehicleType()
                        && v.getStatus() == VehicleStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new CarRentalException(
                        "No available " + reservation.getVehicleType()
                                + " at pickup location"));

        // Assign vehicle and update statuses
        vehicle.setStatus(VehicleStatus.RENTED);
        reservation.assignVehicle(vehicle);
        reservation.activate();

        // Notify observers
        notifyVehiclePickedUp(reservation);

        return vehicle;
    }

    public synchronized Bill returnVehicle(String reservationId,
            String returnLocationId, LocalDate actualReturnDate) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new CarRentalException("Reservation not found: " + reservationId);
        }

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new CarRentalException(
                    "Reservation is not ACTIVE: " + reservation.getStatus());
        }

        Vehicle vehicle = reservation.getAssignedVehicle();

        // Calculate rental days
        long daysDiff = reservation.getReturnDate().toEpochDay()
                - reservation.getPickupDate().toEpochDay();
        int rentalDays = (int) Math.max(1, daysDiff);

        // Calculate base cost using pricing strategy
        double baseCost = pricingStrategy.calculateCost(
                vehicle.getDailyRate(), rentalDays);

        // Calculate equipment cost
        double equipmentCost = reservation.getEquipment().stream()
                .mapToDouble(e -> e.getDailyRate() * rentalDays)
                .sum();

        // Calculate late fee
        double lateFee = 0;
        if (actualReturnDate.isAfter(reservation.getReturnDate())) {
            int lateDays = (int) (actualReturnDate.toEpochDay()
                    - reservation.getReturnDate().toEpochDay());
            lateFee = lateDays * LATE_FEE_PER_DAY;
        }

        // Create bill
        Bill bill = new Bill(reservation, baseCost, equipmentCost, lateFee);

        // Update vehicle: mark as available at the return location
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        // Move vehicle to return location if different
        if (!vehicle.getLocationId().equals(returnLocationId)) {
            locationVehicles.get(vehicle.getLocationId()).remove(vehicle);
            vehicle.setLocationId(returnLocationId);
            locationVehicles.computeIfAbsent(returnLocationId,
                    k -> new ArrayList<>()).add(vehicle);
        }

        // Complete reservation
        reservation.complete(bill.getTotalCost());

        // Notify observers
        notifyVehicleReturned(reservation, bill);

        return bill;
    }

    public synchronized void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new CarRentalException("Reservation not found: " + reservationId);
        }

        // Cancel the reservation (enforces state machine)
        reservation.cancel();
    }

    public synchronized void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addObserver(RentalObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RentalObserver observer) {
        observers.remove(observer);
    }

    private void notifyReservationCreated(Reservation reservation) {
        for (RentalObserver observer : observers) {
            try {
                observer.onReservationCreated(reservation);
            } catch (Exception e) {
                System.err.println("Observer notification failed: " + e.getMessage());
            }
        }
    }

    private void notifyVehiclePickedUp(Reservation reservation) {
        for (RentalObserver observer : observers) {
            try {
                observer.onVehiclePickedUp(reservation);
            } catch (Exception e) {
                System.err.println("Observer notification failed: " + e.getMessage());
            }
        }
    }

    private void notifyVehicleReturned(Reservation reservation, Bill bill) {
        for (RentalObserver observer : observers) {
            try {
                observer.onVehicleReturned(reservation, bill);
            } catch (Exception e) {
                System.err.println("Observer notification failed: " + e.getMessage());
            }
        }
    }
}