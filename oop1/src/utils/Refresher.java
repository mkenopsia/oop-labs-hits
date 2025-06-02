package utils;

import models.Ingredient;
import models.Pizza;
import repository.IngredientRepository;
import repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Refresher {

    private static IngredientRepository ingredientRepository;
    private static PizzaRepository pizzaRepository;

    public static List<Ingredient> refreshIngredients(Scanner scanner) {
        List<Ingredient> ingredients = new ArrayList<>();
        while(true) {
            System.out.println("Введите айди ингредиента для добавления ('q' для остановки): ");
            String input = scanner.nextLine();
            if(input.equals("q")) {
                break;
            }
            ingredients.add(ingredientRepository.get(UUID.fromString(input)));
        }
        return ingredients;
    }

    public static List<Pizza> refreshAvailablePizzas(Scanner scanner) {
        Printer.printPizzas(pizzaRepository.getAll());
        List<Pizza> pizzas = new ArrayList<>();
        while(true) {
            System.out.println("Введите айди совместимой пиццы ('q' для остановки): ");
            String input = scanner.nextLine();
            if(input.equals("q")) {
                break;
            }
            pizzas.add(pizzaRepository.get(UUID.fromString(input)));
        }
        return pizzas;
    }

    public static List<Ingredient> refreshIngredientsByNames(Scanner scanner, String prompt) {
        List<Ingredient> ingredients = new ArrayList<>();
        while(true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if(input.equals("q")) {
                break;
            }
            ingredients.add(ingredientRepository.findIngredientByName(input));
        }
        return ingredients;
    }

    public static IngredientRepository getIngredientRepository() {
        return ingredientRepository;
    }

    public static void setIngredientRepository(IngredientRepository ingredientRepository) {
        Refresher.ingredientRepository = ingredientRepository;
    }

    public static PizzaRepository getPizzaRepository() {
        return pizzaRepository;
    }

    public static void setPizzaRepository(PizzaRepository pizzaRepository) {
        Refresher.pizzaRepository = pizzaRepository;
    }
}
