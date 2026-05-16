package notificationsystem.factory;

import java.util.HashMap;
import java.util.Map;

import notificationsystem.enums.NotificationType;
import notificationsystem.strategy.EmailGateway;
import notificationsystem.strategy.NotificationGateway;
import notificationsystem.strategy.PushGateway;
import notificationsystem.strategy.SMSGateway;

public class NotificationFactory {
    private static final Map<NotificationType, NotificationGateway> gatewayMap = new HashMap<>();

    public static NotificationGateway createGateway(NotificationType type) {
        if (gatewayMap.containsKey(type)) {
            return gatewayMap.get(type);
        }

        NotificationGateway gateway = null;

        switch (type) {
            case EMAIL:
                gateway = new EmailGateway();
                break;
            case SMS:
                gateway = new SMSGateway();
                break;
            case PUSH:
                gateway = new PushGateway();
                break;
        }

        gatewayMap.put(type, gateway);
        return gateway;
    }
}
