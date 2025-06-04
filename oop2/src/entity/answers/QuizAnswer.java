package entity.answers;

import entity.QuestionType;
import entity.Solution;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswer extends Solution {
    private List<Solution> answers;

    public QuizAnswer(String solutionText, List<Solution> answers) {
        super(solutionText);
        this.answers = answers;
    }

    public List<Solution> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Solution> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        List<String> stringAnswers = new ArrayList<>();
        for(Solution solution : answers) {
            if(solution instanceof SingleOptionBetweenTwo) {
                stringAnswers.add(QuestionType.CHOICE_BETWEEN_TWO.getDescription() + ": " + solution.getSolutionText());
            } else if (solution instanceof SingleOptionBetweenSeveral) {
                stringAnswers.add(QuestionType.CHOICE_BETWEEN_SEVERAL.getDescription() + ": " + solution.getSolutionText());
            } else {
                stringAnswers.add(QuestionType.FREE_RESPONSE.getDescription() + ": " + solution.getSolutionText());
            }
        }
        return String.join(", ", stringAnswers);
    }
}
