package repository;

import entity.ProgrammingLanguage;

public class ProgrammingLanguagesRepository extends CrudRepository<ProgrammingLanguage>{
    public ProgrammingLanguagesRepository() {
        save("C++", new ProgrammingLanguage("C++"));
        save("C#", new ProgrammingLanguage("C#"));
        save("Java", new ProgrammingLanguage("Java"));
        save("Python", new ProgrammingLanguage("Python"));
    }
}
