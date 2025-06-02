package service;

import models.*;
import repository.IngredientRepository;
import repository.PizzaBoardRepository;
import repository.PizzaRepository;
import utils.Refresher;
import utils.Printer;

import java.util.*;
import java.util.stream.Collectors;

public class PizzaBoardService {

    private final PizzaBoardRepository pizzaBoardRepository;
    private final IngredientRepository ingredientRepository;

    public PizzaBoardService(PizzaBoardRepository pizzaBoardRepository) {
        this.pizzaBoardRepository = pizzaBoardRepository;
        this.ingredientRepository = pizzaBoardRepository.getIngredientRepository();
    }


    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Вывести все бортики: 1");
            System.out.println("Добавить бортик: 2");
            System.out.println("Редактировать бортик: 3");
            System.out.println("Удалить бортик: 4");
            System.out.println("Найти бортик: 5");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    Printer.printPizzaBoards(this.pizzaBoardRepository.getAll());
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
                    Printer.printPizzaBoards(filter(scanner));
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
            System.out.println("Выход: 9");
        }
    }

    private Map<UUID, PizzaBoard> filter(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название для поиска:");
        String pattern = scanner.nextLine();

        return this.pizzaBoardRepository.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getName().toLowerCase().contains(pattern.toLowerCase()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private void update(Scanner scanner) {
        scanner.nextLine();
        Printer.printPizzaBoards(this.pizzaBoardRepository.getAll());
        System.out.println("Введите id бортика для редактирования:");

        UUID id = UUID.fromString(scanner.nextLine());
        PizzaBoard oldPizzaBoard = this.pizzaBoardRepository.get(id);

        PizzaBoard pizzaBoard = new PizzaBoard();

        System.out.println("Введите название бортика (q - пропустить): ");
        String name = scanner.nextLine();
        pizzaBoard.setName((name.equals("q")) ? oldPizzaBoard.getName() : name);

        System.out.println("Введите цену бортика ЧЕРЕЗ ТОЧКУ! (q - пропустить): ");
        String price = scanner.nextLine();
        pizzaBoard.setPrice((price.equals("q")) ? oldPizzaBoard.getPrice() : Double.parseDouble(price));

        Printer.printIngredients(this.ingredientRepository.getAll());

        Printer.printIngredients(ingredientRepository.getAll());
        pizzaBoard.setIngredients(Refresher.refreshIngredients(scanner));

        pizzaBoard.setAvailablePizza(Refresher.refreshAvailablePizzas(scanner));

        this.pizzaBoardRepository.save(id, pizzaBoard);
    }

    public void delete(Scanner scanner) {
        Printer.printPizzaBoards(this.pizzaBoardRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите id бортика для удаления:");
        this.pizzaBoardRepository.delete(UUID.fromString(scanner.nextLine()));
    }

    private void save(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название: ");
        String name = scanner.nextLine();
        System.out.println("Введите цену: ");
        Double price = scanner.nextDouble();



        Printer.printIngredients(this.ingredientRepository.getAll());

        Printer.printIngredients(ingredientRepository.getAll());
        scanner.nextLine();
        var ingredients = Refresher.refreshIngredients(scanner);

        var availablePizzas = Refresher.refreshAvailablePizzas(scanner);

        this.pizzaBoardRepository.save(UUID.randomUUID(), new PizzaBoard(name, price, ingredients, availablePizzas));
    }
}
