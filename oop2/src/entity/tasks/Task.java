package entity.tasks;

import api.Printable;
import entity.NamedEntity;
import entity.Solution;

public abstract class Task extends NamedEntity implements Printable {
    private String text;
    private String example;

    public Task(String name, String text, String example) {
        super(name);
        this.text = text;
        this.example = example;
    }

    @Override
    public void print() {
        System.out.println("\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\tТекст задания: " + this.text);
        System.out.println("\t\t\tПример задания: " + this.example);
    }

    public void internalPrint() {

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
}
