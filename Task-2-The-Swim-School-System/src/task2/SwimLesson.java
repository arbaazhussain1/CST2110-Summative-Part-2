package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SwimLesson {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private SwimLevel level;
    private Instructor instructor;
    private List<SwimStudent> students;
    private static final int MAX_STUDENT = 4;
    private int duration; // Duration of the lesson in minutes

    public SwimLesson(DayOfWeek dayOfWeek, LocalTime startTime, SwimLevel level, Instructor instructor, int duration) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.level = level;
        this.instructor = instructor;
        this.students = new ArrayList<>();
        this.duration = duration;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
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

    public boolean addStudent(SwimStudent student) {
        if (this.students.size() < MAX_STUDENT) {
            students.add(student);
            return true;
        } else {
            return false;
        }
    }

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

    public void removeStudent(SwimStudent student) {
        students.remove(student);
        System.out.println(student.getName() + " removed from the lesson at " + startTime);
    }

    public int getDuration() {
        return duration;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getAvailableSpaces() {
        return MAX_STUDENT - this.students.size();
    }

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
