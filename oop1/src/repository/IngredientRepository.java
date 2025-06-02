package repository;

import models.Ingredient;

import java.util.UUID;

public class IngredientRepository extends Repository<Ingredient>{

    public IngredientRepository() {
        addIngredients();
    }

    private void addIngredients() {
        addIngredient("Гауда", 165.9);
        addIngredient("Пармезан", 173.0);
        addIngredient("Дорблю", 183.0);
        addIngredient("Ветчина", 100.0);
        addIngredient("Курочка гриль", 135.0);
        addIngredient("Запечёная говядина", 130.0);
        addIngredient("Копчёные колбаски", 125.0);
        addIngredient("Бекон", 110.0);
        addIngredient("Пепперони", 110.0);
        addIngredient("Соус 1000 островов", 20.9);
        addIngredient("Сырный соус", 20.7);
        addIngredient("Соус цезарь", 19.5);
        addIngredient("Острый соус", 28.3);
        addIngredient("Томатный соус", 18.0);
        addIngredient("Помидоры", 110.9);
        addIngredient("Грибы", 98.2);
        addIngredient("Красные перцы", 115.7);
        addIngredient("Огурцы", 93.5);
        addIngredient("Базилик", 100.0);
        addIngredient("Картофель", 109.4);
        addIngredient( "Моцарелла", 215.9);
        addIngredient("Чеддер", 171.0);
        addIngredient("Соус барбекью", 22.0);
        addIngredient("тест", 123.0);
    }

    private void addIngredient(String name, double price) {
        Ingredient ingredient = new Ingredient(name, price);
        this.storage.put(UUID.randomUUID(), ingredient);
    }

    public Ingredient findIngredientByName(String name) {
        return this.getAll().values().stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не найден ингридиент: " + name));
    }

}
