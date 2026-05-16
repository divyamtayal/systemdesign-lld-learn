package machine_coding.movie_booking_system.strategy.payment;

import machine_coding.movie_booking_system.entities.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}