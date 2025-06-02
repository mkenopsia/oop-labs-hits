package service;

import models.Ingredient;
import models.Pizza;
import models.PizzaBase;
import repository.IngredientRepository;
import repository.PizzaBaseRepository;
import repository.PizzaRepository;
import utils.NameFilter;
import utils.Printer;
import utils.Refresher;

import java.util.*;
import java.util.stream.Collectors;

public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final PizzaBaseRepository pizzaBaseRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = pizzaRepository.getIngredientRepository();
        this.pizzaBaseRepository = pizzaRepository.getPizzaBaseRepository();
    }

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Вывести все пиццы: 1");
            System.out.println("Добавить пиццу: 2");
            System.out.println("Редактировать пиццу: 3");
            System.out.println("Удалить пиццу: 4");
            System.out.println("Найти пиццу: 5");
            System.out.println("Отфильтровать пиццы по ингридиенту: 6");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    Printer.printPizzas(pizzaRepository.getAll());
                    break;
                case 2:
                    this.save(scanner);
                    break;
                case 3:
                    this.update(scanner);
                    break;
                case 4:
                    this.delete(scanner);
                    break;
                case 5:
                    System.out.println("Введите название пиццы:");
                    scanner.nextLine();
                    String pattern = scanner.nextLine();
                    NameFilter<Pizza> nameFilter = new NameFilter<>(pattern);
                    Printer.printPizzas(nameFilter.filter(pizzaRepository.getAll()));
                    break;
                case 6:
                    Printer.printPizzas(this.filterByIngredientName(scanner,  pizzaRepository.getAll()));
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
            System.out.println("Выход: 9");
        }
    }

    private  Map<UUID, Pizza> filterByIngredientName(Scanner scanner, Map<UUID, Pizza> map) {
        scanner.nextLine();
        System.out.println("Введите название ингредиента:");
        String pattern = scanner.nextLine();
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getIngredients()
                        .stream().map(ingredient -> ingredient.getName().toLowerCase())
                        .toList()
                        .contains(pattern.toLowerCase()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private void update(Scanner scanner) {
        Printer.printPizzas(pizzaRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите id пиццы для редактирования:");

        UUID id = UUID.fromString(scanner.nextLine());
        Pizza oldPizza = this.pizzaRepository.get(id);

        Pizza pizza = new Pizza();

        System.out.println("Введите название пиццы (q - пропустить): ");
        String name = scanner.nextLine();
        pizza.setName((name.equals("q")) ? oldPizza.getName() : name);

        System.out.println("Введите цену пиццы ЧЕРЕЗ ТОЧКУ! (q - пропустить): ");
        String price = scanner.nextLine();
        pizza.setPrice((price.equals("q")) ? oldPizza.getPrice() : Double.parseDouble(price));

        Printer.printIngredients(this.ingredientRepository.getAll());

        pizza.setIngredients(Refresher.refreshIngredients(scanner));

        Printer.printBases(this.pizzaBaseRepository.getAll());
        System.out.println("Введите айди основы (q - пропустить):");
        String baseId = scanner.nextLine();
        pizza.setPizzaBase(baseId.equals("q") ? oldPizza.getPizzaBase() : this.pizzaBaseRepository.get(UUID.fromString(baseId)));

        this.pizzaRepository.save(id, pizza);
    }

    public void delete(Scanner scanner) {
        Printer.printPizzas(pizzaRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите id пиццы для удаления:");
        this.pizzaRepository.delete(UUID.fromString(scanner.nextLine()));
    }

    private void save(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название пиццы: ");
        String name = scanner.nextLine();
        System.out.println("Введите цену пиццы");
        Double price = scanner.nextDouble();

        scanner.nextLine();
        Printer.printIngredients(ingredientRepository.getAll());
        List<Ingredient> ingredients = Refresher.refreshIngredients(scanner);

        Printer.printBases(this.pizzaBaseRepository.getAll());
        System.out.println("Введите айди основы");
        scanner.nextLine();
        PizzaBase pizzaBase = this.pizzaBaseRepository.get(UUID.fromString(scanner.nextLine()));

        this.pizzaRepository.save(UUID.randomUUID(), new Pizza(name, pizzaBase, ingredients, price));
    }
}
