package service;

import entity.ProgrammingLanguage;
import repository.ProgrammingLanguagesRepository;
import service.api.Processor;
import utils.InputValidator;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgrammingLanguageService implements Processor {
    private final ProgrammingLanguagesRepository repository;
    private static final ProgrammingLanguageService INSTANCE = new ProgrammingLanguageService(new ProgrammingLanguagesRepository());

    private ProgrammingLanguageService(ProgrammingLanguagesRepository repository) {
        this.repository = repository;
    }

    public static ProgrammingLanguageService getInstance() {
        return INSTANCE;
    }

    public ProgrammingLanguage getProgrammingLanguage(Scanner scanner) {
        scanner.nextLine();
        var languages = this.repository.getAll().values().stream().toList();
        Printer.printProgrammingLanguages(languages);
        int languageIndex = InputValidator.validateProgrammingLanguageInput(scanner, languages.size());

        return languages.get(languageIndex);
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
                    process(scanner);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
        return languages;
    }

    @Override
    public void process(Scanner scanner) {
        int option = -1;
        while (option != 0) {
            System.out.println("======Выберите действие:======");
            System.out.println("Показать все языки: 1");
            System.out.println("Добавить новый язык: 2");
            System.out.println("Удалить язык: 3");
            System.out.println("Изменить язык: 4");
            System.out.println("Выйти: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    Printer.printProgrammingLanguages(this.repository.getAll().values().stream().toList());
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите название языка: ");
                    String name = scanner.nextLine();
                    this.repository.save(name, new ProgrammingLanguage(name));

                    break;
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите название языка для удаления: ");
                    String nameForDeletion = scanner.nextLine();
                    this.repository.delete(nameForDeletion);

                    break;
                }
                case 4: {
                    var languages = this.repository.getAll().values().stream().toList();
                    Printer.printProgrammingLanguages(languages);
                    int index = InputValidator.validateProgrammingLanguageInput(scanner, languages.size());

                    System.out.println("Введите новое название: ");
                    String newName = scanner.nextLine();
                    this.repository.save(newName, new ProgrammingLanguage(newName));
                    this.repository.delete(languages.get(index).getName());

                    break;
                }
                case 0: {
                    return;
                }
            }
        }
    }
}
