package service;

import entity.ProgrammingLanguage;
import org.w3c.dom.ls.LSOutput;
import repository.ProgrammingLanguagesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProgrammingLanguageService {
    private final ProgrammingLanguagesRepository repository;
    private static final ProgrammingLanguageService INSTANCE = new ProgrammingLanguageService(new ProgrammingLanguagesRepository());

    private ProgrammingLanguageService(ProgrammingLanguagesRepository repository) {
        this.repository = repository;
    }

    public static ProgrammingLanguageService getInstance() {
        return INSTANCE;
    }

    public ProgrammingLanguage getProgrammingLanguage(Scanner scanner) {
        String inputOption = "";
        scanner.nextLine();
        while(!this.repository.getAll().containsKey(inputOption)) {
            System.out.println("Выберите язык программирования: ");
            for(Map.Entry<String, ProgrammingLanguage> entry : this.repository.getAll().entrySet()) {
                System.out.println(entry.getKey());
            }
            inputOption = scanner.nextLine();
        }

        return this.repository.get(inputOption);
    }

    public List<ProgrammingLanguage> getLanguages(Scanner scanner) {
        List<ProgrammingLanguage> languages = new ArrayList<>();
        int option = -1;
        while(option != 0) {
            System.out.println("=====Язык программирования=====");
            System.out.println("Выбрать из существующих: 1");
            System.out.println("Редактировать языки: 2");
            System.out.println("Выход: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    languages.add(getProgrammingLanguage(scanner));
                    break;
                }
                case 2: {
                    editLanguages(scanner);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
        return languages;
    }

    public void editLanguages(Scanner scanner) {
        int option = -1;
        while (option != 0) {
            System.out.println("======Выберите действие:======");
            System.out.println("Добавить новый язык: 1");
            System.out.println("Удалить язык: 2");
            System.out.println("Изменить язык: 3");
            System.out.println("Выйти: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите название языка: ");
                    String name = scanner.nextLine();
                    this.repository.save(name, new ProgrammingLanguage(name));

                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите название языка для удаления: ");
                    String nameForDeletion = scanner.nextLine();
                    this.repository.delete(nameForDeletion);

                    break;
                }
                case 3: {
                    String nameForEdit = "";
                    while(!this.repository.getAll().containsKey(nameForEdit)) {
                        System.out.println("Введите название языка для редактирования: ");
                        nameForEdit = scanner.nextLine();
                    }

                    System.out.println("Введите новое название: ");
                    String newName = scanner.nextLine();
                    this.repository.save(newName, new ProgrammingLanguage(newName));
                    this.repository.delete(nameForEdit);

                    break;
                }
                case 0: {
                    return;
                }
            }
        }
    }
}
