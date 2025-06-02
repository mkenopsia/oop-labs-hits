package service;

import repository.ClassroomRepository;
import utils.Printer;

import java.util.Scanner;

public class ClassroomService {
    private final ClassroomRepository repository;

    public ClassroomService(ClassroomRepository repository) {
        this.repository = repository;
    }

    public void process(Scanner scanner) {
        int option = -1;
        while(option != 0) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Показать текущие классы: 1");
            System.out.println("Перейти к конкретному классу: 2");
            System.out.println("Добавить новый класс: 3");
            System.out.println("Редактировать класс: 4");
            System.out.println("Удалить класс: 5");
            System.out.println("Назад: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    Printer.printRepositoryContent(repository);
                }
                case 2: {

                }
                case 3: {

                }
                case 4: {

                }
                case 5: {

                }
                case 0: {

                }
            }
        }
    }
}
