package service;

import entity.Question;
import entity.QuestionType;
import entity.answers.Answer;
import entity.tasks.Quiz;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionService {
    private static final QuestionService INSTANCE = new QuestionService();

    private QuestionService() {}

    public static QuestionService getInstance() {
        return INSTANCE;
    }

    public List<Question> createQuestions(Scanner scanner) {
        List<Question> questions = new ArrayList<>();
        int option = -1;
        while (option != 0) {
            System.out.println("Добавить вопрос: 1");
            System.out.println("Закончить добавлять вопросы: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                        questions.add(createQuestion(scanner));
                    }
                    break;
                case 0: {
                    break;
                }
            }
        }

        return questions;
    }

    public Question createQuestion(Scanner scanner) {
        int questionType = -1;
        while(questionType != 1 & questionType != 2 & questionType != 3) {
            System.out.println("======Выберите тип вопроса:======");
            System.out.println("Выбор одного из двух: 1");
            System.out.println("Выбор одного из нескольких: 2");
            System.out.println("Свободный ответ: 3");
            questionType = scanner.nextInt();

            switch (questionType) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Введите вопрос: ");
                    String questionContent = scanner.nextLine();
                    return new Question(questionContent, QuestionType.CHOICE_BETWEEN_TWO);
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите вопрос: ");
                    String questionContent = scanner.nextLine();
                    return new Question(questionContent, QuestionType.CHOICE_BETWEEN_SEVERAL);
                }
                case 3: {
                    scanner.nextLine();
                    System.out.println("Введите вопрос: ");
                    String questionContent = scanner.nextLine();
                    return new Question(questionContent, QuestionType.FREE_RESPONSE);
                }
            }
        }
        return null;
    }

    public void editQuestions(Scanner scanner, Quiz task) {
        int option = -1;
        while (option != 0) {
            Printer.printElementsFromList(task.getQuestions());
            System.out.println("Добавить вопрос: 1");
            System.out.println("Удалить вопрос: 2");
            System.out.println("Изменить вопрос: 3");
            System.out.println("Выход: 0");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    task.getQuestions().add(createQuestion(scanner));
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("Введите номер для удаления: ");
                    int indexForRemove = scanner.nextInt();
                    task.getQuestions().remove(indexForRemove);
                    break;
                }
                case 3: {
                    System.out.println("Введите номер вопроса для редактирования: ");
                    int indexForRemove = scanner.nextInt();
                    if(indexForRemove > task.getQuestions().size() || indexForRemove < 0) {
                        break;
                    }
                    editQuestion(scanner, task.getQuestions().get(indexForRemove));
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }

    private void editQuestion(Scanner scanner, Question question) {
        scanner.nextLine();
        System.out.println("~~~~~Старый текст: " + question.getQuestionText() + "~~~~~");
        System.out.println("Введите новый текст вопроса: ");
        String newText = scanner.nextLine();
        System.out.println("~~~~~Старый тип вопроса: " + question.getQuestionType() + "~~~~~");
        System.out.println("======Выберите тип вопроса:======");
        System.out.println("Выбор одного из двух: 1");
        System.out.println("Выбор одного из нескольких: 2");
        System.out.println("Свободный ответ: 3");
        QuestionType type;
        int typeIndex = scanner.nextInt();
        if(typeIndex == 1) {
            type = QuestionType.CHOICE_BETWEEN_TWO;
        } else if (typeIndex == 2) {
            type = QuestionType.CHOICE_BETWEEN_SEVERAL;
        } else {
            type = QuestionType.FREE_RESPONSE;
        }
        question.setQuestionText(newText);
        question.setQuestionType(type);
    }
}
