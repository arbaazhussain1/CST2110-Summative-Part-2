    package task1;

    import java.util.ArrayList;
    import java.util.List;
    import task1.Laureate;

    public class LiteraturePrize {

        private String year;

        private List<Laureate> winners;

        public LiteraturePrize(String year) {
            this.year = year;
            this.winners = new ArrayList<>();
        }

        public String getYear() {
            return year;
        }

        public List<Laureate> getWinners() {
            return winners;
        }

        public void setWinners(Laureate winners) {
            this.winners.add(winners);
        }

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
