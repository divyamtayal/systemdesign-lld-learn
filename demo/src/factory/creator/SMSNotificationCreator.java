package factory.creator;

import factory.notification.Notification;
import factory.notification.SMSNotification;

public class SMSNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }

}
