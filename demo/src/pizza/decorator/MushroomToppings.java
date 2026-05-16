package pizza.decorator;

import pizza.entities.BasePizaa;

public class MushroomToppings extends ToppingsDecorator {
    public MushroomToppings(BasePizaa basePizaa) {
        super(basePizaa);
    }

    @Override
    public double getCost() {
        return basePizaa.getCost() + 1.00;
    }

    @Override
    public String getDescription() {
        return basePizaa.getDescription() + ", Mushroom";
    }

}
