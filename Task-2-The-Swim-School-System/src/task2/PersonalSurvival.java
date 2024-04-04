package task2;

public class PersonalSurvival extends Qualification {

    private String survivalLevel;

    public PersonalSurvival(SwimLevel level, String survivalLevel) {
        super(level);
        this.survivalLevel = survivalLevel;
    }

    // Getter for survival level
    public String getSurvivalLevel() {
        return survivalLevel;
    }

    // Override method to display qualification details
    @Override
    public String displayDetails() {
        return "PersonalSurvival: Level - " + getLevel() + ", Survival Level - " + survivalLevel;
    }
}
