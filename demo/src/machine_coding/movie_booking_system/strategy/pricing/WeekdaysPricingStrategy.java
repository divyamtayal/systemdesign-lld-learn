package machine_coding.movie_booking_system.strategy.pricing;

import java.util.List;

import machine_coding.movie_booking_system.entities.Seat;

public class WeekdaysPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();
    }

}
