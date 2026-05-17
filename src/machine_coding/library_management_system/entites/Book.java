package machine_coding.library_management_system.entites;

public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    @Override
    public String getAuthorOrPublisher() {
        return author;
    }
}