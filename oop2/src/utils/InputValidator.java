package utils;

import java.util.Scanner;

public class InputValidator {
    public static int validateTopicInput(Scanner scanner, int size) {
        System.out.println("Выберите номер темы: ");
        int topicIndex = scanner.nextInt();
        while(topicIndex < 0 || topicIndex >= size) {
            System.out.println("\n\nВведите корректный номер темы\n\n");
            topicIndex = scanner.nextInt();
        }
        return topicIndex;
    }

    public static int validateClassroomInput(Scanner scanner, int size) {
        System.out.println("Выберите номер класса: ");
        int classIndex = scanner.nextInt();
        while (classIndex < 0 || classIndex >= size) {
            if(classIndex == -1) return -1;
            System.out.println("\n\nВведите корректный номер класса\n\n");
            classIndex = scanner.nextInt();
        }

        return classIndex;
    }

    public static int validateTaskInput(Scanner scanner, int size) {
        System.out.println("Выберите номер задания: ");
        int taskIndex = scanner.nextInt();
        while (taskIndex < 0 || taskIndex >= size) {
            System.out.println("\n\nВведите корректный номер задания\n\n");
            taskIndex = scanner.nextInt();
        }

        return taskIndex;
    }

    public static int validateProgrammingLanguageInput(Scanner scanner, int size) {
        System.out.println("Выберите номер языка программирования: ");
        int languageIndex = scanner.nextInt();
        while (languageIndex < 0 || languageIndex >= size) {
            System.out.println("\n\nВведите корректный номер языка программирования\n\n");
            languageIndex = scanner.nextInt();
        }

        return languageIndex;
    }

    public static int validateSectionInput(Scanner scanner, int size) {
        System.out.println("Выберите номер секции для редактирования: ");
        int index = scanner.nextInt();
        while (index < 0 || index >= size) {
            System.out.println("\n\nВведите корректный номер секции\n\n");
            index = scanner.nextInt();
        }

        return index;
    }
}
