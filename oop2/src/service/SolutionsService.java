package service;

import entity.*;
import entity.Module;
import entity.answers.Answer;
import entity.tasks.Quiz;
import entity.tasks.Task;
import repository.SolutionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SolutionsService {
    private final SolutionRepository repository;
    private static final SolutionsService INSTANCE = new SolutionsService(new SolutionRepository());

    private SolutionsService(SolutionRepository repository) {
        this.repository = repository;
    }

    public static SolutionsService getInstance() {
        return INSTANCE;
    }

    public void showSolutions(Scanner scanner) {
        List<Classroom> classrooms = ClassroomService.getInstance().getAllClassrooms().values().stream().toList();
        int count = 0;
        for (Classroom classroom : ClassroomService.getInstance().getAllClassrooms().values()) {
            System.out.println(classroom.getClassroomName() + ": " + count++);
        }
        System.out.println("Выберите номер класса: ");
        System.out.println("Назад: -1");
        int classIndex = scanner.nextInt();
        if(classIndex == -1) return;
        if(classIndex < 0 || classIndex >= classrooms.size()) {
            System.out.println("\n\nВведите корректный номер класса\n\n");
            return;
        }

        showSolutionsInTopics(scanner, classrooms.get(classIndex));
    }

    private void showSolutionsInTopics(Scanner scanner, Classroom classroom) {
        List<Topic> topics = new ArrayList<>();
        for(Topic topic : classroom.getTopics()) {
            if(topic instanceof Module) {
                topics.addAll(((Module) topic).getSections());
            }
            topics.add(topic);
        }

        List<String> topicNames = topics.stream().map(Topic::getName).toList();

        int count = 0;
        for(String topicName : topicNames) {
            System.out.println(count++ + ": " + topicName);
        }
        System.out.println("Выберите тему для отображения решений по ней: ");
        int chosenTopic = scanner.nextInt();

        while(chosenTopic < 0 || chosenTopic >= topicNames.size()) {
            System.out.println("Выберите корректную тему для отображения решений по ней: ");
            chosenTopic = scanner.nextInt();
        }

        

        if(this.repository.getAll().containsKey(topicNames.get(chosenTopic))) {
            System.out.println("Решения: ");
            for(Solution solution : this.repository.get(topicNames.get(chosenTopic))) {
                solution.print();
            }
        } else {
            System.out.println("Решений на эту тему нет");
        }
    }

    public void process(Scanner scanner) {
        List<Classroom> classrooms = ClassroomService.getInstance().getAllClassrooms().values().stream().toList();
        int count = 0;
        for (Classroom classroom : ClassroomService.getInstance().getAllClassrooms().values()) {
            System.out.println(classroom.getClassroomName() + ": " + count++);
        }
        System.out.println("Выберите номер класса: ");
        System.out.println("Назад: -1");
        int classIndex = scanner.nextInt();
        if(classIndex == -1) return;
        if(classIndex < 0 || classIndex >= classrooms.size()) {
            System.out.println("\n\nВведите корректный номер класса\n\n");
            return;
        }

        processTopics(scanner, classrooms.get(classIndex).getTopics());
    }

    private void processTopics(Scanner scanner, List<Topic> topics) {
        int count = 0;
        for(Topic topic : topics) {
            if(topic instanceof Module) {
                System.out.println("- Модуль: " + topic.getName() + ": " + count++);
            } else {
                System.out.println("- Секция: " + topic.getName() + ": " + count++);
            }
        }
        System.out.println("Выберите номер темы: ");
        int topicIndex = scanner.nextInt();
        if(topicIndex < 0 || topicIndex >= topics.size()) {
            System.out.println("\n\nВведите корректный номер темы\n\n");
            return;
        }

        if(topics.get(topicIndex) instanceof Module) {
            processModule(scanner, (Module) topics.get(topicIndex));
        }
    }

    private void processModule(Scanner scanner, Module module) {
        int option = -1;
        while(option != 0) {
            System.out.println("======Выберите действие:======");
            System.out.println("Выбрать задания текущего модуля для отправки решения: 1");
            System.out.println("Выбрать секцию текущего модуля для отправки решения: 2");
            System.out.println("Выход: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    saveSolution(scanner, module.getTasks());
                    break;
                }
                case 2: {
                    int count = 0;
                    for(Section section : module.getSections()) {
                        System.out.println(count++ + ": " + section.getName());
                    }

                    int sectionIndex = scanner.nextInt();
                    while(sectionIndex < 0 || sectionIndex >= module.getSections().size()) {
                        System.out.println("Введите корректный номер секции для отправки решения: ");
                        sectionIndex = scanner.nextInt();
                    }

                    saveSolution(scanner, module.getSections().get(sectionIndex).getTasks());
                }
                case 0: {
                    break;
                }
            }
        }
    }

    private void saveSolution(Scanner scanner, List<Task> tasks) {
        if(tasks.isEmpty()) {
            System.out.println("!___Заданий нет___!");
            return;
        }

        int count = 0;
        for(Task task : tasks) {
            System.out.print(count++ + ": ");
            task.print();
        }
        System.out.println("Выберите номер задания для отправки решения: ");
        int taskIndex = scanner.nextInt();
        while(taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("Введите корректный номер задания для отправки решения: ");
            taskIndex = scanner.nextInt();
        }

        Task chosenTask = tasks.get(taskIndex);

        Solution solution;

        scanner.nextLine();
        if(chosenTask instanceof Quiz) {
            List<String> answers = new ArrayList<>();
            for(Question question : ((Quiz) chosenTask).getQuestions()) {
                question.print();
                System.out.println("Введите ответ: ");
                String answer = scanner.nextLine();
                answers.add(answer);
            }
            solution = new Answer("", answers);
        } else {
            chosenTask.print();
            System.out.println("Введите ответ: ");
            String answer = scanner.nextLine();
            solution = new Solution(answer);
        }

        List<Solution> solutions = this.repository.getAll().getOrDefault(chosenTask.getName(), new ArrayList<>());
        solutions.add(solution);
        this.repository.save(chosenTask.getName(), solutions);
    }
}
