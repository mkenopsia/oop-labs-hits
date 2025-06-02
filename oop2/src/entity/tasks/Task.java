package entity.tasks;

import api.Printable;
import entity.NamedEntity;
import entity.Solution;

public abstract class Task extends NamedEntity implements Printable {
    private String text;
    private String example;
    private Solution solution;

    public Task(String name, String text, String example, Solution solution) {
        super(name);
        this.text = text;
        this.example = example;
        this.solution = solution;
    }

    @Override
    public void print() {
        System.out.println("\t\t\tТекст задания: " + this.text);
        System.out.println("\t\t\tПример задания: " + this.example);
        solution.print();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
