package task1;

import java.util.List;
import java.util.Scanner;

public class ProgrameDataManagement {

    public void start() {
        Scanner scanner = new Scanner(System.in);

      

        int choice;
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
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listPrizeWinnersByYearRange(scanner);
                    break;
                case 2:
                    selectPrizeWinner(scanner);
                    break;
                case 3:
                    searchByGenre(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
    
    
    
    public void listPrizeWinnersByYearRange(Scanner scanner) {
        System.out.println("Enter start year: > ");
        int startYear = scanner.nextInt();
        System.out.println("Enter end year: > ");
        int endYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<LiteraturePrize> filteredPrizes = data.filterPrizeWinnersByYearRange(startYear, endYear);

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("| Year | Prize winners (and associated nations)                                |");
        System.out.println("-----------------------------------------------------------------------------");

        for (LiteraturePrize prize : filteredPrizes) {
            System.out.println(prize);
        }
    }
    
    
     public void selectPrizeWinner (Scanner scanner) {
        System.out.println("Enter year of prize: > ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        LiteraturePrize prize = data.getPrizeWinnerByYear(year);

        if (prize != null) {
            System.out.println(prize);
        } else {
            System.out.println("No prize winner found for the year " + year);
        }
    }
     
     public void searchByGenre(Scanner scanner) {
        System.out.println("Enter search term for writing genre: > ");
        String searchGenre = scanner.nextLine().toLowerCase();

        List<Laureate> matchingLaureates = data.searchLaureatesByGenre(searchGenre);

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Name           | Born      | Died      | Nations       | Languages       | Genres               | Year   |");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Laureate laureate : matchingLaureates) {
            System.out.println(laureate);
        }
    }
    
}
