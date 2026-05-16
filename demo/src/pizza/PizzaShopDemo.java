package pizza;

import pizza.decorator.ExtraCheeseToppings;
import pizza.decorator.JalepenoToppings;
import pizza.decorator.MushroomToppings;
import pizza.entities.BasePizaa;
import pizza.entities.IndieTandoori;

public class PizzaShopDemo {
    public static void main(String[] args) {
        System.out.println("Welcome to the Pizza Shop!");
        BasePizaa indieTandooriPizza = new IndieTandoori();
        System.out.println("You have ordered: " + indieTandooriPizza.getDescription());
        System.out.println("Cost: $" + indieTandooriPizza.getCost());
        System.out.println("Description: " + indieTandooriPizza.getDescription());

        System.out.println("\nAdding extra cheese...");
        BasePizaa cheeseTandooriPizza = new ExtraCheeseToppings(indieTandooriPizza);
        System.out.println("You have ordered: " + cheeseTandooriPizza.getDescription());
        System.out.println("Cost: $" + cheeseTandooriPizza.getCost());
        System.out.println("Description: " + cheeseTandooriPizza.getDescription());

        System.out.println("Adding jalepno and mushroom with extra cheese...");
        BasePizaa jalepenoMushroomExtraCheesePizza = new JalepenoToppings(new MushroomToppings(cheeseTandooriPizza));
        System.out.println("You have ordered: " + jalepenoMushroomExtraCheesePizza.getDescription());
        System.out.println("Cost: $" + jalepenoMushroomExtraCheesePizza.getCost());
        System.out.println("Description: " + jalepenoMushroomExtraCheesePizza.getDescription());
    }
}
