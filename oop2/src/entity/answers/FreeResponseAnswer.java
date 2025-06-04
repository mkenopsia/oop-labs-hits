package entity.answers;

import entity.QuestionType;
import entity.Solution;

public class FreeResponseAnswer extends Solution {
    public static String answerType = QuestionType.FREE_RESPONSE.getDescription();
    public FreeResponseAnswer(String solutionText) {
        super(solutionText);
    }
}
