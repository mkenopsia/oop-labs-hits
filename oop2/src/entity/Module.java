package entity;

import entity.tasks.Task;

import java.util.List;

public class Module extends Topic {
    List<Section> sections;

    public Module(String name, boolean isVisible, List<Task> tasks, List<Section> sections) {
        super(name, isVisible, tasks);
        this.sections = sections;
    }

    @Override
    public void print() {
        System.out.println("<=-- Модуль --=>");
        System.out.println("\t\tНазвание: " + this.getName());
        System.out.println("\t\tОтображается студентам: " + ((this.isVisible()) ? "Да" : "Нет"));

        if(this.getSections().isEmpty()) {
            System.out.println("\t\tСекций нет");
        } else {
            System.out.println("\t\tСекции: ");
            System.out.println("\t\t\t##########: ");
            for(var section : this.getSections()) {
                section.internalPrint();
            }
        }

        if(this.getTasks().isEmpty()) {
            System.out.println("\t\tЗаданий нет");
        } else {
            System.out.println("\t\tЗадания: ");
            for(var task : this.getTasks()) {
                task.print();
            }
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
