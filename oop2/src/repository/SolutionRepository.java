package repository;

import api.Printable;
import entity.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionRepository{
    protected Map<String, List<Solution>> storage = new HashMap<>();

    public void save(String id, List<Solution> obj) {
        storage.put(id, obj);
    }

    public List<Solution> get(String id) {
        return storage.get(id);
    }

    public Map<String, List<Solution>> getAll() {
        return storage;
    }

    public void delete(String id) {
        storage.remove(id);
    }

    public void clear() {
        storage.clear();
    }
}
