package machine_coding.library_management_system.strategy;

import java.util.ArrayList;
import java.util.List;

import machine_coding.library_management_system.entites.LibraryItem;

public class SearchByAuthorStrategy implements SearchStrategy {
    @Override
    public List<LibraryItem> search(String query, List<LibraryItem> items) {
        List<LibraryItem> result = new ArrayList<>();
        items.stream()
                .filter(item -> item.getAuthorOrPublisher().toLowerCase().contains(query.toLowerCase()))
                .forEach(result::add);
        return result;
    }
}
