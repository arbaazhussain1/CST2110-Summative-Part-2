package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

    public SwimLesson getLesson(DayOfWeek selectedDay, LocalTime selectedTime, SwimLevel selectedLevel) {
        // Iterate through the list of lessons to find the matching lesson
        for (SwimLesson lesson : lessons) {
            if (lesson.getDayOfWeek().equals(selectedDay) &&
                    lesson.getStartTime().equals(selectedTime) &&
                    lesson.getLevel().equals(selectedLevel)) {
                return lesson;
            }
        }
        return null; // Return null if no matching lesson is found
    }

    public List<SwimLesson> getLessonsByLevel(SwimLevel level) {

        List<SwimLesson> lessonList=new ArrayList<>();

        for (SwimLesson lesson:this.lessons)
        {
            if(lesson.getLevel().equals(level))
                lessonList.add(lesson);
        }
        return lessonList;
    }
}
