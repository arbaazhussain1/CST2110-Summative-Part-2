package task1;

import java.util.ArrayList;
import java.util.List;

public class LiteraturePrize {

    private String year;
    private List<Laureate> laureates;
    private String[] winners;

    public LiteraturePrize(String year, ArrayList laureates, String[] winners) {
        this.year = year;
        this.laureates = new ArrayList<>();
        this.winners = winners;
    }

    public String getYear() {
        return year;
    }

    public List<Laureate> getLaureates() {
        return laureates;
    }

    public String[] getWinners() {
        return winners;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Check the coursework pdf file for layout
        sb.append("Literature Prize Winners for The Year ").append(year).append(":\n");  // This line might not match the layout requirements.
        for (int i = 0; i < winners.length; i++) {
            sb.append(i + 1).append(". ").append(winners[i]).append("\n");
            sb.append("-----------------------------------------------------------------------------");
        }
        return sb.toString();

    }
}