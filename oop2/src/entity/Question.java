package entity;

import entity.answers.Answer;

public class Question {
    private String questionText;
    private Answer answer;
    private QuestionType questionType;

    public Question(String questionText, Answer answer, QuestionType questionType) {
        this.questionText = questionText;
        this.answer = answer;
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
