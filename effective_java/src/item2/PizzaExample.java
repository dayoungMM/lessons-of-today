package item2;

import static item2.NyPizza.Size.SMALL;
import static item2.Pizza.Topping.HAM;

public class PizzaExample {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        Calzone cal = new Calzone.Builder().addTopping(HAM).sauceInside().build();

    }
}
