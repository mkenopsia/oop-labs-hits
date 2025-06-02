package models;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends StandardEntity{

    public Pizza() {}

    public Pizza(String name, Double price) {
        super(name, price);
    }

    public Pizza(String name, PizzaBase base, List<Ingredient> ingredients, Double price) {
        super(name, price);
        this.pizzaBase = base;
        this.ingredients = ingredients;
    }

    public Pizza(String name, Double price, PizzaSize size, PizzaBase pizzaBase, PizzaBoard board, List<Ingredient> ingredients) {
        super(name, price);
        this.size = size;
        this.pizzaBase = pizzaBase;
        this.board = board;
        this.ingredients = ingredients;
        this.setPrice(price);
    }

    private PizzaSize size;

    private PizzaBoard board;

    private PizzaBase pizzaBase;

    private PizzaSize pizzaSize;

    private List<Ingredient> ingredients;

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public PizzaBoard getBoard() {
        return board;
    }

    public void setBoard(PizzaBoard board) {
        this.board = board;
    }

    public PizzaBase getPizzaBase() {
        return pizzaBase;
    }

    public void setPizzaBase(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }
}