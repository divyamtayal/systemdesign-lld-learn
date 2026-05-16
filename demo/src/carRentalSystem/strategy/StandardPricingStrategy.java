package carRentalSystem.strategy;

public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculateCost(double dailyRate, int days) {
        return dailyRate * days;
    }
}
