package service;

import entity.Classroom;
import entity.Topic;
import org.w3c.dom.ls.LSOutput;
import repository.ClassroomRepository;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClassroomService {
    private final ClassroomRepository repository;
    private static final ClassroomService INSTANCE = new ClassroomService(new ClassroomRepository());

    private ClassroomService(ClassroomRepository repository) {
        this.repository = repository;
    }

    public static ClassroomService getInstance() {
        return INSTANCE;
    }

    public void process(Scanner scanner) {
        int option = -1;
        while(option != 0) {
            System.out.println("==============Выберите режим==============");
            System.out.println("Показать текущие классы: 1");
            System.out.println("Перейти к конкретному классу для редактирования: 2");
            System.out.println("Добавить новый класс: 3");
            System.out.println("Удалить класс: 4");
            System.out.println("Назад: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    Printer.printRepositoryContent(repository);
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    processClassroom(scanner);
                    break;
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите название для класса:");
                    String name = scanner.nextLine();
                    this.repository.save(name, new Classroom(name, new ArrayList<>()));
                    break;
                }
                case 4: {
                    deleteClassroom(scanner);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }

    private void deleteClassroom(Scanner scanner) {
        Printer.printRepositoryKeys(repository);
        String className = "";
        while(!repository.getAll().containsKey(className)) {
            System.out.println("Введите название класса для удаления: ");
            className = scanner.nextLine();
        }

        this.repository.delete(className);
    }

    private void processClassroom(Scanner scanner) {
        Printer.printRepositoryKeys(this.repository);
        String className = "";
        while(!this.repository.getAll().containsKey(className)) {
            System.out.println("Введите название класса: ");
            className = scanner.nextLine();
        }

        Classroom classroom = this.repository.get(className);
        int option = -1;
        while(option != 0) {
            classroom.print();
            System.out.println("==============Выберите режим==============");
            System.out.println("Изменить название класса: 1");
            System.out.println("Добавить тему (модуль/секцию): 2");
            System.out.println("Удалить тему: 3");
            System.out.println("Редактировать тему: 4");
            System.out.println("Назад: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите новое название для класса:");
                    String newClassname = scanner.nextLine();
                    classroom.setClassroomName(newClassname);
                    break;
                }
                case 2: {
                    Topic newTopic =  TopicService.getInstance().process(scanner);
                    if(newTopic != null) {
                        classroom.addTopic(newTopic);
                    }
                    break;
                }
                case 3: {
                    String topicForDeletion = "";
                    while(!classroom.getTopics().contains(topicForDeletion)) {
                        topicForDeletion = scanner.nextLine();
                    }
                    classroom.removeTopic(topicForDeletion);
                    break;
                }
                case 4: {
                    if(classroom.getTopics().isEmpty()) {
                        System.out.println("Тем нет");
                        break;
                    }
                    for(Topic topic : classroom.getTopics()) {
                        System.out.println(topic.getName());
                    }
                    String nameTopicForEdit = "";
                    scanner.nextLine();
                    while(!classroom.getTopics().stream().map(Topic::getName).collect(Collectors.toSet()).contains(nameTopicForEdit)) {
                        System.out.println("Выберите тему для редактирования: ");
                        nameTopicForEdit = scanner.nextLine();
                    }
                    final String finalNameTopicForEdit = nameTopicForEdit;
                    Topic topicForEdit = classroom.getTopics().stream().filter(t -> t.getName().equals(finalNameTopicForEdit)).findFirst().get();

                    TopicService.getInstance().editTopic(scanner, topicForEdit);
                    break;
                }
            }
        }
    }

    public Map<String, Classroom> getAllClassrooms() {
        return this.repository.getAll();
    }
}
