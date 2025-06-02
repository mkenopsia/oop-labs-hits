package models;

import java.util.ArrayList;
import java.util.List;

public class PizzaBoard extends StandardEntity {

    public PizzaBoard() {}

    public PizzaBoard(String name, Double price) {
        super(name, price);
    }

    public PizzaBoard(String name, Double price, List<Ingredient> ingredients, List<Pizza> availablePizzas) {
        super(name, price);
        this.ingredients = ingredients;
        this.availablePizzas = availablePizzas;
    }

    private List<Ingredient> ingredients = new ArrayList<>();

    private List<Pizza> availablePizzas = new ArrayList<>();

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Pizza> getAvailablePizza() {
        return availablePizzas;
    }

    public void setAvailablePizza(List<Pizza> availablePizza) {
        this.availablePizzas = availablePizza;
    }
}