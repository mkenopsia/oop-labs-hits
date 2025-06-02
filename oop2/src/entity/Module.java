package entity;

import entity.tasks.Task;

import java.util.List;

public class Module extends Topic {
    List<Topic> topics;

    public Module(String name, boolean isVisible, List<Task> tasks, List<Topic> topics) {
        super(name, isVisible, tasks);
        this.topics = topics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
