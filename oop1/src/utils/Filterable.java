package utils;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

@FunctionalInterface
public interface Filterable<T> {
    Predicate<Map.Entry<UUID, T>> getPredicate();
}