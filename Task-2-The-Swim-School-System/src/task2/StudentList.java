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

    public List<SwimStudent> getAllStudents() {
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
    public boolean isStudentOnWaitingList(String name) {
        for (SwimStudent student : waitingList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return true; // Student is on the waiting list
            }
        }
        return false; // Student is not on the waiting list
    }
    
    public void addStudentToWaitingList(SwimStudent student) {
    waitingList.add(student);
}
    
    public void removeStudentToWaitingList(SwimStudent student) {
    waitingList.remove(student);
}

    // You can add more methods as per your requirements, such as finding a student by name, etc.
}
