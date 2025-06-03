package entity.tasks;

import entity.ProgrammingLanguage;
import entity.Question;
import entity.Solution;

import java.util.List;

public class Quiz extends Task{
    private List<Question> questions;

    public Quiz(String name, String text, String example, List<Question> questions) {
        super(name, text, example);
        this.questions = questions;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\t\t\tВопросы:" + this.questions.stream().map(Question::getQuestionText).toList());
    }

    @Override
    public void internalPrint() {
        System.out.println("\t\t\t\tНазвание: " + this.getName());
        System.out.println("\t\t\t\tЗадание по алгоритмике: " + this.getText());
        System.out.println("\t\t\t\tТекст задания: " + this.getText());
        System.out.println("\t\t\t\tПример задания: " + this.getExample());
        System.out.println("\t\t\t\tВопросы:" + this.questions.stream().map(Question::getQuestionText).toList());
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
