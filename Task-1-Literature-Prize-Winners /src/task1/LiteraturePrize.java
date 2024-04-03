package task1;

// Import necessary Java classes and the custom Laureate class from the same package
import java.util.ArrayList;
import java.util.List;
import task1.Laureate;

// Class representing a Literature Prize
public class LiteraturePrize {

    // Field to store the year of the prize
    private String year;

    // List to store the winners of the prize
    private List<Laureate> winners;

    // Constructor to initialize the LiteraturePrize object with the provided year
    public LiteraturePrize(String year) {
        this.year = year;
        this.winners = new ArrayList<>();
    }

    // Getter method to retrieve the year of the prize
    public String getYear() {
        return year;
    }

    // Getter method to retrieve the list of winners of the prize
    public List<Laureate> getWinners() {
        return winners;
    }

    // Method to add a winner to the list of winners
    public void setWinners(Laureate winners) {
        this.winners.add(winners);
    }

    // Override toString method to provide a string representation of the LiteraturePrize object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LiteraturePrize{");
        sb.append("year=").append(year);
        sb.append(", winners=").append(winners);
        sb.append('}');
        return sb.toString();
    }

}
