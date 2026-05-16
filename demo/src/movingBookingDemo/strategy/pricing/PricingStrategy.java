package movingBookingDemo.strategy.pricing;

import java.util.List;

import movingBookingDemo.entities.Seat;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);

}
