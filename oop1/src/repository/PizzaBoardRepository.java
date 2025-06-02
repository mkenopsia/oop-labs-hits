package repository;

import models.Ingredient;
import models.Pizza;
import models.PizzaBoard;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PizzaBoardRepository extends Repository<PizzaBoard> {

    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public PizzaBoardRepository(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
        addBoards();
    }

    private void addBoards() {
        addBoard("Сырный",
                50.0,
                List.of(ingredientRepository.findIngredientByName("Моцарелла")),
                List.of(this.pizzaRepository.findPizzaByName("Бекон"),
                        this.pizzaRepository.findPizzaByName("Острая"),
                        this.pizzaRepository.findPizzaByName("Цыплёнок - набираем массу"),
                        this.pizzaRepository.findPizzaByName("Ветчина и грибы"))
        );

        addBoard("Сочный",
                60.0,
                List.of(ingredientRepository.findIngredientByName("Моцарелла"), ingredientRepository.findIngredientByName("Ветчина")),
                List.of(this.pizzaRepository.findPizzaByName("Бекон"),
                        this.pizzaRepository.findPizzaByName("Острая"),
                        this.pizzaRepository.findPizzaByName("Цыплёнок - набираем массу"),
                        this.pizzaRepository.findPizzaByName("Ветчина и грибы"),
                        this.pizzaRepository.findPizzaByName("Пепперони")));
        addBoard("Стандарт",
                0.0,
                List.of(),
                List.of());
    }

    private void addBoard(String name, Double price, List<Ingredient> ingredients, List<Pizza> availablePizzas) {
        PizzaBoard pizzaBoard = new PizzaBoard(name, price, ingredients, availablePizzas);
        this.storage.put(UUID.randomUUID(), pizzaBoard);
    }

    public PizzaBoard findBoardByName(String name) {
       return  this.storage.values().stream()
                .filter(board -> board.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public IngredientRepository getIngredientRepository() {
        return ingredientRepository;
    }
}
