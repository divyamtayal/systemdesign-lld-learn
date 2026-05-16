package machine_coding.movie_booking_system.strategy.pricing;

import java.util.List;

import machine_coding.movie_booking_system.entities.Seat;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);

}
