package machine_coding.food_delivery_system.strategy.assignment;

import java.util.List;
import java.util.Optional;

import machine_coding.food_delivery_system.entities.DeliveryAgent;
import machine_coding.food_delivery_system.entities.Order;

public interface DeliveryAssignmentStrategy {
    Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents);
}