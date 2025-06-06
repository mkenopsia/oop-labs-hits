package entity;

import entity.api.Printable;

public class Solution implements Printable {
    private String solutionText;

    public Solution(String solutionText) {
        this.solutionText = solutionText;
    }

    @Override
    public void print() {
        System.out.println("Решение: " + solutionText);
    }

    public String getSolutionText() {
        return solutionText;
    }

    public void setSolutionText(String solutionText) {
        this.solutionText = solutionText;
    }
}
