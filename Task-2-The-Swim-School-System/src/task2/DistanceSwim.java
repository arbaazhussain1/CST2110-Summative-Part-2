package task2;

// This class represents a DistanceSwim qualification, extending Qualification class
public class DistanceSwim extends Qualification {

    // Instance variable to store the distance of the swim
    private int distance;

    // Constructor to initialize a DistanceSwim qualification with level, instructor, and distance
    public DistanceSwim(SwimLevel level, Instructor instructor, int distance) {
        // Call the constructor of the superclass (Qualification) with appropriate parameters
        super(level, instructor, "DistanceSwim");
        // Set the distance of the swim
        this.distance = distance;
    }

    // Getter method for retrieving the distance of the swim
    public int getDistance() {
        return distance;
    }

    // Override toString method to provide a string representation of the DistanceSwim object
    @Override
    public String toString() {
        // Return a string representing the DistanceSwim object, including level, distance, and instructor details
        return "DistanceSwim: Level - " + getLevel() + ", Distance - " + distance + " meters" + " (Instructor: " + getInstructor() + " )";

    }

    // Setter method to update the distance of the swim
    public void setDistance(int i) {
        this.distance = i;
    }
}
