import service.ClassroomService;
import service.SolutionsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while(option != 0) {
            System.out.println("=========Выберите действие=========");
            System.out.println("Модерация классов: 1");
            System.out.println("Перейти к решениям: 2");
            System.out.println("Выход из программы: 0");
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    ClassroomService.getInstance().process(scanner);
                    break;
                }
                case 2: {
                    SolutionsService.getInstance().process(scanner);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }
    }
}