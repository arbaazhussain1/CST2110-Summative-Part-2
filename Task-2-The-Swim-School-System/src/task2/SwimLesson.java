package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimLesson {

    private String dayOfWeek;
    private String startTime;
    private SwimLevel level;
    private Instructor instructor;
    private List<SwimStudent> students;

    public SwimLesson(String dayOfWeek, String startTime, SwimLevel level, Instructor instructor) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.level = level;
        this.instructor = instructor;
        this.students = new ArrayList<>();
    }

    // Getters
    
    public String getDayOfWeek() {
        return dayOfWeek;
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
//    public void addStudent(SwimStudent student) {
//        students.add(student);
//    }
    
    public void addStudents(List<SwimStudent> students) {
    for (SwimStudent student : students) {
        if (this.students.size() < 4) {
            this.students.add(student);
            System.out.println(student.getName() + " added to the lesson at " + startTime);
        } else {
            System.out.println("Cannot add " + student.getName() + ". The lesson is already full.");
        }
    }
}


    public void removeStudent(SwimStudent student) {
        students.remove(student);
        System.out.println(student.getName() + " removed from the lesson at " + startTime);
    }

}
