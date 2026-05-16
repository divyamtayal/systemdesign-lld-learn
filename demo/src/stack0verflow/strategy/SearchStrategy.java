package stack0verflow.strategy;

import java.util.List;

import stack0verflow.entities.Question;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);
}
