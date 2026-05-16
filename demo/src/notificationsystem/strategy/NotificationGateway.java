package notificationsystem.strategy;

import notificationsystem.entities.Notification;

public interface NotificationGateway {
    void send(Notification notification) throws Exception;
}
