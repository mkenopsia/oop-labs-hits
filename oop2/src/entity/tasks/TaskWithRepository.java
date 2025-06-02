package entity.tasks;

import entity.Solution;

public class TaskWithRepository extends Task{
    private String repositoryLink;

    public TaskWithRepository(String name, String text, String example, Solution solution, String repositoryLink) {
        super(name, text, example, solution);
        this.repositoryLink = repositoryLink;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
    }
}
