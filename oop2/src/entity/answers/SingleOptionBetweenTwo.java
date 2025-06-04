package entity.answers;

import entity.QuestionType;
import entity.Solution;

public class SingleOptionBetweenTwo extends Solution {
    public static String answerType = QuestionType.CHOICE_BETWEEN_TWO.getDescription();
    public SingleOptionBetweenTwo(String solutionText) {
        super(solutionText);
    }
}
