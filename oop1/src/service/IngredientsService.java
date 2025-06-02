package service;

import models.Ingredient;
import models.StandardEntity;
import repository.IngredientRepository;
import utils.NameFilter;
import utils.Printer;

import java.util.*;

public class IngredientsService implements CrudService {

    private final IngredientRepository ingredientRepository;

    public IngredientsService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Вывести все ингредиенты: 1");
            System.out.println("Добавить ингредиент: 2");
            System.out.println("Редактировать ингредиент: 3");
            System.out.println("Удалить ингредиент: 4");
            System.out.println("Найти ингридиент: 5");
            mode = scanner.nextInt();

            switch (mode) {
                case 1:
                    Printer.printIngredients(ingredientRepository.getAll());
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
                    System.out.println("Введите название ингредиента:");
                    scanner.nextLine();
                    String pattern = scanner.nextLine();
                    NameFilter<Ingredient> nameFilter = new NameFilter<>(pattern);
                    Printer.printIngredients(nameFilter.filter(ingredientRepository.getAll()));
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
        }
    }

    @Override
    public void save(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название ингредиента: ");
        String name = scanner.nextLine();
        System.out.println("Введите цену ингредиента");
        Double price = scanner.nextDouble();

        this.ingredientRepository.save(UUID.randomUUID(), new Ingredient(name, price));
    }

    @Override
    public void delete(Scanner scanner) {
        Printer.printIngredients(ingredientRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите айди ингредиента для удаления:");
        String id = scanner.nextLine();

        ingredientRepository.delete(UUID.fromString(id));
    }

    @Override
    public void update(Scanner scanner) {
        Printer.printIngredients(ingredientRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите айди ингредиента, который хотите редактировать:");
        String id = scanner.nextLine();

        System.out.println("Измените название ингредиента: ");
        String name = scanner.nextLine();
        System.out.println("Измените цену ингредиента");
        Double price = scanner.nextDouble();

        ingredientRepository.save(UUID.fromString(id), new Ingredient(name, price));
    }
}
