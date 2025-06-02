package utils;

import models.Order;
import models.StandardEntity;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NameFilter<T extends StandardEntity>  implements Filterable<T> {

    private final String pattern;

    public NameFilter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Predicate<Map.Entry<UUID, T>> getPredicate() {
        return entry -> entry.getValue().getName().contains(pattern);
    }

    public Map<UUID, T> filter(Map<UUID, T> map) {
        return map.entrySet().stream()
                .filter(this.getPredicate())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

}
