package machine_coding.movie_booking_system.entities;

import java.util.UUID;

import machine_coding.movie_booking_system.enums.PaymentStatus;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final PaymentStatus status;
    private final String transactionId;

    public Payment(double amount, PaymentStatus status, String transactionId) {
        this.paymentId = UUID.randomUUID().toString();
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
