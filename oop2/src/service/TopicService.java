package service;

import entity.Module;
import entity.Section;
import entity.Topic;
import utils.InputValidator;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopicService {
    private static final TopicService INSTANCE = new TopicService();

    private TopicService() {}

    public static TopicService getInstance() {
        return INSTANCE;
    }

    public Topic process(Scanner scanner) {
        int option = -1;
        while(option != 0) {
            System.out.println("==============Выберите действие==============");
            System.out.println("Добавить модуль: 1");
            System.out.println("Добавить секцию: 2");
            System.out.println("Назад: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    return createModule(scanner);
                }
                case 2: {
                    return createSection(scanner);
                }
                case 0: {
                    return null;
                }
            }
        }
        return null;
    }

    private Module createModule(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название модуля: ");
        String name = scanner.nextLine();
        System.out.println("Виден студентам: (д/н)");
        boolean isVisible = scanner.nextLine().equals("д");
        return new Module(name, isVisible, new ArrayList<>(), new ArrayList<>());
    }

    private Section createSection(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название секции: ");
        String name = scanner.nextLine();
        System.out.println("Виден студентам: (д/н)");
        boolean isVisible = scanner.nextLine().equals("д");
        return new Section(name, isVisible, new ArrayList<>());
    }

    public void editTopic(Scanner scanner, Topic topicForEdit) {
        boolean isModule = (topicForEdit instanceof Module);
        int option = -1;
        while(option != 0) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Добавить задание: 1");
            System.out.println("Удалить задание: 2");
            System.out.println("Изменить задание: 3");
            System.out.println("Изменить название: 4");
            System.out.println("Изменить видимость для студентов: 5");
            if(isModule) {
                System.out.println("Редактировать секции: 6");
                System.out.println("Добавить секцию: 7");
            }
            System.out.println("Выход: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    topicForEdit.getTasks().add(TaskService.getInstance().createTask(scanner));
                    break;
                }
                case 2: {
                    Printer.printTasks(topicForEdit.getTasks());
                    int indexForDeletion = InputValidator.validateTaskInput(scanner, topicForEdit.getTasks().size());

                    TaskService.getInstance().deleteTask(topicForEdit.getTasks().get(indexForDeletion));
                    topicForEdit.getTasks().remove(indexForDeletion);
                    break;
                }
                case 3: {
                    TaskService.getInstance().editTasks(scanner, topicForEdit);
                    break;
                }
                case 4: {
                    System.out.println("Введите новое название темы: ");
                    String newName = scanner.nextLine();
                    topicForEdit.setName(newName);
                    break;
                }
                case 5: {
                    System.out.println("Отображать тему студентам?: (д/н)");
                    scanner.nextLine();
                    String choice = scanner.nextLine();
                    topicForEdit.setVisible((choice.equals("д")));
                    break;
                }
                case 6: {
                    if(!isModule) break;
                    if(((Module) topicForEdit).getSections().isEmpty()) {
                        System.out.println("Секций нет");
                        break;
                    }

                    editSection(scanner, ((Module) topicForEdit).getSections());
                    break;
                }
                case 7: {
                    if(!isModule) break;
                    ((Module) topicForEdit).getSections().add(createSection(scanner));
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }

    private void editSection(Scanner scanner, List<Section> sections) {
        Printer.printSections(sections);

        int index = InputValidator.validateSectionInput(scanner, sections.size());

        editTopic(scanner, sections.get(index));
    }

}
