package utils;

import api.Printable;
import repository.CrudRepository;

import java.util.Map;

public class Printer {

    private Printer() {}

    public static <T extends Printable> void printRepositoryContent(CrudRepository<T> crudRepository) {
        for (Map.Entry<String, T> entry : crudRepository.getAll().entrySet()) {
            entry.getValue().print();
        }
    }
}
