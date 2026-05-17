package machine_coding.library_management_system.strategy;

import java.util.ArrayList;
import java.util.List;

import machine_coding.library_management_system.entites.LibraryItem;

public class SearchByTitleStrategy implements SearchStrategy {
    @Override
    public List<LibraryItem> search(String query, List<LibraryItem> items) {
        List<LibraryItem> result = new ArrayList<>();
        items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(query.toLowerCase()))
                .forEach(result::add);
        return result;
    }
}