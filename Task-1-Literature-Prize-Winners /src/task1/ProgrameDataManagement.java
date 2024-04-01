package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProgrameDataManagement {

    List<LiteraturePrize> ReadInPrizes = new ArrayList<>();

    public void start() {

        // testing  printPrizes
//        printPrizes();
        ReadData reader = new ReadData();
        ReadInPrizes = reader.readPrizesFromFile();
        Scanner scanner = new Scanner(System.in);

//        for (LiteraturePrize prize: ReadInPrizes  ) { 
//            System.out.println(prize);
//        }
        String choice;
        do {
            System.out.println("----------------------");
            System.out.println("Literature prize menu");
            System.out.println("----------------------");
            System.out.println("List ................1");
            System.out.println("Select ..............2");
            System.out.println("Search...............3");
            System.out.println("Exit.................0");
            System.out.println("----------------------");

            System.out.print("\nEnter choice:> ");
            choice = scanner.nextLine();

            //while loop to check the user input (regex)  (0123) {1} (String)
            switch (choice) {
                case "1":
                    listPrizeWinnersByYearRange(scanner);
                    break;
                case "2":
                    selectPrizeWinner(scanner);
                    break;
                case "3":
                    searchByGenre(scanner);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again. Select one of the following categories [1,2,3,0] from the Literature prize menu.");
            }
        } while (!"0".equals(choice));
    }

//    // testing  printPrizes
//    public void printPrizes() {
//        List<LiteraturePrize> prizes = ReadInPrizes;
//        System.out.println("----------------------");
//        System.out.println("List of Literature Prizes");
//        System.out.println("----------------------");
//        for (LiteraturePrize prize : prizes) {
//            System.out.println(prize);
//        }
//    }
    public void listPrizeWinnersByYearRange(Scanner scanner) {
    int startYear;
    int endYear;

    // Prompt the user to enter the start year within the valid range
    do {
        System.out.println("Enter start year > (1901-2022):");
        startYear = Integer.parseInt(scanner.nextLine());
        if (startYear < 1901 || startYear > 2022) {
            System.out.println("Invalid year. Please enter a year between 1901 and 2022.");
        }
    } while (startYear < 1901 || startYear > 2022);

    // Prompt the user to enter the end year within the valid range
    do {
        System.out.println("Enter end year > (1901-2022):");
        endYear = Integer.parseInt(scanner.nextLine());
        if (endYear < 1901 || endYear > 2022) {
            System.out.println("Invalid year. Please enter a year between 1901 and 2022.");
        } else if (endYear < startYear) {
            System.out.println("End year must be greater than or equal to the start year.");
        }
    } while (endYear < 1901 || endYear > 2022 || endYear < startYear);

    System.out.println("-----------------------------------------------------------------------------------------------------");
    System.out.println("| Year | Prize winners (and associated nations)                                                     |");
    System.out.println("-----------------------------------------------------------------------------------------------------");

    for (LiteraturePrize prize : ReadInPrizes) {
        int prizeYear = Integer.parseInt(prize.getYear());
        if (prizeYear >= startYear && prizeYear <= endYear) {
            StringBuilder winnersBuilder = new StringBuilder();
            for (Laureate winner : prize.getWinners()) {
                String winnerName = winner.getName().replaceAll("\\s*\\(\\d{4}-\\d{4}\\)\\s*", "").replaceAll("\\s*\\(b\\.\\s*\\d{4}\\)\\s*", "");
                winnersBuilder.append(winnerName);
                List<String> nations = winner.getNations();
                if (nations != null && !nations.isEmpty()) {
                    winnersBuilder.append(" [").append(String.join(", ", nations)).append("]");
                }
                winnersBuilder.append(" | ");
            }
            String prizeYearString = prize.getYear();
            String formattedYear;
            if (winnersBuilder.length() > 0) {
                // Remove the last " | " separator if there are winners
                formattedYear = String.format("| %-4s | %-90s |", prizeYearString, winnersBuilder.substring(0, winnersBuilder.length() - 3));
            } else {
                // If no winners, mark as "NOT AWARDED"
                formattedYear = String.format("| %-4s | %-90s |", prizeYearString, "NOT AWARDED");
            }
            System.out.println(formattedYear);
        }
    }

    System.out.println("-----------------------------------------------------------------------------------------------------");
}




    public void selectPrizeWinner(Scanner scanner) {
        System.out.println("Enter year of prize: > ");
        String year = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        //LiteraturePrize prize = data.getPrizeWinnerByYear(year);
//        if (prize != null) {
//            System.out.println(prize);
//        } else {
//            System.out.println("No prize winner found for the year " + year);
//        }
    }

    public void searchByGenre(Scanner scanner) {
        System.out.println("Enter search term for writing genre: > ");
        String searchGenre = scanner.nextLine().toLowerCase();

        //List<Laureate> matchingLaureates = data.searchLaureatesByGenre(searchGenre);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Name                                   | Genres                                                       | Year   |");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

//        for (Laureate laureate : matchingLaureates) {
//            System.out.println(laureate);
//        }
    }

    public static boolean yearPatternCorrect(String year) {
        String regex = "^[0123456789]{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(year).matches();
    }

}
