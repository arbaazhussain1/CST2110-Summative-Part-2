package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProgrameDataManagement {

    List<LiteraturePrize> ReadInPrizes = new ArrayList<>();

    public void start() {
        ReadData reader = new ReadData();
        ReadInPrizes = reader.readPrizesFromFile();
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!"0".equals(choice));
    }

    public void listPrizeWinnersByYearRange(Scanner scanner) {
        System.out.println("Enter start year: > ");
        String startYear = scanner.nextLine();

        while (!yearPatternCorrect(startYear)) {
            System.out.println("Retry the input");
            startYear = scanner.nextLine();
        };

        System.out.println("Enter end year: > ");
        String endYear = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        // search through the list (ReadInPrizes), present the years that are between the user input.
        // add to filtered prizes.  
        List<LiteraturePrize> filteredPrizes;
        // create a sb
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("| Year | Prize winners (and associated nations)                                |");
        System.out.println("-----------------------------------------------------------------------------");

//        for (LiteraturePrize prize : filteredPrizes) {
//            System.out.println(prize.toString());
//        }
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