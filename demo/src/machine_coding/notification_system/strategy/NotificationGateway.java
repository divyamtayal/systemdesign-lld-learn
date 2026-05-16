package machine_coding.notification_system.strategy;

import machine_coding.notification_system.entities.Notification;

public interface NotificationGateway {
    void send(Notification notification) throws Exception;
}
