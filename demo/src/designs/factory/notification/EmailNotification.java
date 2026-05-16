package factory.notification;

public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email notification sent with message: " + message);
    }

}
