package factory.creator;

import factory.notification.EmailNotification;
import factory.notification.Notification;

public class EmailNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
