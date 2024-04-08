package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimStudent {
    
    private String name;
    private SwimLevel level;
    private List<Qualification> qualifications;

    private SwimLesson swimClass; // Reference to the associated lesson

    // Constructor
    public SwimStudent(String name, SwimLevel level) {
        this.name = name;
        this.level = level;
        this.qualifications = new ArrayList<>();

    }
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public SwimLevel getLevel() {
        return level;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setLevel(SwimLevel level) {
        this.level = level;
    }

    public void addQualification(Qualification qualification) {
        qualifications.add(qualification);
    }
    
    // Method to associate a lesson with the student
    public void setSwimLesson(SwimLesson lesson) {
        this.swimClass = lesson;
    }

    // Method to get the swim lesson associated with the student
    public SwimLesson getSwimLesson() {
        return swimClass;
    }

    // Method to get the day and time of the swim lesson
    public String getDayAndTime() {
        if (swimClass != null) {
            return swimClass.getDayOfWeek() + ", " + swimClass.getStartTime();
        } else {
            return ""; // No lesson associated
        }
    }


    public boolean hasAchieved(Qualification qualification) {
        for (Qualification q:this.qualifications)
        {
            if (q.getName().equalsIgnoreCase(qualification.getName()) && q.getLevel().equals(qualification.getLevel()) && q.getInstructor().getName().equalsIgnoreCase(qualification.getInstructor().getName()))
                return true;
        }
        return false;
    }
}
