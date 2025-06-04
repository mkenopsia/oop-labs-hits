package service;

import entity.*;
import entity.Module;
import entity.answers.FreeResponseAnswer;
import entity.answers.QuizAnswer;
import entity.answers.SingleOptionBetweenSeveral;
import entity.answers.SingleOptionBetweenTwo;
import entity.tasks.Quiz;
import entity.tasks.Task;
import repository.SolutionRepository;
import utils.InputValidator;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void process(Scanner scanner) {
        int option = -1;
        while (option != 0) {
            System.out.println("Отправить решение: 1");
            System.out.println("Посмотреть отправленные решения: 2");
            System.out.println("Редактировать отправленные решения: 3");
            System.out.println("Удалить отправленное решение: 4");
            System.out.println("Назад: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    this.processSending(scanner);
                    break;
                }
                case 2: {
                    this.chooseClassForShowingSolutions(scanner);
                    break;
                }
                case 3: {
                    this.processEditing(scanner);
                    break;
                }
                case 4: {
                    this.processDeletion(scanner);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }

    public void chooseClassForShowingSolutions(Scanner scanner) {
        int option = -1;
        while (option != 0) {
            Printer.printClassroomNames();
            System.out.println("Выберите класс для просмотра отправленных решений: ");
            System.out.println("Назад: -1");

            int classroomIndex = InputValidator.validateClassroomInput(
                    scanner, ClassroomService.getInstance().getAllClassrooms().size());

            if(classroomIndex == -1) return;

            this.showSolutionsByTopic(scanner,
                    ClassroomService.getInstance().getAllClassrooms().values().stream().toList().get(classroomIndex).getTopics());
        }
    }

    private void showSolutionsByTopic(Scanner scanner, List<Topic> topics) {
        int option = -1;
        while(option != 0) {
            System.out.println("======Выберите действие:======");
            System.out.println("Посмотреть решения заданий по всем темам: 1");
            System.out.println("Посмотреть решения заданий по темам, которые могут видеть студенты: 2");
            System.out.println("Назад: 0");
            option = scanner.nextInt();

            if(option == 0) return;

            List<Topic> currTopics;
            if (option == 1) {
                currTopics = topics;
            } else if (option == 2) {
                currTopics = topics.stream().filter(Topic::isVisible).toList();
            } else {
                break;
            }

            Printer.printTopics(currTopics);
            int topicIndex = InputValidator.validateTopicInput(scanner, currTopics.size());

            if(currTopics.get(topicIndex) instanceof Module) {
                showSentSolutionsInModule(scanner, ((Module) currTopics.get(topicIndex)));
            } else {
                showSentSolutionsInTopic(scanner, currTopics.get(topicIndex));
            }
        }
    }

    private void showSentSolutionsInTopic(Scanner scanner, Topic topic) {
        if(topic.getTasks().isEmpty()) {
            System.out.println("Заданий в этой теме нет (пусто)");
            return;
        }

        Printer.printTasks(topic.getTasks());
        int taskIndex = InputValidator.validateTaskInput(scanner, topic.getTasks().size());
        this.showSolutionsByTask(topic.getTasks().get(taskIndex));
    }

    private void showSentSolutionsInModule(Scanner scanner, Module module) {
        int option = -1;
        while (option != 0) {
            System.out.println("======Выберите действие:======");
            System.out.println("Посмотреть решения на задания этого модуля: 1");
            System.out.println("Посмотреть решения на задания секций этого модуля: 2");
            System.out.println("Назад: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    showSentSolutionsInTopic(scanner, module);
                    break;
                }
                case 2: {
                    showSolutionsByTopic(scanner, new ArrayList<>(module.getSections()));
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }

    private void showSolutionsByTask(Task task) {
        if (this.repository.get(task.getName()) == null || this.repository.get(task.getName()).isEmpty()) {
            System.out.println("Решений по этой задаче нет (пусто)");
            return;
        }

        var solutions = this.repository.get(task.getName());
        Printer.printSolutions(solutions);
    }

    public void processSending(Scanner scanner) {
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

        processTopicsForSendingSolution(scanner, classrooms.get(classIndex).getTopics());
    }

    private void processTopicsForSendingSolution(Scanner scanner, List<Topic> topics) {
        Printer.printTopics(topics);
        int topicIndex = InputValidator.validateTopicInput(scanner, topics.size());

        if(topics.get(topicIndex) instanceof Module) {
            processModuleForSendingSolution(scanner, (Module) topics.get(topicIndex));
        } else {
            saveSolution(scanner, topics.get(topicIndex).getTasks());
        }
    }

    private void processModuleForSendingSolution(Scanner scanner, Module module) {
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

        Printer.printTasks(tasks);

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
            List<Solution> answers = new ArrayList<>();
            for(Question question : ((Quiz) chosenTask).getQuestions()) {
                question.print();
                System.out.println("Введите ответ: ");
                String solutionText = scanner.nextLine();

                if(question.getQuestionType() == QuestionType.CHOICE_BETWEEN_TWO) {
                    answers.add(new SingleOptionBetweenTwo(solutionText));
                } else if (question.getQuestionType() == QuestionType.CHOICE_BETWEEN_SEVERAL) {
                    answers.add(new SingleOptionBetweenSeveral(solutionText));
                } else {
                    answers.add(new FreeResponseAnswer(solutionText));
                }
            }
            solution = new QuizAnswer("", answers);
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

    public void processEditing(Scanner scanner) {
        int count = 0;
        var taskNames = this.repository.getAll().keySet().stream().toList();
        for(Map.Entry<String, List<Solution>> entry : this.repository.getAll().entrySet()) {
            System.out.println("Задание: " + entry.getKey() + ": " + count++);
            Printer.printSolutionsWithTabulation(entry.getValue());
        }

        System.out.println("Выберите номер задания для редактирования решений: ");
        int taskIndex = scanner.nextInt();
        while(taskIndex < 0 || taskIndex >= taskNames.size()) {
            System.out.println("Выберите корректный номер задания для редактирования решений: ");
            taskIndex = scanner.nextInt();
        }

        editTaskSolutions(scanner, this.repository.get(taskNames.get(taskIndex)), taskNames.get(taskIndex));
    }

    private void editTaskSolutions(Scanner scanner, List<Solution> solutions, String taskName) {
        Printer.printSolutionsWithTabulation(solutions);

        System.out.println("Выберите номер решения для редактирования: ");
        int solutionIndex = scanner.nextInt();
        while(solutionIndex < 0 || solutionIndex >= solutions.size()) {
            System.out.println("Выберите корректный номер решения для редактирования: ");
            solutionIndex = scanner.nextInt();
        }

        editSolution(scanner, solutions.get(solutionIndex), taskName);
    }

    private void editSolution(Scanner scanner, Solution solution, String taskName) {
        if(solution instanceof QuizAnswer) {
            System.out.println("Старое решение: " + "Выбранные варианты ответов: [" + ((QuizAnswer) solution).toString() + "]");
            List<Solution> answers = new ArrayList<>();
            for(Question question : ((Quiz) TaskService.getInstance().getTaskByName(taskName)).getQuestions()) {
                question.print();
                System.out.println("Введите новый ответ: ");
                String solutionText = scanner.nextLine();

                if(question.getQuestionType() == QuestionType.CHOICE_BETWEEN_TWO) {
                    answers.add(new SingleOptionBetweenTwo(solutionText));
                } else if (question.getQuestionType() == QuestionType.CHOICE_BETWEEN_SEVERAL) {
                    answers.add(new SingleOptionBetweenSeveral(solutionText));
                } else {
                    answers.add(new FreeResponseAnswer(solutionText));
                }
            }
            ((QuizAnswer) solution).setAnswers(answers);
        } else {
            System.out.println("Старое решение: " + ": " + solution.getSolutionText());
            String newSolutionText = scanner.nextLine();
            solution.setSolutionText(newSolutionText);
        }
    }

    public void processDeletion(Scanner scanner) {
        int count = 0;
        var taskNames = this.repository.getAll().keySet().stream().toList();
        for(Map.Entry<String, List<Solution>> entry : this.repository.getAll().entrySet()) {
            System.out.println("Задание: " + entry.getKey() + ": " + count++);
            Printer.printSolutionsWithTabulation(entry.getValue());
        }

        System.out.println("Выберите номер задания для удаления решений: ");
        int taskIndex = scanner.nextInt();
        while(taskIndex < 0 || taskIndex >= taskNames.size()) {
            System.out.println("Выберите корректный номер задания для удаления решений: ");
            taskIndex = scanner.nextInt();
        }

        deleteTaskSolution(scanner, this.repository.get(taskNames.get(taskIndex)));
    }

    private void deleteTaskSolution(Scanner scanner, List<Solution> solutions) {
        Printer.printSolutionsWithTabulation(solutions);

        System.out.println("Выберите номер решения для удаления: ");
        int solutionIndex = scanner.nextInt();
        while(solutionIndex < 0 || solutionIndex >= solutions.size()) {
            System.out.println("Выберите корректный номер решения для удаления: ");
            solutionIndex = scanner.nextInt();
        }

        solutions.remove(solutionIndex);
    }
}
