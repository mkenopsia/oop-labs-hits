package service;

import models.*;
import repository.*;
import utils.ConsoleHelper;
import utils.Printer;
import utils.Refresher;

import java.util.*;
import java.util.stream.Collectors;

public class CartService {

    private static PizzasInCartRepository pizzasInCartRepository;
    private static PizzaRepository pizzaRepository;
    private static PizzaBoardRepository pizzaBoardRepository;
    private static IngredientRepository ingredientRepository;
    private static PizzaBaseRepository pizzaBaseRepository;

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("Выберите режим:");
            System.out.println("Добавить в корзину пиццу из каталога: 1");
            System.out.println("Добавить в корзину A|B пиццу: 2");
            System.out.println("Добавить в корзину кастомную пиццу: 3");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();

            switch (mode) {
                case 1:
                    this.saveStandardPizza(scanner);
                    break;
                case 2:
                    this.saveABPizza(scanner);
                    break;
                case 3:
                    this.saveCustomPizza(scanner);
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
        }
    }

    public void saveStandardPizza(Scanner scanner) {
        Printer.printPizzas(pizzaRepository.getAll());

        System.out.println("Выберите название пиццы для добавления в корзину: ");
        scanner.nextLine();
        Pizza pizza = pizzaRepository.findPizzaByName(scanner.nextLine());

        var available = pizzaBoardRepository.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getAvailablePizza()
                        .stream().map(Pizza::getName).toList()
                        .contains(pizza.getName()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        PizzaBoard pizzaBoard;
        if(available.size() == 0) {
            pizzaBoard = new PizzaBoard("Стандартный бортик", 0.0);
        } else {
            Printer.printPizzaBoards(available);
            String name = ConsoleHelper.readString(scanner, "Выберите бортик пиццы из доступных:");
            pizzaBoard = pizzaBoardRepository.findBoardByName(name);
        }


        PizzaSize pizzaSize = this.selectPizzaSize(scanner);

        System.out.println("Хотите удвоить ингредиенты? (y/n");
        String decision = scanner.nextLine();

        List<Ingredient> ingredientsToAdd = new ArrayList<>();
        if(decision.equals("y")) {
            Printer.printIngredients(ingredientRepository.getAll());
            ingredientsToAdd = Refresher.refreshIngredientsByNames(scanner, "Укажите ингредиенты для добавки: ");
        }

        var newIngredients = new ArrayList<>(pizza.getIngredients());
        newIngredients.addAll(ingredientsToAdd);

        Double price = this.calculateTotalPrice(pizza.getPizzaBase(), pizzaBoard, pizzaSize, newIngredients);
        pizzasInCartRepository.save(UUID.randomUUID(),
                new Pizza(pizza.getName(), price, pizzaSize, pizza.getPizzaBase(), pizzaBoard, newIngredients));
    }

    public void saveABPizza(Scanner scanner) {
        Printer.printPizzas(pizzaRepository.getAll());
        System.out.println("Выберите пиццу для первой половины: ");
        scanner.nextLine();
        Pizza firstPizza = pizzaRepository.findPizzaByName(scanner.nextLine());

        System.out.println("Выберите пиццу для второй половины: ");
        Pizza secondPizza = pizzaRepository.findPizzaByName(scanner.nextLine());

        var available1 = pizzaBoardRepository.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getAvailablePizza()
                        .stream().map(Pizza::getName).toList()
                        .contains(firstPizza.getName()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        var available2 = pizzaBoardRepository.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getAvailablePizza()
                        .stream().map(Pizza::getName).toList()
                        .contains(secondPizza.getName()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));


        var available = available1.entrySet().stream()
                .filter(entry -> available2.values().stream()
                        .map(PizzaBoard::getName).toList().contains(entry.getValue().getName()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        PizzaBoard pizzaBoard;
        if(available.size() == 0) {
            pizzaBoard = new PizzaBoard("Стандартный бортик", 0.0);
        } else {
            Printer.printPizzaBoards(available);
            String name = ConsoleHelper.readString(scanner, "Выберите бортик пиццы из доступных:");
            pizzaBoard = pizzaBoardRepository.findBoardByName(name);
        }

        String name = firstPizza.getName() + " + " + secondPizza.getName();

        PizzaSize size = this.selectPizzaSize(scanner);
        Double price = (firstPizza.getPrice() + secondPizza.getPrice()) / 2 * size.getCoef();

        var ingredients = new ArrayList<>(firstPizza.getIngredients());
        ingredients.addAll(secondPizza.getIngredients());

        PizzaBase pizzaBase = new PizzaBase(firstPizza.getPizzaBase().getName() + " + " + secondPizza.getPizzaBase().getName(), firstPizza.getPrice());

        pizzasInCartRepository.save(UUID.randomUUID(), new Pizza(name, price, size, pizzaBase, pizzaBoard, ingredients));
    }

    public void saveCustomPizza(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Дайте название Вашему шедевру: ");
        String name = scanner.nextLine();

        Printer.printIngredients(ingredientRepository.getAll());
        List<Ingredient> ingredients;
        try{
            ingredients = Refresher.refreshIngredientsByNames(scanner, "Укажите ингридиенты: ");
        } catch (Exception e) {
            System.out.println("Такого ингридинта нет");
            return;
        }

        PizzaBase pizzaBase = this.selectBase(scanner);

        PizzaBoard pizzaBoard = this.selectBoard(scanner);

        PizzaSize pizzaSize = this.selectPizzaSize(scanner);

        Double price = this.calculateTotalPrice(pizzaBase, pizzaBoard, pizzaSize, ingredients);
        pizzasInCartRepository.save(UUID.randomUUID(), new Pizza(name, price, pizzaSize, pizzaBase, pizzaBoard, ingredients));
    }

    private Double calculateTotalPrice(PizzaBase base, PizzaBoard board, PizzaSize size, List<Ingredient> ingredients) {
        double total = base.getPrice() + board.getPrice();
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrice();
        }
        return total * size.getCoef();
    }

    private PizzaBase selectBase(Scanner scanner) {
        Printer.printBases(pizzaBaseRepository.getAll());
        String name = ConsoleHelper.readString(scanner, "Выберите основу:");
        return pizzaBaseRepository.findBaseByName(name);
    }

    private PizzaBoard selectBoard(Scanner scanner) {
        Printer.printPizzaBoards(pizzaBoardRepository.getAll());
        String name = ConsoleHelper.readString(scanner, "Выберите бортик пиццы:");
        return pizzaBoardRepository.findBoardByName(name);
    }

    private PizzaSize selectPizzaSize(Scanner scanner) {
        Printer.printPizzaSizes();
        System.out.println("Выберите размер для пиццы");
        String input = scanner.nextLine();
        if (input.equals("25см")) return PizzaSize.SMALL;
        if (input.equals("35см")) return PizzaSize.LARGE;
        return PizzaSize.STANDARD;
    }

    public static void setPizzasInCartRepository(PizzasInCartRepository pizzasInCartRepository) {
        CartService.pizzasInCartRepository = pizzasInCartRepository;
    }

    public static void setPizzaRepository(PizzaRepository pizzaRepository) {
        CartService.pizzaRepository = pizzaRepository;
    }

    public static void setPizzaBoardRepository(PizzaBoardRepository pizzaBoardRepository) {
        CartService.pizzaBoardRepository = pizzaBoardRepository;
    }

    public static void setIngredientRepository(IngredientRepository ingredientRepository) {
        CartService.ingredientRepository = ingredientRepository;
    }

    public static void setPizzaBaseRepository(PizzaBaseRepository pizzaBaseRepository) {
        CartService.pizzaBaseRepository = pizzaBaseRepository;
    }
}
