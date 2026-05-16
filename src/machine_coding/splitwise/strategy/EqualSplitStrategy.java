package machine_coding.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import machine_coding.splitwise.entities.Split;
import machine_coding.splitwise.entities.User;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants,
            List<Double> splitValues) {
        List<Split> splits = new ArrayList<>();
        double amountPerPerson = totalAmount / participants.size();
        for (User participant : participants) {
            splits.add(new Split(participant, amountPerPerson));
        }
        return splits;
    }
}