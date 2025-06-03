package entity;

import api.Printable;

public class ProgrammingLanguage extends NamedEntity implements Printable {
    public ProgrammingLanguage(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println("\t\t\tЯзык программирования: " + this.getName());
    }
}
