import repository.*;
import service.*;
import utils.Refresher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        init();

        ModerationService moderationService = new ModerationService();
        OrderService orderService = new OrderService();
        CartService cartService = new CartService();

        int mode = 0;
        while(mode != 9) {
            System.out.println("Выберите режим:");
            System.out.println("Выбрать пиццу для заказа: 1");
            System.out.println("Корзина: 2");
            System.out.println("Модерация: 3");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    cartService.process(scanner);
                    break;
                case 2:
                    orderService.process(scanner);
                    break;
                case 3:
                    moderationService.process(scanner);
                    break;
                default:
                    System.out.println("Ошибка (нашмите 9 для выхода)");
            }
        }

        scanner.close();
    }

    static void init() {
        var ingredientRepository = new IngredientRepository();
        var pizzaBaseRepository = new PizzaBaseRepository();
        var pizzaRepository = new PizzaRepository(ingredientRepository, pizzaBaseRepository);
        var pizzaBoardRepository = new PizzaBoardRepository(ingredientRepository, pizzaRepository);
        var pizzasInCartRepository = new PizzasInCartRepository();
        var orderRepository = new OrderRepository();
        var orderHistoryService = new OrdersHistoryService(orderRepository);

        ModerationService.setIngredientsService(new IngredientsService(ingredientRepository));
        ModerationService.setPizzaBaseService(new PizzaBaseService(pizzaBaseRepository));
        ModerationService.setPizzaService(new PizzaService(pizzaRepository));
        ModerationService.setPizzaBoardService(new PizzaBoardService(pizzaBoardRepository));
        ModerationService.setOrdersHistoryService(orderHistoryService);
        Refresher.setIngredientRepository(ingredientRepository);
        Refresher.setPizzaRepository(pizzaRepository);
        OrderService.setOrderRepository(orderRepository);
        OrderService.setPizzasInCartRepository(pizzasInCartRepository);
        CartService.setPizzasInCartRepository(pizzasInCartRepository);
        CartService.setIngredientRepository(ingredientRepository);
        CartService.setPizzaBoardRepository(pizzaBoardRepository);
        CartService.setPizzaRepository(pizzaRepository);
        CartService.setPizzaBaseRepository(pizzaBaseRepository);

    }
}