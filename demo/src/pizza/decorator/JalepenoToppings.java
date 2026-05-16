package pizza.decorator;

import pizza.entities.BasePizaa;

public class JalepenoToppings extends ToppingsDecorator {
    public JalepenoToppings(BasePizaa basePizaa) {
        super(basePizaa);
    }

    @Override
    public double getCost() {
        return basePizaa.getCost() + 0.75;
    }

    @Override
    public String getDescription() {
        return basePizaa.getDescription() + ", Jalepeno";
    }

}
