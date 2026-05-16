package machine_coding.notification_system.factory;

import java.util.HashMap;
import java.util.Map;

import machine_coding.notification_system.enums.NotificationType;
import machine_coding.notification_system.strategy.EmailGateway;
import machine_coding.notification_system.strategy.NotificationGateway;
import machine_coding.notification_system.strategy.PushGateway;
import machine_coding.notification_system.strategy.SMSGateway;

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
