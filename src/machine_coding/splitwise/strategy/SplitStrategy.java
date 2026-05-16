package machine_coding.splitwise.strategy;

import java.util.List;

import machine_coding.splitwise.entities.Split;
import machine_coding.splitwise.entities.User;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);
}