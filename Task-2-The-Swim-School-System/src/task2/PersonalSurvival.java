package task2;

// This class represents a PersonalSurvival qualification, extending the Qualification class
public class PersonalSurvival extends Qualification {

    // Instance variable to store the survival level
    private String survivalLevel;

    // Constructor to initialize a PersonalSurvival qualification with level, instructor, and survival level
    public PersonalSurvival(SwimLevel level, Instructor instructor, String survivalLevel) {
        // Call the constructor of the superclass (Qualification) with appropriate parameters
        super(level, instructor, "PersonalSurvival");
        // Set the survival level
        this.survivalLevel = survivalLevel;
    }

    // Getter method for retrieving the survival level
    public String getSurvivalLevel() {
        return survivalLevel;
    }

    // Override toString method to provide a string representation of the PersonalSurvival object
    @Override
    public String toString() {
        // Return a string representing the PersonalSurvival object, including survival level, level, and instructor details
        return this.survivalLevel + ": Level - " + getLevel() + " (Instructor: " + getInstructor() + " )";

    }

    // Setter method to update the survival level
    public void setSurvivalLevel(String l) {
        this.survivalLevel = l;
    }
}
