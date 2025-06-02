package entity.tasks;

import entity.ProgrammingLanguage;
import entity.Solution;

public class AlgoTask extends Task{
    private ProgrammingLanguage programmingLanguage;

    public AlgoTask(String name, String text, String example, Solution solution, ProgrammingLanguage programmingLanguage) {
        super(name, text, example, solution);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void print() {
        System.out.println("\t\t\tЗадание по алгоритмике: " + this.getText());
        System.out.println("\t\t\tТекст задания: " + this.getText());
        System.out.println("\t\t\tПример задания: " + this.getExample());
        this.getSolution().print();
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
