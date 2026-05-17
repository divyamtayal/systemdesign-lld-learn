package machine_coding.task_management_system.entities;

import java.util.UUID;
import java.sql.Date;
import java.time.Instant;

public class Comment {
    private final String id;
    private final String content;
    private final User author;
    private final Date timestamp;

    public Comment(String content, User author) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.timestamp = (Date) Date.from(Instant.now());
    }

    public User getAuthor() {
        return author;
    }
}