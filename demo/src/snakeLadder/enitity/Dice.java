package snakeLadder.enitity;

public class Dice {
    private final int maxValue;
    private final int minValue;

    public Dice(int minValue, int maxValue) {
        if (minValue <= 0 || maxValue <= 0 || minValue >= maxValue) {
            throw new IllegalArgumentException("Invalid dice range");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int roll() {
        // Simulate dice roll (for demonstration, we can use a random number generator)
        int result = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
        // System.out.println("Dice rolled: " + result);
        return result;
    }
}
