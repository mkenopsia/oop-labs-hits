package entity.tasks;

import entity.ProgrammingLanguage;
import entity.Solution;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class AlgoTask extends Task{
    private List<ProgrammingLanguage> languages;

    public AlgoTask(String name, String text, String example, List<ProgrammingLanguage> languages) {
        super(name, text, example);
        this.languages = languages;
    }

    @Override
    public void print() {
        System.out.println("\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\tЗадание по алгоритмике: " + this.getText());
        System.out.println("\t\t\tТекст задания: " + this.getText());
        System.out.println("\t\t\tПример задания: " + this.getExample());
        System.out.println("\t\t\tЯзыки программирования: " + this.languages.stream().map(ProgrammingLanguage::getName).toList());;
    }

    @Override
    public void internalPrint() {
        System.out.println("\t\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\t\tЗадание по алгоритмике: " + this.getText());
        System.out.println("\t\t\t\tТекст задания: " + this.getText());
        System.out.println("\t\t\t\tПример задания: " + this.getExample());
        System.out.println("\t\t\t\tЯзыки программирования: " + this.languages.stream().map(ProgrammingLanguage::getName).toList());;

    }

    public List<ProgrammingLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<ProgrammingLanguage> languages) {
        this.languages = languages;
    }
}
