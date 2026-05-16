package movingBookingDemo.strategy.pricing;

import java.util.List;

import movingBookingDemo.entities.Seat;

public class WeekendPricingStrategy implements PricingStrategy {
    private static final double WEEKEND_SURCHARGE = 1.2; // 20% surcharge

    @Override
    public double calculatePrice(List<Seat> seats) {
        double basePrice = seats.stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();
        return basePrice * WEEKEND_SURCHARGE;
    }

}
