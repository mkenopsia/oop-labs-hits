package service;

import models.Ingredient;
import models.PizzaBase;
import models.StandardEntity;
import repository.PizzaBaseRepository;
import utils.NameFilter;
import utils.Printer;

import java.util.Scanner;
import java.util.UUID;

public class PizzaBaseService implements CrudService {

    private final PizzaBaseRepository pizzaBaseRepository;

    public PizzaBaseService(PizzaBaseRepository pizzaBaseRepository) {
        this.pizzaBaseRepository = pizzaBaseRepository;
    }

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Вывести все основы: 1");
            System.out.println("Добавить основу: 2");
            System.out.println("Редактировать основу: 3");
            System.out.println("Удалить основу: 4");
            System.out.println("Найти основу: 5");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    Printer.printBases(pizzaBaseRepository.getAll());
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
                    System.out.println("Введите название основы:");
                    scanner.nextLine();
                    String pattern = scanner.nextLine();
                    NameFilter<PizzaBase> nameFilter = new NameFilter<>(pattern);
                    Printer.printBases(nameFilter.filter(pizzaBaseRepository.getAll()));
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
            System.out.println("Выход: 9");
        }
    }

    @Override
    public void save(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название основы: ");
        String name = scanner.nextLine();
        System.out.println("Введите цену основы");
        Double price = validatePrice(scanner);

        if(price == null) return;

        this.pizzaBaseRepository.save(UUID.randomUUID(), new PizzaBase(name, price));
    }
    @Override
    public void delete(Scanner scanner) {
        Printer.printBases(pizzaBaseRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите айди основы для удаления:");
        String id = scanner.nextLine();

        pizzaBaseRepository.delete(UUID.fromString(id));
    }

    @Override
    public void update(Scanner scanner) {
        Printer.printBases(pizzaBaseRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите айди основы, который хотите редактировать:");
        String id = scanner.nextLine();

        System.out.println("Измените название основы: ");
        String name = scanner.nextLine();
        System.out.println("Измените цену основы");
        Double price = validatePrice(scanner);

        if(price == null) return;

        pizzaBaseRepository.save(UUID.fromString(id), new PizzaBase(name, price));
    }

    private Double validatePrice(Scanner scanner) {
        Double price = scanner.nextDouble();

        PizzaBase standard = this.pizzaBaseRepository.findBaseByName("Классическое тесто");
        if(price > (standard.getPrice() * 0.2) + standard.getPrice()) {
            System.out.printf("Цена не должна превышать %s\n", (standard.getPrice() * 0.2) + standard.getPrice());
            return null;
        }

        return price;
    }
}
