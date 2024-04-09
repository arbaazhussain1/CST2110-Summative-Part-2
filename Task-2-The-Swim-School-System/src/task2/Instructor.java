package task2;

import java.util.ArrayList;
import java.util.List;

// This class represents an instructor who conducts swim lessons
class Instructor {

    // Instance variables
    private String name; // Instructor's name
    private List<SwimLesson> swimLessonList; // List of swim lessons conducted by the instructor

    // Constructor to initialize an Instructor object with a name
    public Instructor(String name) {
        this.name = name;
        swimLessonList = new ArrayList<>(); // Initialize the list of swim lessons
    }

    // Getter method to retrieve the instructor's name
    public String getName() {
        return name;
    }

    // Method to add a swim lesson to the instructor's list of lessons
    public void addLesson(SwimLesson lesson) {
        swimLessonList.add(lesson);
    }

    // Getter method to retrieve the list of swim lessons conducted by the instructor
    public List<SwimLesson> getSwimLessonList() {
        return swimLessonList;
    }

    // Override toString method to return the instructor's name
    @Override
    public String toString() {
        return name;
    }

    // Method to check if the instructor conducts a lesson with a specific swim level
    public boolean hasLessonWithLevel(SwimLevel level) {
        for (SwimLesson lesson : this.swimLessonList) {
            if (lesson.getLevel().equals(level)) {
                return true;
            }
        }

        return false;
    }
}
