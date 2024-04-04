package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimLesson {

    private String day;
    private String startTime;
    private SwimLevel level;
    private Instructor instructor;
    private List<SwimStudent> students;

    public SwimLesson(String day, String startTime, SwimLevel level, Instructor instructor) {
        this.day = day;
        this.startTime = startTime;
        this.level = level;
        this.instructor = instructor;
        this.students = new ArrayList<>();
    }

    // Getters
    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public SwimLevel getLevel() {
        return level;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<SwimStudent> getStudents() {
        return students;
    }

    // Method to add a student to the lesson
    public void addStudent(SwimStudent student) {
        students.add(student);
    }

}
