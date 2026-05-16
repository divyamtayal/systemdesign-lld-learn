package machine_coding.car_rental_system.strategy;

public interface PricingStrategy {
    double calculateCost(double dailyRate, int days);
}