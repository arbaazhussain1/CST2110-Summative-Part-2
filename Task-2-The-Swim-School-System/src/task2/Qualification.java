
package task2;


abstract class Qualification {
    private SwimLevel level;

    public Qualification(SwimLevel level) {
        this.level = level;
    }

    // Getter for level
    public SwimLevel getLevel() {
        return level;
    }

    // Abstract method for displaying qualification details
    public abstract String displayDetails();
}
    

