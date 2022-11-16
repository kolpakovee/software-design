import javax.lang.model.element.Name;
import java.util.Scanner;

public class Student {
    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    String name;
    String surname;

    int grade;
    boolean haveGrade = false;

    static Student getStudentFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input name: ");
        String name = in.nextLine();
        System.out.print("Input surname: ");
        String surname = in.nextLine();
        return new Student(name, surname);
    }

    void inputGradeFromConsole() {
        System.out.print("Input grade for " + name + " " + surname + ": ");
        Scanner in = new Scanner(System.in);
        int newGrade = in.nextInt();
        if (grade < 0 || grade > 10) {
            System.out.print("You entered an incorrect grade!");
            return;
        }
        if (haveGrade) {
            newGrade = (int) Math.round((newGrade + this.grade) / 2.0);
        }
        this.grade = newGrade;
        haveGrade = true;
    }

    public String toString() {
        return name + " " + surname + ": " + (haveGrade ? Integer.toString(grade) : "-");
    }
}
