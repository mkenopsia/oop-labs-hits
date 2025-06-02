import entity.Classroom;
import repository.ClassroomRepository;
import service.ClassroomService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ClassroomService classroomService = new ClassroomService(new ClassroomRepository());
        Scanner scanner = new Scanner(System.in);
        classroomService.process(scanner);
    }
}