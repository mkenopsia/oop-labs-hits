package service;

import entity.Classroom;
import entity.Topic;
import repository.ClassroomRepository;
import service.api.Processor;
import utils.InputValidator;
import utils.Printer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ClassroomService implements Processor {
    private final ClassroomRepository repository;
    private static final ClassroomService INSTANCE = new ClassroomService(new ClassroomRepository());

    private ClassroomService(ClassroomRepository repository) {
        this.repository = repository;
    }

    public static ClassroomService getInstance() {
        return INSTANCE;
    }

    @Override
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
                    System.out.println("Введите название для нового класса:");
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
        var classrooms = this.repository.getAll().values().stream().toList();

        if(classrooms.isEmpty()) {
            System.out.println("Классов нет");
            return;
        }

        Printer.printClassrooms(classrooms);
        int classroomIndex = InputValidator.validateClassroomInput(scanner, classrooms.size());

        Classroom classroom = classrooms.get(classroomIndex);
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
                    var topics = classroom.getTopics();
                    if(topics.isEmpty()) {
                        System.out.println("Тем нет");
                        break;
                    }

                    Printer.printTopics(topics);

                    int topicIndex = InputValidator.validateTopicInput(scanner, topics.size());

                    TopicService.getInstance().editTopic(scanner, topics.get(topicIndex));
                    break;
                }
            }
        }
    }

    public Map<String, Classroom> getAllClassrooms() {
        return this.repository.getAll();
    }
}
