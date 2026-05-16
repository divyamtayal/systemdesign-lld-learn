package machine_coding.notification_system.strategy;

public class EmailGateway implements NotificationGateway {
    @Override
    public void send(machine_coding.notification_system.entities.Notification notification) throws Exception {
        String email = notification.getRecipient().getEmail()
                .orElseThrow(() -> new IllegalArgumentException("Email address is required for EMAIL notification."));
        System.out.println("--- Sending EMAIL ---");
        System.out.println("To: " + email);
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Body: " + notification.getMessage());
        System.out.println("---------------------\n");
    }

}
