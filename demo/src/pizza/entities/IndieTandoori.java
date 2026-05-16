package pizza.entities;

public class IndieTandoori implements BasePizaa {
    @Override
    public double getCost() {
        return 10.00;
    }

    @Override
    public String getDescription() {
        return "Indie Tandoori Pizza";
    }

}
