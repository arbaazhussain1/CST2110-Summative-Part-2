package task2;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private List<SwimStudent> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public void addStudent(SwimStudent student) {
        students.add(student);
    }

    public void removeStudent(SwimStudent student) {
        students.remove(student);
    }

    public List<SwimStudent> getAllStudents() {
        return students;
    }

    // You can add more methods as per your requirements, such as finding a student by name, etc.
}