package task2;

public class DistanceSwim extends Qualification {

    private int distance;

    public DistanceSwim(SwimLevel level, Instructor instructor, int distance) {
        super(level, instructor, "DistanceSwim");
        this.distance = distance;
    }

    // Getter for distance
    public int getDistance() {
        return distance;
    }

    // Override method to display qualification details
    @Override
    public String toString() {
        return "DistanceSwim: Level - " + getLevel() + ", Distance - " + distance + " meters" + " (Instructor: " + getInstructor() + " )";

    }

    public void setDistance(int i) {
        this.distance = i;
    }
}
