package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimStudent {
    
    private String name;
    private SwimLevel level;
    private List<Qualification> qualifications;

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
    
    // Method to get the swim lesson associated with the student
    public SwimLesson getSwimLesson() {
        // Add implementation to return the swim lesson associated with the student
        // This method should return the swim lesson object of the student, or null if none exists
        return null; // Placeholder, replace with actual implementation
    }

    // Method to get the day and time of the swim lesson
    public String getDayAndTime() {
        // Add implementation to return the day and time of the swim lesson
        // This method should return a String representing the day and time of the swim lesson,
        // or an empty string if no lesson is associated with the student
        return ""; // Placeholder, replace with actual implementation
    }
    

}
