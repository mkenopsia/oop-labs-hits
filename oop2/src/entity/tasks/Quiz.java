package entity.tasks;

import entity.Question;
import entity.Solution;

import java.util.List;

public class Quiz extends Task{
    private List<Question> questions;

    public Quiz(String name, String text, String example, Solution solution, List<Question> questions) {
        super(name, text, example, solution);
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
