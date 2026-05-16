package machine_coding.notification_system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import machine_coding.notification_system.decorator.RetryableGatewayDecorator;
import machine_coding.notification_system.entities.Notification;
import machine_coding.notification_system.factory.NotificationFactory;
import machine_coding.notification_system.strategy.NotificationGateway;

public class NotificationService {
    private final ExecutorService executor;

    public NotificationService(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void sendNotification(Notification notification) {
        executor.submit(() -> {
            NotificationGateway gateway = new RetryableGatewayDecorator(
                    NotificationFactory.createGateway(notification.getType()),
                    3,
                    1000);
            try {
                gateway.send(notification);
            } catch (Exception e) {
                System.out.println("Exception while sending notification: " + e);
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
