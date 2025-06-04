package service;

import entity.ProgrammingLanguage;
import entity.Question;
import entity.Topic;
import entity.tasks.AlgoTask;
import entity.tasks.Quiz;
import entity.tasks.Task;
import entity.tasks.TaskWithRepository;
import repository.TaskRepository;

import java.util.List;
import java.util.Scanner;

public class TaskService {
    private final TaskRepository taskRepository;
    private static final TaskService INSTANCE = new TaskService(new TaskRepository());

    private TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }

    public void editTasks(Scanner scanner, Topic topic) {
        int count = 0;
        for(Task task : topic.getTasks()) {
            System.out.println(task.getName() + ": " + count++);
        }
        System.out.println("Выберите номер задания для редактирования: ");
        int taskIndex = scanner.nextInt();
        Task task = topic.getTasks().get(taskIndex);
        if(task instanceof  AlgoTask) {
            this.editAlgoTask(scanner, (AlgoTask) task);
        } else if(task instanceof TaskWithRepository) {
            this.editTaskWithRepository(scanner, (TaskWithRepository) task);
        } else {
            this.editQuiz(scanner, (Quiz) task);
        }
    }

    private void editAlgoTask(Scanner scanner, AlgoTask task) {
        int option = -1;
        String oldName = task.getName();
        String newName = null;
        while (option != 0) {
            System.out.println("======Выберите параметр для редактирования======");
            System.out.println("Изменить название: 1");
            System.out.println("Изменить текст: 2");
            System.out.println("Изменить пример: 3");
            System.out.println("Изменить языки программирования: 4");
            System.out.println("Выход: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите новое название: ");
                    newName = scanner.nextLine();
                    task.setName(newName);
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите новый текст задания: ");
                    String newText = scanner.nextLine();
                    task.setText(newText);
                    break;
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите новый пример: ");
                    String newExample = scanner.nextLine();
                    task.setExample(newExample);
                    break;
                }
                case 4: {
                    scanner.nextLine();
                    List<ProgrammingLanguage> languages = ProgrammingLanguageService.getInstance().getLanguages(scanner);
                    task.setLanguages(languages);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }

        if(newName != null) {
            this.taskRepository.delete(oldName);
            oldName = newName;
        }

        this.taskRepository.save(oldName, task);
    }

    private void editTaskWithRepository(Scanner scanner, TaskWithRepository task) {
        int option = -1;
        String oldName = task.getName();
        String newName = null;
        while (option != 0) {
            System.out.println("======Выберите параметр для редактирования======");
            System.out.println("Изменить название: 1");
            System.out.println("Изменить текст: 2");
            System.out.println("Изменить пример: 3");
            System.out.println("Изменить ссылку на репозиторий: 4");
            System.out.println("Выход: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите новое название: ");
                    newName = scanner.nextLine();
                    task.setName(newName);
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите новый текст задания: ");
                    String newText = scanner.nextLine();
                    task.setText(newText);
                    break;
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите новый пример: ");
                    String newExample = scanner.nextLine();
                    task.setExample(newExample);
                    break;
                }
                case 4: {
                    scanner.nextLine();
                    System.out.println("Введите новую ссылку на репозиторий: ");
                    String newLink = scanner.nextLine();
                    task.setRepositoryLink(newLink);
                    break;
                }
            }
        }

        if(newName != null) {
            this.taskRepository.delete(oldName);
            oldName = newName;
        }

        this.taskRepository.save(oldName, task);
    }

    private void editQuiz(Scanner scanner, Quiz task) {
        int option = -1;
        String oldName = task.getName();
        String newName = null;
        while (option != 0) {
            System.out.println("======Выберите параметр для редактирования======");
            System.out.println("Изменить название: 1");
            System.out.println("Изменить текст: 2");
            System.out.println("Изменить пример: 3");
            System.out.println("Изменить вопросы: 4");
            System.out.println("Выход: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите новое название: ");
                    newName = scanner.nextLine();
                    task.setName(newName);
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите новый текст задания: ");
                    String newText = scanner.nextLine();
                    task.setText(newText);
                    break;
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите новый пример: ");
                    String newExample = scanner.nextLine();
                    task.setExample(newExample);
                    break;
                }
                case 4: {
                    scanner.nextLine();
                    QuestionService.getInstance().editQuestions(scanner, task);
                    break;
                }
            }
        }

        if(newName != null) {
            this.taskRepository.delete(oldName);
            oldName = newName;
        }

        this.taskRepository.save(oldName, task);
    }

    public Task createTask(Scanner scanner) {
        int option = -1;
        while(option != 0) {
            System.out.println("======Выберите тип задания:======");
            System.out.println("Задание по алгоритмике: 1");
            System.out.println("Задание с репозиторием: 2");
            System.out.println("Опрос: 3");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    return createAlgoTask(scanner);
                }
                case 2: {
                    return createTaskWithRepository(scanner);
                }
                case 3: {
                    return createQuiz(scanner);
                }
            }
        }
        return null;
    }

    private AlgoTask createAlgoTask(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название задания: ");
        String name = scanner.nextLine();
        System.out.println("Введите текст задания: ");
        String text = scanner.nextLine();
        System.out.println("Укажите пример задания: ");
        String example = scanner.nextLine();

        List<ProgrammingLanguage> languages = ProgrammingLanguageService.getInstance().getLanguages(scanner);

        AlgoTask task = new AlgoTask(name, text, example, languages);
        this.taskRepository.save(name, task);
        return task;
    }

    private TaskWithRepository createTaskWithRepository(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название задания: ");
        String name = scanner.nextLine();
        System.out.println("Введите текст задания: ");
        String text = scanner.nextLine();
        System.out.println("Укажите пример задания: ");
        String example = scanner.nextLine();
        System.out.println("Укажите ссылку на репозиторий: ");
        String link = scanner.nextLine();

        TaskWithRepository task = new TaskWithRepository(name, text, example, link);
        this.taskRepository.save(name, task);
        return task;
    }

    private Quiz createQuiz(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название опроса: ");
        String name = scanner.nextLine();
        System.out.println("Введите пояснение к опросу: ");
        String text = scanner.nextLine();

        List<Question> questions = QuestionService.getInstance().createQuestions(scanner);

        Quiz task = new Quiz(name, text, "", questions);
        this.taskRepository.save(name, task);
        return task;
    }

    public void deleteTask(Task task) {
        this.taskRepository.delete(task.getName());
    }

    public Task getTaskByName(String taskName) {
        return this.taskRepository.get(taskName);
    }
}
