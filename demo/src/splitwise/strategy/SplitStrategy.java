package splitwise.strategy;

import java.util.List;

import splitwise.entities.Split;
import splitwise.entities.User;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);
}