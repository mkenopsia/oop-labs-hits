package entity;

import api.Printable;

public class Question implements Printable {
    private String questionText;
    private QuestionType questionType;

    public Question(String questionText, QuestionType questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    @Override
    public void print() {
        System.out.println("\t\t\tТекст вопроса: " + this.questionText);
        System.out.println("\t\t\tТип вопроса: " + this.questionType.getDescription());
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
