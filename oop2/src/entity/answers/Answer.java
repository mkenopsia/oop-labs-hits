package entity.answers;

import entity.Solution;

import java.util.List;

public class Answer extends Solution {
    private List<String> answers;

    public Answer(String solutionText, List<String> answers) {
        super(solutionText);
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
