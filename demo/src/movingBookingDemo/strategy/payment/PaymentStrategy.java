package movingBookingDemo.strategy.payment;

import movingBookingDemo.entities.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}