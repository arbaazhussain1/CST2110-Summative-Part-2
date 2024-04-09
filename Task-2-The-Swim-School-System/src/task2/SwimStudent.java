package task2;

import java.util.ArrayList;
import java.util.List;

// Class representing a swim student
public class SwimStudent {

    private String name; // Student's name
    private SwimLevel level; // Student's swim level
    private List<Qualification> qualifications; // List of qualifications achieved by the student

    private SwimLesson swimClass; // Reference to the associated lesson
    private SwimQualification swimQualification; // Student's swim qualification
    private int maximumAward = 0;  // Maximum award the student can achieve

    // Constructor
    public SwimStudent(String name, SwimLevel level) {
        this.name = name;
        this.level = level;
        this.qualifications = new ArrayList<>();

    }

    // Getter for the student's name
    public String getName() {
        return name;
    }

    // Getter for the student's swim level
    public SwimLevel getLevel() {
        return level;
    }

    // Getter for the list of qualifications achieved by the student
    public List<Qualification> getQualifications() {
        return qualifications;
    }

    // Setter for the student's swim level
    public void setLevel(SwimLevel level) {
        this.level = level;
    }

    // Method to associate a lesson with the student
    public void addQualification(Qualification qualification) {
        qualifications.add(qualification);
    }

    // Method to get the swim lesson associated with the student
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

    // Method to check if the student has achieved a specific qualification
    public boolean hasAchieved(Qualification qualification) {
        for (Qualification q : this.qualifications) {
            if (q.getName().equalsIgnoreCase(qualification.getName()) && q.getLevel().equals(qualification.getLevel()) && q.getInstructor().getName().equalsIgnoreCase(qualification.getInstructor().getName())) {
                return true;
            }
        }
        return false;
    }

    // Method to get the student's swim qualification
    public SwimQualification getSwimQualification() {
        return swimQualification;
    }

    // Method to set the student's swim qualification
    public void setSwimQualification(SwimQualification swimQualification) {
        this.swimQualification = swimQualification;
    }

    // Method to check if the student already has a specific qualification
    public boolean isAlreadyHasQualification(Qualification qualification) {

        for (Qualification q : this.qualifications) {
            if (qualification instanceof DistanceSwim) {
                DistanceSwim swim = (DistanceSwim) qualification;
                if (q instanceof DistanceSwim) {
                    DistanceSwim ds = (DistanceSwim) q;
                    if (ds.getName().equalsIgnoreCase(swim.getName()) && ds.getDistance() == swim.getDistance()) {
                        return true;
                    }
                }
            } else if (qualification instanceof PersonalSurvival) {
                PersonalSurvival survival = (PersonalSurvival) qualification;
                if (q instanceof PersonalSurvival) {
                    PersonalSurvival ps = (PersonalSurvival) q;
                    if (ps.getName().equalsIgnoreCase(survival.getName()) && ps.getSurvivalLevel().equalsIgnoreCase(survival.getSurvivalLevel())) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    // Getter for the maximum award
    public int getMaximumAward() {
        return maximumAward;
    }

    // Setter for the maximum award
    public void setMaximumAward(int maximumAward) {
        this.maximumAward = maximumAward;
    }
}
