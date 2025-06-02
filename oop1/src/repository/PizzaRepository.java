package repository;

import models.Ingredient;
import models.Pizza;
import models.PizzaBase;

import java.util.*;

public class PizzaRepository extends Repository<Pizza> {

    private final IngredientRepository ingredientRepository;
    private final PizzaBaseRepository pizzaBaseRepository;

    public PizzaRepository(IngredientRepository ingredientRepository, PizzaBaseRepository pizzaBaseRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaBaseRepository = pizzaBaseRepository;
        loadDefaultPizzas();
    }

    private void loadDefaultPizzas() {
        List<Ingredient> ingredients1 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Томатный соус"),
                this.ingredientRepository.findIngredientByName("Базилик"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );

        Pizza pizza1 = new Pizza("Маргарита", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients1, 543.7);
        storage.put(UUID.randomUUID(), pizza1);

        List<Ingredient> ingredients2 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Пармезан"),
                this.ingredientRepository.findIngredientByName("Дорблю"),
                this.ingredientRepository.findIngredientByName("Томатный соус"),
                this.ingredientRepository.findIngredientByName("Моцарелла"),
                this.ingredientRepository.findIngredientByName("Чеддер")
        );
        Pizza pizza2 = new Pizza("Четыре сыра", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients2, 970.6);
        storage.put(UUID.randomUUID(), pizza2);

        List<Ingredient> ingredients3 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Ветчина"),
                this.ingredientRepository.findIngredientByName("Грибы"),
                this.ingredientRepository.findIngredientByName("Томатный соус"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );
        Pizza pizza3 = new Pizza("Ветчина и грибы", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients3, 641.9);
        storage.put(UUID.randomUUID(), pizza3);

        List<Ingredient> ingredients4 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Копчёные колбаски"),
                this.ingredientRepository.findIngredientByName("Бекон"),
                this.ingredientRepository.findIngredientByName("Острый соус"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );
        Pizza pizza4 = new Pizza("Острая", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients4, 689.0);
        storage.put(UUID.randomUUID(), pizza4);

        List<Ingredient> ingredients5 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Ветчина"),
                this.ingredientRepository.findIngredientByName("Бекон"),
                this.ingredientRepository.findIngredientByName("Соус 1000 островов"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );
        Pizza pizza5 = new Pizza("Бекон", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients5, 656.6);
        storage.put(UUID.randomUUID(), pizza5);

        List<Ingredient> ingredients6 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Курочка гриль"),
                this.ingredientRepository.findIngredientByName("Соус 1000 островов"),
                this.ingredientRepository.findIngredientByName("Огурцы"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );
        Pizza pizza6 = new Pizza("Цыплёнок - набираем массу", this.pizzaBaseRepository.findBaseByName("Основа из куриного фарша"), ingredients6, 715.5);
        storage.put(UUID.randomUUID(), pizza6);

        List<Ingredient> ingredients7 = Arrays.asList(
                this.ingredientRepository.findIngredientByName("Пепперони"),
                this.ingredientRepository.findIngredientByName("Томатный соус"),
                this.ingredientRepository.findIngredientByName("Моцарелла")
        );
        Pizza pizza7 = new Pizza("Пепперони", this.pizzaBaseRepository.findBaseByName("Классическое тесто"), ingredients7, 553.7);
        storage.put(UUID.randomUUID(), pizza7);
    }

    public Pizza findPizzaByName(String name) {
        return this.storage.values().stream()
                .filter(pizza -> pizza.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
    
    public IngredientRepository getIngredientRepository() {
        return ingredientRepository;
    }

    public PizzaBaseRepository getPizzaBaseRepository() {
        return pizzaBaseRepository;
    }
}