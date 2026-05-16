package pizza.entities;

public class VeggieDelight implements BasePizaa {
    @Override
    public double getCost() {
        return 8.00;
    }

    @Override
    public String getDescription() {
        return "Veggie Delight Pizza";
    }

}
