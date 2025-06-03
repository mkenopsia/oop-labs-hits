package entity;

import entity.tasks.AlgoTask;
import entity.tasks.Task;

import java.util.List;

public class Section extends Topic{
    public Section(String name, boolean isVisible, List<Task> tasks) {
        super(name, isVisible, tasks);
    }

    @Override
    public void print() {
        System.out.println("+++_Секция_+++");
        System.out.println("\t\tНазвание: " + this.getName());
        System.out.println("\t\tОтображается студентам: " + ((this.isVisible()) ? "Да" : "Нет"));
        if(this.getTasks().isEmpty()) {
            System.out.println("\t\tЗаданий нет");
        } else {
            System.out.println("\t\tЗадания: ");
            for(var task : this.getTasks()) {
                task.print();
            }
        }
    }

    public void internalPrint() {
        System.out.println("\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\tОтображается студентам: " + ((this.isVisible()) ? "Да" : "Нет"));
        if(this.getTasks().isEmpty()) {
            System.out.println("\t\t\tЗаданий нет");
        } else {
            System.out.println("\t\t\tЗадания: ");
            for(var task : this.getTasks()) {
                task.internalPrint();
            }
        }
        System.out.println("\t\t\t##########: ");
    }
}
