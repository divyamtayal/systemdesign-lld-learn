package movingBookingDemo.strategy.pricing;

import java.util.List;

import movingBookingDemo.entities.Seat;

public class WeekdaysPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();
    }

}
