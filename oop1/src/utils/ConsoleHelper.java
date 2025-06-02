package utils;

import models.PizzaSize;

import java.util.Scanner;

public class ConsoleHelper {

    public static String readString(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static boolean confirm(Scanner scanner, String prompt) {
        System.out.println(prompt + " (y/n)");
        return scanner.nextLine().equalsIgnoreCase("y");
    }

//    public static PizzaSize readPizzaSize(Scanner scanner) {
//        String input = scanner.nextLine();
//        if (input.equals("25см")) return PizzaSize.SMALL;
//        if (input.equals("35см")) return PizzaSize.LARGE;
//        return PizzaSize.STANDARD;
//    }
}