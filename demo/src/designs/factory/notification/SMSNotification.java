package factory.notification;

public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS notification sent with message: " + message);
    }

}
