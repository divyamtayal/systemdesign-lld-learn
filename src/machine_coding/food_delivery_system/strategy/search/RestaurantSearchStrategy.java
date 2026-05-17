package machine_coding.food_delivery_system.strategy.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import machine_coding.food_delivery_system.entities.Address;
import machine_coding.food_delivery_system.entities.Restaurant;

public interface RestaurantSearchStrategy {
    List<Restaurant> filter(List<Restaurant> allRestaurants);
}