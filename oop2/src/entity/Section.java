package entity;

import entity.tasks.Task;

import java.util.List;

public class Section extends Topic{
    public Section(String name, boolean isVisible, List<Task> tasks) {
        super(name, isVisible, tasks);
    }
}
