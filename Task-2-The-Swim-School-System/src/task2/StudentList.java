package task2;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    private List<SwimStudent> students;
    private List<SwimStudent> waitingList;

    public StudentList() {
        this.students = new ArrayList<>();
        this.waitingList = new ArrayList<>();

    }

    public void addStudent(SwimStudent student) {
        students.add(student);
    }

    public void removeStudent(SwimStudent student) {
        students.remove(student);
    }

    public List<SwimStudent> getAllSwimStudents() {
        return students;
    }

    public List<SwimStudent> getWaitingList() {
        return waitingList;
    }

    public boolean isStudentOnWaitingList(SwimStudent student) {
        return waitingList.contains(student);
    }

    // Method to find a student by name
    public SwimStudent findStudentByName(String name) {
        for (SwimStudent student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student; // Found the student, return it
            }
        }
        return null; // Student not found
    }

    // Check if a student is on the waiting list
    public void addStudentToWaitingList(SwimStudent student) {
        waitingList.add(student);
    }

    public void removeStudentToWaitingList(SwimStudent student) {
        waitingList.remove(student);
    }

    public List<SwimStudent> getStudentsByLevel(SwimLevel level) {
        List<SwimStudent> studentList = new ArrayList<>();
        for (SwimStudent student : students) {
            if (student.getLevel().equals(level)) {
                studentList.add(student);
            }

        }

        return studentList;
    }

}
