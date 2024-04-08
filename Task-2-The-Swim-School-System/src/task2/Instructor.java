
package task2;


import java.util.ArrayList;
import java.util.List;
class Instructor {
    private String name;
    private List<SwimLesson> swimLessonList;

    public Instructor(String name) {
        this.name = name;
        swimLessonList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addLesson(SwimLesson lesson) {
        swimLessonList.add(lesson);
    }

    public List<SwimLesson> getSwimLessonList() {
        return swimLessonList;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasLessonWithLevel(SwimLevel level) {
        for (SwimLesson lesson:this.swimLessonList)
        {
            if(lesson.getLevel().equals(level))
                return true;
        }

        return false;
    }
}