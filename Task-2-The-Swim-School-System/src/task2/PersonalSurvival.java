package task2;

public class PersonalSurvival extends Qualification {

    private String survivalLevel;

    public PersonalSurvival(SwimLevel level, Instructor instructor, String survivalLevel) {
        super(level, instructor, "PersonalSurvival");
        this.survivalLevel = survivalLevel;
    }

    // Getter for survival level
    public String getSurvivalLevel() {
        return survivalLevel;
    }

    // Override method to display qualification details
    @Override
    public String toString() {
        return this.survivalLevel + ": Level - " + getLevel() + " (Instructor: " + getInstructor() + " )";

    }

    public void setSurvivalLevel(String l) {
        this.survivalLevel = l;
    }
}
