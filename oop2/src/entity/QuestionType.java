package entity;

public enum QuestionType {
    CHOICE_BETWEEN_TWO("Выбор одного из двух"),
    CHOICE_BETWEEN_SEVERAL("Выбор одного из нескольких"),
    FREE_RESPONSE("Свободный ответ");

    private String description;

    QuestionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
