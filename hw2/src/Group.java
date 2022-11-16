import java.util.Scanner;

public class Group {
    static Student[] students;

    public static boolean getGroupFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number of students: ");
        int studentsCount = scanner.nextInt();
        if (studentsCount <= 0 || studentsCount > 30) {
            System.out.print("You entered the wrong number of students!");
            return false;
        }
        students = new Student[studentsCount];
        for (int i = 0; i < studentsCount; i++) {
            students[i] = Student.getStudentFromConsole();
        }
        return true;
    }

    public static Student getRandomStudent() {
        return students[(int) (Math.random() * students.length)];
    }

    public static void printStudentsToConsole() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
