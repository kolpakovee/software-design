import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;
        if (Group.getGroupFromConsole()) {
            System.out.println("\nSupported commands:\n" +
                    "/r - random student and grading\n" +
                    "/p - group list\n" +
                    "/q - program termination\n");
            do {
                System.out.print("Enter command: ");
                command = in.nextLine();
                if (Objects.equals(command, "/r")) {
                    Student student = Group.getRandomStudent();
                    student.inputGradeFromConsole();
                } else if (Objects.equals(command, "/p")) {
                    Group.printStudentsToConsole();
                }
            } while (!Objects.equals(command, "/q"));
        }
    }
}