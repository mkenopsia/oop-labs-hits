package entity;

import entity.api.Printable;
import entity.tasks.Task;

import java.util.List;

public abstract class Topic extends NamedEntity implements Printable {
    private boolean isVisible;
    private List<Task> tasks;

    public Topic(String name, boolean isVisible, List<Task> tasks) {
        super(name);
        this.isVisible = isVisible;
        this.tasks = tasks;
    }

    @Override
    public void print() {
        System.out.println("\t\tНазвание: " + this.getName());
        System.out.println("\t\tОтображается студентам: " + ((this.isVisible) ? "Да" : "Нет"));
        if(tasks.isEmpty()) {
            System.out.println("\t\tЗаданий нет");
        } else {
            System.out.println("\t\tЗадания: ");
            for(var task : tasks) {
                task.print();
            }
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}