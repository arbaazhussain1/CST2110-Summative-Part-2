package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimStudent {
    
    private String name;
    private SwimLevel level;
    private List<Qualification> qualifications;
    private List<Qualification> distanceSwimQualifications;
    private List<Qualification> personalSurvivalQualifications;
    private SwimLesson swimClass; // Reference to the associated lesson

    // Constructor
    public SwimStudent(String name, SwimLevel level) {
        this.name = name;
        this.level = level;
        this.qualifications = new ArrayList<>();
        this.distanceSwimQualifications = new ArrayList<>();
        this.personalSurvivalQualifications = new ArrayList<>();
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
    
    public List<Qualification> getDistanceSwimQualifications() {
        return distanceSwimQualifications;
    }

    public List<Qualification> getPersonalSurvivalQualifications() {
        return personalSurvivalQualifications;
    }

}
