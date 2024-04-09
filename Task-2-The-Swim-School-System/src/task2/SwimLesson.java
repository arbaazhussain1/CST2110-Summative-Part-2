package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Class representing a swimming lesson
public class SwimLesson {

    private DayOfWeek dayOfWeek;  // Day of the week the lesson takes place
    private LocalTime startTime; // Start time of the lesson
    private SwimLevel level;  // Level of the swimmers in the lesson
    private Instructor instructor;  // Instructor for the lesson
    private List<SwimStudent> students;  // List of students enrolled in the lesson
    private static final int MAX_STUDENT = 4;  // Maximum number of students allowed in the lesson
    private int duration; // Duration of the lesson in minutes

    // Constructor to initialize a SwimLesson object
    public SwimLesson(DayOfWeek dayOfWeek, LocalTime startTime, SwimLevel level, Instructor instructor, int duration) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.level = level;
        this.instructor = instructor;
        this.students = new ArrayList<>();  // Initialize the list of students
        this.duration = duration;
    }

    // Getter for dayOfWeek
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    // Getter for startTime
    public LocalTime getStartTime() {
        return startTime;
    }

    // Getter for level
    public SwimLevel getLevel() {
        return level;
    }

    // Getter for instructor
    public Instructor getInstructor() {
        return instructor;
    }

    // Getter for students
    public List<SwimStudent> getStudents() {
        return students;
    }

    // Method to add a single student to the lesson
    public boolean addStudent(SwimStudent student) {
        if (this.students.size() < MAX_STUDENT) {
            students.add(student);
            return true; // Student successfully added
        } else {
            return false; // Lesson is full, cannot add student
        }
    }

    // Method to add a list of students to the lesson
    public void addStudents(List<SwimStudent> students) {
        for (SwimStudent student : students) {
            if (this.students.size() < MAX_STUDENT) {
                this.students.add(student);
                System.out.println(student.getName() + " added to the lesson at " + startTime);
            } else {
                System.out.println("Cannot add " + student.getName() + ". The lesson is already full.");
            }
        }
    }

    // Method to remove a student from the lesson
    public void removeStudent(SwimStudent student) {
        students.remove(student);
        System.out.println(student.getName() + " removed from the lesson at " + startTime);
    }

    // Getter for duration
    public int getDuration() {
        return duration;
    }

    // Setter for instructor
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Method to get the number of available spaces in the lesson
    public int getAvailableSpaces() {
        return MAX_STUDENT - this.students.size();
    }

    // Method to represent the SwimLesson object as a string
    @Override
    public String toString() {
        return "Day =" + dayOfWeek
                + ", Time=" + startTime
                + ", level=" + level
                + ", instructor=" + instructor
                + ", students=" + students.size()
                + ", duration=" + duration;
    }
}
