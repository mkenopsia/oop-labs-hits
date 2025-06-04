package service;

import entity.Module;
import entity.Section;
import entity.Topic;
import entity.tasks.Task;
import entity.tasks.TaskWithRepository;

import java.util.ArrayList;
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
        String input = "";
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
                    int pos = 0;
                    for(Task task : topicForEdit.getTasks()) {
                        System.out.println(task.getName() + ": " + pos);
                    }

                    System.out.println("Введите номер задания для удаления: ");
                    int indexForDeletion = scanner.nextInt();

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
                    int pos = 0;
                    for(Section section : ((Module) topicForEdit).getSections()) {
                        System.out.println(section.getName() + ": " + pos);
                    }

                    System.out.println("Выберите номер секции для редактирования: ");
                    int index = scanner.nextInt();

                    editTopic(scanner, ((Module) topicForEdit).getSections().get(index));
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


}
