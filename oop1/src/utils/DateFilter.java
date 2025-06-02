package utils;

import models.Order;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class DateFilter implements Filterable<Order> {

    private final LocalDateTime targetDate;

    public DateFilter(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public Predicate<Map.Entry<UUID, Order>> getPredicate() {
        return entry -> entry.getValue().getDate().toLocalDate().isEqual(targetDate.toLocalDate());
    }
}
