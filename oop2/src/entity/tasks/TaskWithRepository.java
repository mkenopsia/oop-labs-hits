package entity.tasks;

import entity.Question;
import entity.Solution;

public class TaskWithRepository extends Task{
    private String repositoryLink;

    public TaskWithRepository(String name, String text, String example, String repositoryLink) {
        super(name, text, example);
        this.repositoryLink = repositoryLink;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\t\t\tСсылка на репозиторий: " + this.repositoryLink);
    }

    @Override
    public void internalPrint() {
        System.out.println("\t\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\t\tЗадание по алгоритмике: " + this.getText());
        System.out.println("\t\t\t\tТекст задания: " + this.getText());
        System.out.println("\t\t\t\tПример задания: " + this.getExample());
        System.out.println("\t\t\t\tСсылка на репозиторий: " + this.repositoryLink);
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
    }
}
