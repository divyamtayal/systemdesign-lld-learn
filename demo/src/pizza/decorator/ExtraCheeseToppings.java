package pizza.decorator;

import pizza.entities.BasePizaa;

public class ExtraCheeseToppings extends ToppingsDecorator {
    public ExtraCheeseToppings(BasePizaa basePizaa) {
        super(basePizaa);
    }

    @Override
    public double getCost() {
        return basePizaa.getCost() + 1.50;
    }

    @Override
    public String getDescription() {
        return basePizaa.getDescription() + ", Extra Cheeze";
    }

}
