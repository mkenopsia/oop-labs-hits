package utils;

import api.Printable;
import entity.Question;
import repository.CrudRepository;

import java.util.List;
import java.util.Map;

public class Printer {

    private Printer() {}

    public static <T extends Printable> void printRepositoryContent(CrudRepository<T> crudRepository) {
        for (Map.Entry<String, T> entry : crudRepository.getAll().entrySet()) {
            entry.getValue().print();
        }
    }

    public static <T extends Printable> void printRepositoryKeys(CrudRepository<T> crudRepository) {
        for (Map.Entry<String, T> entry : crudRepository.getAll().entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static <T extends Printable> void printElementsFromList(List<T> elements) {
        int count = 0;
        System.out.println("+++++++++++++++++++++");
        for(T element : elements) {
            System.out.print(count++ + ": ");
            element.print();
            System.out.println("+++++++++++++++++++++");
        }
    }
}
