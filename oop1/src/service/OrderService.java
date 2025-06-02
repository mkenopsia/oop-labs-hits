package service;

import models.Order;
import models.Pizza;
import repository.OrderRepository;
import repository.PizzasInCartRepository;
import utils.Printer;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class OrderService {

    private static PizzasInCartRepository pizzasInCartRepository;
    private static OrderRepository orderRepository;

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Показать пиццы в корзине: 1");
            System.out.println("Удалить пиццу из корзины: 2");
            System.out.println("Сделать заказ: 3");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    Printer.printPizzasInCart(pizzasInCartRepository.getAll());
                    break;
                case 2:
                    this.delete(scanner);
                    break;
                case 3:
                    this.makeOrder(scanner);
                    break;
                default:
                    System.out.println("Ошибка (нажмите 9 для выхода)");
            }
            System.out.println("Выход: 9");
        }
    }

    public void makeOrder(Scanner scanner) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setUserId(UUID.randomUUID());

        scanner.nextLine();
        System.out.println("Укажите время и дату доставки в формате гггг-мм--дд_чч:мм (q - доставить в течение 30 минут");
        String stringDate = scanner.nextLine(); //2025-05-03_12:30:00

        LocalDateTime date = stringDate.equals("q") ? LocalDateTime.now().plusMinutes(30) :
                LocalDateTime.parse(String.join("T", stringDate.split("_")[0],stringDate.split("_")[1]));
        order.setDate(date);
        order.setStatus("NEW");

        System.out.println("Добавьте комментарий к заказу (q - не добавлять): ");
        String comment = scanner.nextLine();
        order.setComment(comment.equals("q") ? "" : comment);

        order.setPizzasForOrder(pizzasInCartRepository.getAll().values().stream().toList());
        order.setPrice(order.getPizzasForOrder().stream().mapToDouble(Pizza::getPrice).sum());

        orderRepository.save(UUID.randomUUID(), order);
        pizzasInCartRepository.clear();
    }

    public void delete(Scanner scanner) {
        Printer.printPizzasInCart(pizzasInCartRepository.getAll());
        scanner.nextLine();
        System.out.println("Введите айди пиццы для удаления из корзины:");
        String id = scanner.nextLine();

        pizzasInCartRepository.delete(UUID.fromString(id));
    }

    public static void setPizzasInCartRepository(PizzasInCartRepository pizzasInCartRepository) {
        OrderService.pizzasInCartRepository = pizzasInCartRepository;
    }

    public static void setOrderRepository(OrderRepository orderRepository) {
        OrderService.orderRepository = orderRepository;
    }
}
