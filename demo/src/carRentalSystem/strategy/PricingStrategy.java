package carRentalSystem.strategy;

public interface PricingStrategy {
    double calculateCost(double dailyRate, int days);
}