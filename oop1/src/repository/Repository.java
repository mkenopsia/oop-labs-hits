package repository;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Repository<T> {
    protected Map<UUID, T> storage = new HashMap<>();

    public void save(UUID id, T obj) {
        storage.put(id, obj);
    }

    public T get(UUID id) {
        return storage.get(id);
    }

    public Map<UUID, T> getAll() {
        return storage;
    }

    public void delete(UUID id) {
        storage.remove(id);
    }

    public void clear() {
        storage.clear();
    }
}
