package machine_coding.atm.entities;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final String accountNumber;
    private double balance;
    private Map<String, Card> cards;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.cards = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
