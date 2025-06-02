package entity;

import api.Printable;

import java.util.List;

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
        } else if(topics.size() == 1) {
            System.out.println("\tТемы: ");
            for(var topic : topics) {
                topic.print();
            }
        }
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
