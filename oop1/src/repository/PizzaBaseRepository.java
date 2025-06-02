package repository;

import models.Ingredient;
import models.PizzaBase;

import java.util.NoSuchElementException;
import java.util.UUID;

public class PizzaBaseRepository extends Repository<PizzaBase> {

    public PizzaBaseRepository() {
        addPizzaBases();
    }

    private void addPizzaBases() {
        addPizzaBase("Классическое тесто", 210.0);
        addPizzaBase("Американское тесто", 233.5);
        addPizzaBase("Итальянское тесто", 230.0);
        addPizzaBase("Овощная основа (кабачки+зелень)", 190.5);
        addPizzaBase("Основа из куриного фарша", 250.4);
        addPizzaBase("Картофельная основа", 220.0);
        addPizzaBase("Основа из лаваша", 210.0);
    }

    private void addPizzaBase(String name, double price) {
        PizzaBase pizzaBase = new PizzaBase(name, price);
        storage.put(UUID.randomUUID(), pizzaBase);
    }

    public PizzaBase findBaseByName(String name) {
        return this.getAll().values().stream()
                .filter(base -> base.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
