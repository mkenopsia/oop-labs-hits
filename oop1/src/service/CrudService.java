package service;

import models.StandardEntity;

import java.util.Scanner;

public interface CrudService {

    void save(Scanner scanner);

    void update(Scanner scanner);

    void delete(Scanner scanner);

}
