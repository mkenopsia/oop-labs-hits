package entity;

import api.Printable;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.stream.Collectors;

public class Classroom implements Printable {
    private String classroomName;
    private List<Topic> topics;

    public Classroom(String classroomName, List<Topic> topics) {
        this.classroomName = classroomName;
        this.topics = topics;
    }

    @Override
    public void print() {
        System.out.println("Название класса: " + this.classroomName);
        if(topics.isEmpty()) {
            System.out.println("\tТем нет");
        } else {
            System.out.println("\tТемы: ");
            System.out.println("------------------------------");
            for(var topic : topics) {
                if(topic instanceof Module) {
                    ((Module) topic).print();
                } else {
                    topic.print();
                }
                System.out.println("------------------------------");
            }
        }
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void removeTopic(String topicName) {
        this.topics = this.topics
                .stream().filter(topic -> !topic.getName().equals(topicName)).collect(Collectors.toList());
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
