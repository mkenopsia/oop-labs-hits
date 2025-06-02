package repository;

import entity.Classroom;
import entity.Module;
import entity.ProgrammingLanguage;
import entity.Solution;
import entity.tasks.AlgoTask;
import entity.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository extends CrudRepository<Classroom>{
    public ClassroomRepository() {
        this.storage.put("Тестовый класс", new Classroom("Тестовый класс",
                new ArrayList<>(List.of(new Module("Модуль программирования", true,
                        List.of(new AlgoTask("Решить тест", "Решите тест на языке C++", "С++ норм", new Solution("std::sout << \"c++ norm\""), new ProgrammingLanguage("C++"))), new ArrayList<>())))));
    }
}
