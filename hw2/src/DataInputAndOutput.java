import java.util.ArrayList;
import java.util.Scanner;


public class DataInputAndOutput {
    static Student getStudentFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input name: ");
        String name = in.nextLine();
        System.out.print("Input surname: ");
        String surname = in.nextLine();
        return new Student(name, surname);
    }

    static void inputGradeFromConsole(Student student) {
        System.out.print("Input grade for " + student.name + " " + student.surname + ": ");
        Scanner in = new Scanner(System.in);
        int newGrade = in.nextInt();
        if (student.grade < 0 || student.grade > 10) {
            System.out.print("You entered an incorrect grade!");
            return;
        }
        if (student.haveGrade) {
            newGrade = (int) Math.round((newGrade + student.grade) / 2.0);
        }
        student.grade = newGrade;
        student.haveGrade = true;
    }

    public static boolean getGroupFromConsole(ArrayList<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number of students: ");
        int studentsCount = scanner.nextInt();
        if (studentsCount <= 0 || studentsCount > 30) {
            System.out.print("You entered the wrong number of students!");
            return false;
        }
        for (int i = 0; i < studentsCount; i++) {
            students.add(getStudentFromConsole());
        }
        return true;
    }
}
