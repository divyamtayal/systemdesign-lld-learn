package machine_coding.stackoverflow.strategy;

import java.util.List;

import machine_coding.stackoverflow.entities.Question;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);
}
