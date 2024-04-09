package task2;

// Abstract class for swim qualifications
abstract class Qualification {

    // Instance variables
    private SwimLevel level; // Level of the qualification
    private Instructor instructor; // Instructor who awards the qualification
    private String name;  // Name of the qualification

    // Constructor
    public Qualification(SwimLevel level, Instructor instructor, String name) {
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

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for instructor
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

}
