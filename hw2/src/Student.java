public class Student {
    String name;
    String surname;
    int grade;
    boolean haveGrade = false;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return name + " " + surname + ": " + (haveGrade ? Integer.toString(grade) : "-") + "\n";
    }
}
