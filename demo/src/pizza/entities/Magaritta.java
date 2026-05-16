package pizza.entities;

public class Magaritta implements BasePizaa {
    @Override
    public double getCost() {
        return 5.00;
    }

    @Override
    public String getDescription() {
        return "Magaritta Pizza";
    }

}
