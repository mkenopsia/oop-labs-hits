package utils;

import models.*;

import java.util.Map;
import java.util.UUID;

public class Printer {
    public static void printPizzaInCart(UUID id, Pizza pizza) {
        print(id, pizza);
        System.out.printf("Бортик: %s\n", pizza.getBoard().getName());
        System.out.printf("Размер: %s\n", pizza.getSize().getType());
    }

    public static void printPizzasInCart(Map<UUID, Pizza> storage) {
        Double price = 0.0;
        for (Map.Entry<UUID, Pizza> entry : storage.entrySet()) {
            printPizzaInCart(entry.getKey(), entry.getValue());
            price += entry.getValue().getPrice();
        }
        System.out.printf("==============Суммарная цена: %.2f==============\n", price);
    }

    public static void print(UUID id, Pizza pizza) {
        System.out.printf("==============Id: #%s==============\n", id);
        System.out.printf("Название: %s\n", pizza.getName());
        System.out.printf("Ингредиенты: %s\n", pizza.getIngredients().stream().map(Ingredient::getName).toList());
        System.out.printf("Основа: %s\n", pizza.getPizzaBase().getName());
        System.out.printf("Цена: %.2f\n", pizza.getPrice());
    }

    public static void printPizzas(Map<UUID, Pizza> storage) {
        for (Map.Entry<UUID, Pizza> entry : storage.entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }

    public static void print(UUID id, StandardEntity ingredient) {
        System.out.printf("==============Id: #%s==============\n", id);
        System.out.printf("Название: %s\n", ingredient.getName());
        System.out.printf("Цена: %.2f\n", ingredient.getPrice());
    }

    public static void  printIngredients(Map<UUID, Ingredient> storage) {
        for (Map.Entry<UUID, Ingredient> entry : storage.entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }

    public static void  printBases(Map<UUID, PizzaBase> storage) {
        for (Map.Entry<UUID, PizzaBase> entry : storage.entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }

    public static void print(UUID id, PizzaBoard pizzaBoard) {
        System.out.printf("==============Id: #%s==============\n", id);
        System.out.printf("Название: %s\n", pizzaBoard.getName());
        System.out.printf("Цена: %s\n", pizzaBoard.getPrice());
        System.out.printf("Ингридиенты: %s\n", pizzaBoard.getIngredients().stream().map(Ingredient::getName).toList());
        System.out.printf("Совместимые пиццы: %s\n", pizzaBoard.getAvailablePizza().stream().map(Pizza::getName).toList());
    }

    public static void printPizzaBoards(Map<UUID, PizzaBoard> storage) {
        for (Map.Entry<UUID, PizzaBoard> entry : storage.entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }

    public static void printPizzaSizes() {
        for(var size : PizzaSize.values()){
            System.out.println(size.getType());
        }
    }

    public static void print(UUID id, Order order) {
        System.out.printf("==============Id: #%s==============\n", id);
        System.out.printf("Дата: %s\n", order.getDate());
        System.out.printf("Статус: %s\n", order.getStatus());
        System.out.printf("Комментарий: %s\n", order.getComment());
        System.out.println("Пиццы: ");
        for(var pizza : order.getPizzasForOrder()) {
            System.out.printf("Название: %s\n", pizza.getName());
            System.out.printf("Ингредиенты: %s\n", pizza.getIngredients().stream().map(Ingredient::getName).toList());
            System.out.printf("Основа: %s\n", pizza.getPizzaBase().getName());
            System.out.printf("Цена: %.2f\n", pizza.getPrice());
        }
        System.out.printf("==============Сумма заказа: %s==============\n", order.getPrice());
    }

    public static void printPizzaOrders(Map<UUID, Order> storage) {
        for (Map.Entry<UUID, Order> entry : storage.entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }
}
