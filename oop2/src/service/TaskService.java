package service;

import entity.*;
import entity.Module;
import entity.tasks.AlgoTask;
import entity.tasks.Quiz;
import entity.tasks.Task;
import entity.tasks.TaskWithRepository;

import java.util.*;

public class TaskService { private static final TaskService INSTANCE = new TaskService();

    private TaskService() {
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }

    public Map<String, List<Task>> getAllTasksFromClassrooms() {
//        Deque<Topic> queue = new ArrayDeque<>(classroom.getTopics());

        Map<String, List<Task>>  tasksByTopics = new HashMap<>();

        for(Classroom classroom : ClassroomService.getInstance().getAllClassrooms().values()) {
            Deque<Topic> queue = new ArrayDeque<>(classroom.getTopics());
            List<Task> currTasks = new ArrayList<>();
            while(!queue.isEmpty()) {
                Topic currTopic = queue.pop();
                if(currTopic instanceof Module) {
                    queue.addAll(((Module) currTopic).getSections());
                }
                currTasks.addAll(currTopic.getTasks());
            }
            tasksByTopics.put(classroom.getClassroomName(), currTasks);
        }

        return tasksByTopics;
    }

    public List<Task> getAllTasksFromClassroom(Classroom classroom) {
        Deque<Topic> queue = new ArrayDeque<>(classroom.getTopics());

        List<Task> tasks = new ArrayList<>();
        while(!queue.isEmpty()) {
            Topic currTopic = queue.pop();
            if(currTopic instanceof Module) {
                queue.addAll(((Module) currTopic).getSections());
            }
            tasks.addAll(currTopic.getTasks());
        }
        return tasks;
    }

//    public List<Task> getAllTasksFromTopic(Topic topic) {
//        Deque<Topic> queue = new ArrayDeque<>(classroom.getTopics());
//
//        List<Task> tasks = new ArrayList<>();
//        while(!queue.isEmpty()) {
//            Topic currTopic = queue.pop();
//            if(currTopic instanceof Module) {
//                queue.addAll(((Module) currTopic).getSections());
//            }
//            tasks.addAll(currTopic.getTasks());
//        }
//        return tasks;
//    }

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
                    String newName = scanner.nextLine();
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
    }

    private void editTaskWithRepository(Scanner scanner, TaskWithRepository task) {
        int option = -1;
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
                    String newName = scanner.nextLine();
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
    }

    private void editQuiz(Scanner scanner, Quiz task) {
        int option = -1;
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
                    String newName = scanner.nextLine();
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

        return new AlgoTask(name, text, example, languages);
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

        return new TaskWithRepository(name, text, example, link);
    }

    private Quiz createQuiz(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название опроса: ");
        String name = scanner.nextLine();
        System.out.println("Введите пояснение к опросу: ");
        String text = scanner.nextLine();

        List<Question> questions = QuestionService.getInstance().createQuestions(scanner);

        return new Quiz(name, text, "", questions);
    }
}
