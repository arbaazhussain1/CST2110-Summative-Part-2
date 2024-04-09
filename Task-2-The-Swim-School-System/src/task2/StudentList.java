package task2;

import java.util.ArrayList;
import java.util.List;

// Class to manage the list of students enrolled in swimming classes
public class StudentList {

    // Instance variables
    private List<SwimStudent> students;  // List of all enrolled students
    private List<SwimStudent> waitingList; // List of students on the waiting list

    // Constructor to initialize the lists
    public StudentList() {
        this.students = new ArrayList<>();  // Initialize the list of students
        this.waitingList = new ArrayList<>();  // Initialize the waiting list

    }

    // Method to add a student to the list of enrolled students
    public void addStudent(SwimStudent student) {
        students.add(student);
    }

    // Method to remove a student from the list of enrolled students
    public void removeStudent(SwimStudent student) {
        students.remove(student);
    }

    // Method to get all enrolled students
    public List<SwimStudent> getAllSwimStudents() {
        return students;
    }

    // Method to get the waiting list of students
    public List<SwimStudent> getWaitingList() {
        return waitingList;
    }

    // Method to check if a student is on the waiting list
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

    // Method to add a student to the waiting list
    public void addStudentToWaitingList(SwimStudent student) {
        waitingList.add(student);
    }

    // Method to remove a student from the waiting list
    public void removeStudentToWaitingList(SwimStudent student) {
        waitingList.remove(student);
    }

    // Method to get all students of a particular level
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
