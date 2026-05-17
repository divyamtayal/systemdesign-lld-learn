package machine_coding.food_delivery_system.observer;

import machine_coding.food_delivery_system.entities.Order;

public interface OrderObserver {
    void onUpdate(Order order);
}