package entity.answers;

import entity.QuestionType;
import entity.Solution;

public class SingleOptionBetweenSeveral extends Solution {
    public static String answerType = QuestionType.CHOICE_BETWEEN_SEVERAL.getDescription();
    public SingleOptionBetweenSeveral(String solutionText) {
        super(solutionText);
    }
}
