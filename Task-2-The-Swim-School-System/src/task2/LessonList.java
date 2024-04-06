package task2;

import java.util.ArrayList;
import java.util.List;

public class LessonList {

    private List<SwimLesson> lessons;

    public LessonList() {
        this.lessons = new ArrayList<>();
    }

    // Method to add a lesson to the list
    public void addLesson(SwimLesson lesson) {
        lessons.add(lesson);
    }

    // Method to remove a lesson from the list
    public void removeLesson(SwimLesson lesson) {
        lessons.remove(lesson);
    }

    // Getter for the list of lessons
    public List<SwimLesson> getLessons() {
        return lessons;
    }

    // Method to get the day of the lesson
    public String getDay(SwimLesson lesson) {
        return lesson.getDayOfWeek();
    }

    // Method to get the start time of the lesson
    public String getStartTime(SwimLesson lesson) {
        return lesson.getStartTime();
    }

    // Method to get the instructor of the lesson
    public Instructor getInstructor(SwimLesson lesson) {
        return lesson.getInstructor();
    }

    // Method to get the students enrolled in the lesson
    public List<SwimStudent> getStudents(SwimLesson lesson) {
        return lesson.getStudents();
    }

//    // Method to add a student to the lesson
//    public void addStudent(SwimLesson lesson, SwimStudent student) {
//        lesson.addStudent(student);
//    }
//    // Method to add multiple students to the lesson
//    public void addStudents(SwimLesson lesson, List<SwimStudent> students) {
//        for (SwimStudent student : students) {
//            lesson.addStudent(student);
//        }
//    }
    public void addStudents(SwimLesson lesson, List<SwimStudent> students) {
    lesson.addStudents(students);
}


    // Method to remove a student from the lesson
    public void removeStudent(SwimLesson lesson, SwimStudent student) {
        lesson.removeStudent(student);
    }
}
