package carRentalSystem;

import java.time.LocalDate;
import java.util.*;

import carRentalSystem.entities.Bill;
import carRentalSystem.entities.Customer;
import carRentalSystem.entities.Equipment;
import carRentalSystem.entities.Location;
import carRentalSystem.entities.Reservation;
import carRentalSystem.entities.Vehicle;
import carRentalSystem.enums.EquipmentType;
import carRentalSystem.enums.VehicleType;
import carRentalSystem.observer.EmailNotificationObserver;
import carRentalSystem.observer.InvoiceObserver;
import carRentalSystem.strategy.StandardPricingStrategy;
import carRentalSystem.strategy.WeekendPricingStrategy;

public class CarRentalDemo {
    public static void main(String[] args) {
        CarRentalSystem system = CarRentalSystem.getInstance();

        // Register observers
        system.addObserver(new EmailNotificationObserver());
        system.addObserver(new InvoiceObserver());

        // Add locations
        Location jfk = new Location("L1", "JFK Airport", "JFK Airport, NY");
        Location downtown = new Location("L2", "Downtown Manhattan", "123 Main St, NY");
        system.addLocation(jfk);
        system.addLocation(downtown);

        // Add vehicles
        system.addVehicle(new Vehicle("V1", "ABC-1234", VehicleType.ECONOMY, 40.0, "L1"));
        system.addVehicle(new Vehicle("V2", "DEF-5678", VehicleType.ECONOMY, 40.0, "L1"));
        system.addVehicle(new Vehicle("V3", "GHI-9012", VehicleType.SUV, 75.0, "L1"));
        system.addVehicle(new Vehicle("V4", "JKL-3456", VehicleType.LUXURY, 150.0, "L2"));

        // Create customers
        Customer alice = new Customer("C1", "Alice", "alice@email.com", "DL-001");
        Customer bob = new Customer("C2", "Bob", "bob@email.com", "DL-002");

        // Equipment options
        Equipment gps = new Equipment(EquipmentType.GPS, 10.0);
        Equipment childSeat = new Equipment(EquipmentType.CHILD_SEAT, 8.0);

        // === Scenario 1: Standard reservation and pickup ===
        System.out.println("========== SCENARIO 1: Reserve + Pickup (Standard Pricing) ==========");
        system.setPricingStrategy(new StandardPricingStrategy());
        Reservation res1 = system.makeReservation(alice, VehicleType.ECONOMY,
                "L1", "L1", LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 13),
                Arrays.asList(gps));
        System.out.println("Reserved: " + res1);

        Vehicle pickup1 = system.pickupVehicle(res1.getId());
        System.out.println("Picked up: " + pickup1);

        // === Scenario 2: Return vehicle on time ===
        System.out.println("\n========== SCENARIO 2: Return On Time ==========");
        Bill bill1 = system.returnVehicle(res1.getId(), "L1", LocalDate.of(2025, 3, 13));
        System.out.println("Bill: " + bill1);

        // === Scenario 3: Weekend pricing ===
        System.out.println("\n========== SCENARIO 3: Reserve with Weekend Pricing ==========");
        system.setPricingStrategy(new WeekendPricingStrategy(1.5));
        Reservation res2 = system.makeReservation(bob, VehicleType.SUV,
                "L1", "L2", LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 17),
                Arrays.asList(gps, childSeat));
        System.out.println("Reserved: " + res2);

        Vehicle pickup2 = system.pickupVehicle(res2.getId());
        System.out.println("Picked up: " + pickup2);

        // === Scenario 4: Late return ===
        System.out.println("\n========== SCENARIO 4: Late Return (1 day late) ==========");
        Bill bill2 = system.returnVehicle(res2.getId(), "L2", LocalDate.of(2025, 3, 18));
        System.out.println("Bill: " + bill2);

        // === Scenario 5: Cancel a reservation ===
        System.out.println("\n========== SCENARIO 5: Cancel Reservation ==========");
        Reservation res3 = system.makeReservation(alice, VehicleType.LUXURY,
                "L2", "L2", LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5),
                Collections.emptyList());
        System.out.println("Reserved: " + res3);
        system.cancelReservation(res3.getId());
        System.out.println("Cancelled. Status: " + res3.getStatus());
    }
}