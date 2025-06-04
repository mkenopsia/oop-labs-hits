package utils;

import api.Printable;
import entity.*;
import entity.Module;
import entity.answers.QuizAnswer;
import entity.tasks.AlgoTask;
import entity.tasks.Task;
import entity.tasks.TaskWithRepository;
import repository.CrudRepository;
import service.ClassroomService;

import java.util.List;
import java.util.Map;

public class Printer {

    private Printer() {}

    public static <T extends Printable> void printRepositoryContent(CrudRepository<T> crudRepository) {
        for (Map.Entry<String, T> entry : crudRepository.getAll().entrySet()) {
            entry.getValue().print();
        }
    }

    public static <T extends Printable> void printRepositoryKeys(CrudRepository<T> crudRepository) {
        for (Map.Entry<String, T> entry : crudRepository.getAll().entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static <T extends Printable> void printElementsFromList(List<T> elements) {
        int count = 0;
        System.out.println("+++++++++++++++++++++");
        for(T element : elements) {
            System.out.print(count++ + ": ");
            element.print();
            System.out.println("+++++++++++++++++++++");
        }
    }

    public static void printClassrooms(List<Classroom> classrooms) {
        int count = 0;
        for(Classroom classroom : classrooms) {
            System.out.println(classroom.getClassroomName() + ": " + count++);
        }
    }

    public static void printProgrammingLanguages(List<ProgrammingLanguage> languages) {
        int count = 0;
        for(var language : languages) {
            System.out.println(language.getName() + ": " + count++);
        }
    }

    public static void printTopics(List<Topic> topics) {
        int count = 0;
        for(Topic topic : topics) {
            if(topic instanceof Module) {
                System.out.println("- Модуль: " + topic.getName() + ": " + count++);
            } else {
                System.out.println("- Секция: " + topic.getName() + ": " + count++);
            }
        }
    }

    public static void printClassroomNames() {
        int count = 0;
        for (Classroom classroom : ClassroomService.getInstance().getAllClassrooms().values()) {
            System.out.println(classroom.getClassroomName() + ": " + count++);
        }
    }

    public static void printTasks(List<Task> tasks) {
        int count = 0;
        for(Task task : tasks) {
            String type;
            if(task instanceof AlgoTask) {
                type = "Задача по алгоритмике";
            } else if (task instanceof TaskWithRepository) {
                type = "Задача с репозиторием";
            } else {
                type = "Опрос";
            }

            System.out.println(type + ": " + task.getName() + ": " + count++);
        }
    }

    public static void printSolutions(List<Solution> solutions) {
        int count = 0;
        for(Solution solution : solutions) {
            if(solution instanceof QuizAnswer) {
                System.out.println("Решение №" + count++ + ": " + "Выбранные варианты ответов: [" + ((QuizAnswer) solution).toString() + "]");
            } else {
                System.out.println("Решение №" + count++ + ": " + solution.getSolutionText());
            }
        }
    }

    public static void printSolutionsWithTabulation(List<Solution> solutions) {
        int count = 0;
        for(Solution solution : solutions) {
            if(solution instanceof QuizAnswer) {
                System.out.println("\tРешение №" + count++ + ": " + "Выбранные варианты ответов: [" + ((QuizAnswer) solution).toString() + "]");
            } else {
                System.out.println("\tРешение №" + count++ + ": " + solution.getSolutionText());
            }
        }
    }
}
