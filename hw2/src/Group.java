import java.util.ArrayList;

public class Group {
    ArrayList<Student> students = new ArrayList<Student>();

    public Group(){
        students = new ArrayList<>();
    }

    public Student getRandomStudent() {
        return students.get((int) (Math.random() * students.size()));
    }

    public String  toString() {
        StringBuilder result = new StringBuilder("Group:\n");
        for (Student student : students) {
            result.append(student);
        }
        return result.toString();
    }
}
