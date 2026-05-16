package pizza.decorator;

import pizza.entities.BasePizaa;

public abstract class ToppingsDecorator implements BasePizaa {
    BasePizaa basePizaa;

    ToppingsDecorator(BasePizaa basePizaa) {
        this.basePizaa = basePizaa;
    }
}
