package task2;

abstract class Qualification {

    private SwimLevel level;
    private Instructor instructor; // Assuming each qualification is awarded by an instructor
        private String name;


    public Qualification(SwimLevel level, Instructor instructor) {
        this.level = level;
        this.instructor = instructor;
                this.name = name;

    }

    // Getter for level
    public SwimLevel getLevel() {
        return level;
    }

    // Getter for instructor
    public Instructor getInstructor() {
        return instructor;
    }
    
     public String getName() {
        return name;
    }
    

    // Abstract method for displaying qualification details
    public abstract String displayDetails();
}
