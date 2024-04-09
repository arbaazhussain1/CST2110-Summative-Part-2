package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// This class manages a list of swim lessons
public class LessonList {

    // Instance variable to store the list of swim lessons
    private List<SwimLesson> lessons;

    // Constructor to initialize the lesson list
    public LessonList() {
        this.lessons = new ArrayList<>();
    }

    // Method to add a swim lesson to the list
    public void addLesson(SwimLesson lesson) {
        lessons.add(lesson);
    }

    // Method to remove a swim lesson from the list
    public void removeLesson(SwimLesson lesson) {
        lessons.remove(lesson);
    }

    // Getter method to retrieve the list of swim lessons
    public List<SwimLesson> getLessons() {
        return lessons;
    }

    // Method to get a specific swim lesson based on the day, time, and level
    public SwimLesson getLesson(DayOfWeek selectedDay, LocalTime selectedTime, SwimLevel selectedLevel) {
        // Iterate through the list of lessons to find the matching lesson
        for (SwimLesson lesson : lessons) {
            if (lesson.getDayOfWeek().equals(selectedDay)
                    && lesson.getStartTime().equals(selectedTime)
                    && lesson.getLevel().equals(selectedLevel)) {
                return lesson;
            }
        }
        return null; // Return null if no matching lesson is found
    }

    // Method to get all swim lessons for a specific level
    public List<SwimLesson> getLessonsByLevel(SwimLevel level) {

        List<SwimLesson> lessonList = new ArrayList<>();
        // Iterate through the list of lessons to find lessons with the specified level

        for (SwimLesson lesson : this.lessons) {
            if (lesson.getLevel().equals(level)) {
                lessonList.add(lesson);
            }
        }
        return lessonList;
    }
}
