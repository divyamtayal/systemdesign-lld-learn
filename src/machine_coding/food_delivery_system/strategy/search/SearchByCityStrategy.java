package machine_coding.food_delivery_system.strategy.search;

import java.util.List;
import java.util.stream.Collectors;

import machine_coding.food_delivery_system.entities.Restaurant;

public class SearchByCityStrategy implements RestaurantSearchStrategy {
    private final String city;

    public SearchByCityStrategy(String city) {
        this.city = city;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurants) {
        return allRestaurants.stream()
                .filter(r -> r.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}
