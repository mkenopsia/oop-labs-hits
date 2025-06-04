package entity;

import entity.api.Printable;

public class Question implements Printable {
    private String questionText;
    private QuestionType questionType;
    private Solution answer;

    public Question(String questionText, QuestionType questionType, Solution answer) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.answer = answer;
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

    public Solution getAnswer() {
        return answer;
    }

    public void setAnswer(Solution answer) {
        this.answer = answer;
    }
}
