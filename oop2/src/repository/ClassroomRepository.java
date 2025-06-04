package repository;

import entity.*;
import entity.Module;
import entity.answers.FreeResponseAnswer;
import entity.answers.SingleOptionBetweenSeveral;
import entity.answers.SingleOptionBetweenTwo;
import entity.tasks.AlgoTask;
import entity.tasks.Quiz;
import entity.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository extends CrudRepository<Classroom> {
    public ClassroomRepository() {
        this.storage.put(
                "хитс",
                new Classroom("хитс",
                        new ArrayList<>(List.of(new Module(
                                        "прога",
                                        true,
                                        new ArrayList<>(List.of(
                                                new Quiz("Опрос по теории",
                                                        "Теоритический опрос по алгоритмам",
                                                        "",
                                                        new ArrayList<>(List.of(
                                                                new Question(
                                                                        "Какая сортировка быстрее - пузырьком или быстрая?",
                                                                        QuestionType.CHOICE_BETWEEN_TWO, new SingleOptionBetweenTwo("Быстрая")),
                                                                new Question(
                                                                        "Кто такой Дональд Кнут?",
                                                                        QuestionType.FREE_RESPONSE, new FreeResponseAnswer("Создатель знаменитой книги по алгоритмам"))
                                                        )))
                                        )),
                                        new ArrayList<>(List.of(
                                                new Section(
                                                        "сортировки",
                                                        true,
                                                        new ArrayList<>(List.of(
                                                                new AlgoTask(
                                                                        "Быстрая сортировка",
                                                                        "Напишите быструю сортировку",
                                                                        "Сортировка которая быстро сортирует",
                                                                        new ArrayList<>(List.of(
                                                                                new ProgrammingLanguage("C++"),
                                                                                new ProgrammingLanguage("Java")
                                                                        ))
                                                                )
                                                        ))
                                                ))
                                        )
                                ),
                                new Section(
                                        "Теоретические вопросы по модулю",
                                        false,
                                        new ArrayList<>(
                                                List.of(
                                                        new Quiz(
                                                                "Теоретический опрос",
                                                                "Опрос на знание алгоритмов",
                                                                "",
                                                                new ArrayList<>(
                                                                        List.of(
                                                                                new Question(
                                                                                        "Что такое алгоритм? a. набор инструкций б. игра в. фирма",
                                                                                        QuestionType.CHOICE_BETWEEN_SEVERAL,
                                                                                        new SingleOptionBetweenSeveral("а. набор инструкций"))))
                                                        )))
                                )
                        ))
                )
        );
//        this.storage.put("Тестовый класс", new Classroom("Тестовый класс",
//                new ArrayList<>(List.of(new Module("Модуль программирования", true,
//                        List.of(new AlgoTask("Решить тест", "Решите тест на языке C++", "С++ норм", new ProgrammingLanguage("C++"))), new ArrayList<>())))));
    }
}
