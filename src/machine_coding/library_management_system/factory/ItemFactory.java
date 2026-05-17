package machine_coding.library_management_system.factory;

import machine_coding.library_management_system.entites.Book;
import machine_coding.library_management_system.entites.LibraryItem;
import machine_coding.library_management_system.entites.Magazine;
import machine_coding.library_management_system.enums.ItemType;

public class ItemFactory {
    public static LibraryItem createItem(ItemType type, String id, String title, String author) {
        switch (type) {
            case BOOK:
                return new Book(id, title, author);
            case MAGAZINE:
                return new Magazine(id, title, author); // Author might be publisher here
            default:
                throw new IllegalArgumentException("Unknown item type.");
        }
    }
}
