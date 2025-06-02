package repository;

import api.Printable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CrudRepository<T extends Printable> {
    protected Map<String, T> storage = new HashMap<>();

    public void save(String id, T obj) {
        storage.put(id, obj);
    }

    public T get(String id) {
        return storage.get(id);
    }

    public Map<String, T> getAll() {
        return storage;
    }

    public void delete(String id) {
        storage.remove(id);
    }

    public void clear() {
        storage.clear();
    }
}
