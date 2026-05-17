package machine_coding.library_management_system.strategy;

import java.util.List;

import machine_coding.library_management_system.entites.LibraryItem;

public interface SearchStrategy {
    List<LibraryItem> search(String query, List<LibraryItem> items);
}