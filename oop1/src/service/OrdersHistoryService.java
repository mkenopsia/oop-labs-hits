package service;

import models.Order;
import repository.OrderRepository;
import utils.DateFilter;
import utils.Filterable;
import utils.Printer;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrdersHistoryService {

    private final OrderRepository orderRepository;

    public OrdersHistoryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void process(Scanner scanner) {
        int mode = 0;
        while (mode != 9) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Показать все заказы: 1");
            System.out.println("Отфильтровать заказы по дате: 2");
//            System.out.println("Изменить статус заказа: 3");
            System.out.println("Выход: 9");
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    Printer.printPizzaOrders(this.orderRepository.getAll());
                    break;
                case 2:
                    System.out.println("Укажите время и дату в формате гггг-мм--дд_чч:мм");
                    String stringDate = scanner.nextLine(); //2025-05-03_12:30:00
                    LocalDateTime time = LocalDateTime.parse(stringDate);


                    var filteredByDate = this.filter(orderRepository.getAll(), new DateFilter(time));
                    Printer.printPizzaOrders(filteredByDate);
                    break;
//                case 3: ;
                default:
                    System.out.println("Ошибка (нашмите 9 для выхода)");
            }
        }
    }

    private Map<UUID, Order> filter(Map<UUID, Order> map, Filterable<Order> filterable) {
        return map.entrySet().stream()
                .filter(filterable.getPredicate())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

}
