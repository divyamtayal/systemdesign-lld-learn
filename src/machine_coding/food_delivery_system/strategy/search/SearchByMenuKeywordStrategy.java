package machine_coding.food_delivery_system.strategy.search;

import java.util.List;
import java.util.stream.Collectors;

import machine_coding.food_delivery_system.entities.Restaurant;

public class SearchByMenuKeywordStrategy implements RestaurantSearchStrategy {
    private final String keyword;

    public SearchByMenuKeywordStrategy(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurants) {
        return allRestaurants.stream()
                .filter(r -> r.getMenu().getItems().values().stream()
                        .anyMatch(item -> item.getName().toLowerCase().contains(keyword)))
                .collect(Collectors.toList());
    }
}
