package models;

public enum PizzaSize {

    SMALL("25см", 0.9), STANDARD("30см", 1.0), LARGE("35см", 1.2);

    PizzaSize(String type, Double coef) {
        this.type = type;
        this.coef = coef;
    }

    private String type;
    private Double coef;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }
}
