package service;

import java.util.Scanner;

public class ModerationService {

    private static IngredientsService ingredientsService;
    private static PizzaBaseService pizzaBaseService;
    private static PizzaService pizzaService;
    private static PizzaBoardService pizzaBoardService;
    private static OrdersHistoryService ordersHistoryService;

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Ингредиенты: 1");
            System.out.println("Основы: 2");
            System.out.println("Пиццы: 3");
            System.out.println("Бортики: 4");
            System.out.println("Заказы: 5");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    ingredientsService.process(scanner);
                    break;
                case 2:
                    pizzaBaseService.process(scanner);
                    break;
                case 3:
                    pizzaService.process(scanner);
                    break;
                case 4:
                    pizzaBoardService.process(scanner);
                    break;
                case 5:
                    ordersHistoryService.process(scanner);
                    break;
                default:
                    System.out.println("Ошибка (нашмите 9 для выхода)");
            }
        }
    }

    public static IngredientsService getIngredientsService() {
        return ingredientsService;
    }

    public static void setIngredientsService(IngredientsService ingredientsService) {
        ModerationService.ingredientsService = ingredientsService;
    }

    public static PizzaBaseService getPizzaBaseService() {
        return pizzaBaseService;
    }

    public static void setPizzaBaseService(PizzaBaseService pizzaBaseService) {
        ModerationService.pizzaBaseService = pizzaBaseService;
    }

    public static PizzaService getPizzaService() {
        return pizzaService;
    }

    public static void setPizzaService(PizzaService pizzaService) {
        ModerationService.pizzaService = pizzaService;
    }

    public static PizzaBoardService getPizzaBoardService() {
        return pizzaBoardService;
    }

    public static void setPizzaBoardService(PizzaBoardService pizzaBoardService) {
        ModerationService.pizzaBoardService = pizzaBoardService;
    }

    public static void setOrdersHistoryService(OrdersHistoryService ordersHistoryService) {
        ModerationService.ordersHistoryService = ordersHistoryService;
    }
}
