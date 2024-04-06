package task2;

public class DistanceSwim extends Qualification {
    private int distance;

    public DistanceSwim(SwimLevel level, Instructor instructor, int distance) {
        super(level, instructor);
        this.distance = distance;
    }

    // Getter for distance
    public int getDistance() {
        return distance;
    }

    // Override method to display qualification details
    @Override
    public String displayDetails() {
        return "DistanceSwim: Level - " + getLevel() + ", Distance - " + distance + " meters";
    }
}