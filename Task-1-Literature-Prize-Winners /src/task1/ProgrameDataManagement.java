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

//        for (LiteraturePrize prize: ReadInPrizes  ) { 
//            for (Laureate laureate : prize.getWinners()){
////                System.out.println(laureate.getBirthDeath().get(0));
//            }    
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

    public void listPrizeWinnersByYearRange(Scanner scanner) {
        int startYear = 0;
        int endYear = 0;

        // Prompt the user to enter the start year within the valid range
        do {
            System.out.println("Enter start year > (1901-2022):");
            String startInput = scanner.nextLine().trim(); // Get the input and remove leading/trailing whitespace
            if (startInput.length() != 4 || !startInput.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid year between 1901 and 2022.");
                continue; // Restart the loop to prompt for input again
            }
            startYear = Integer.parseInt(startInput);
            if (startYear < 1901 || startYear > 2022) {
                System.out.println("Invalid year. Please enter a year between 1901 and 2022.");
            }
        } while (startYear < 1901 || startYear > 2022);

        // Prompt the user to enter the end year within the valid range
        do {
            System.out.println("Enter end year > (1901-2022):");
            String endInput = scanner.nextLine().trim(); // Get the input and remove leading/trailing whitespace
            if (endInput.length() != 4 || !endInput.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid year between 1901 and 2022.");
                continue; // Restart the loop to prompt for input again
            }
            endYear = Integer.parseInt(endInput);
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
        int year = 0; // Initialize year outside the loop

        do {
            System.out.println("Enter year of prize > (1901-2022): ");
            String yearInput = scanner.nextLine().trim(); // Get the input and remove leading/trailing whitespace
            if (yearInput.length() != 4 || !yearInput.matches("\\d{4}")) {
                System.out.println("Invalid input. Please enter a valid four-digit year between 1901 and 2022.");
                continue; // Restart the loop to prompt for input again
            }
            year = Integer.parseInt(yearInput);
            if (year < 1901 || year > 2022) {
                System.out.println("Invalid year. Please enter a year between 1901 and 2022.");
            }
        } while (year < 1901 || year > 2022);

        // Print the header line with straight "|" characters
        System.out.println("------------------------------------------------------------------------------------------------------------");

        // Print the column headers with fixed widths
        System.out.printf("| %-25s | %-4s | %-4s | %-30s | %-30s |\n", "Winner(s)", "Born", "Died", "Language(s)", "Genre(s)");

        // Print the separator line between header and data
        System.out.println("------------------------------------------------------------------------------------------------------------");

        boolean findWinners = false;
        for (LiteraturePrize prize : ReadInPrizes) {
            int prizeYear = Integer.parseInt(prize.getYear());

            if (prizeYear == year) {
                if (prize != null) {
                    findWinners = true;
                    for (Laureate laureate : prize.getWinners()) {
                        // Print winner information
                        List<String> languages = laureate.getLanguages();
                        List<String> genres = laureate.getGenres();

                        // Determine the maximum number of lines needed for languages and genres
                        int maxLines = Math.max(languages.size(), genres.size());

                        // Print the languages and genres
                        // Print the languages and genres
                        for (int i = 0; i < maxLines; i++) {
                            if (i == 0) {
                                // Print the first language and genre in the same line as the laureate's name
                                System.out.printf("| %-25s | %-4s | %-4s | %-30s | %-30s |\n",
                                        laureate.getName(), laureate.getBirthDeath().get(0), laureate.getBirthDeath().get(1),
                                        i < languages.size() ? languages.get(i) : "", i < genres.size() ? genres.get(i) : "");
                            } else {
                                // For subsequent lines, print only languages and genres
                                // If there are no more languages to print, pad with an empty string
                                String lang = i < languages.size() ? languages.get(i) : "";
                                // If there are no more genres to print, pad with an empty string
                                String genre = i < genres.size() ? genres.get(i) : "";
                                // Print the line with empty placeholders for name, birth, and death
                                System.out.printf("| %-25s | %-4s | %-4s | %-30s | %-30s |\n",
                                        "", "", "", lang.trim(), genre.trim());
                            }
                        }

                        // Print the separator line between data and citation
                        System.out.println("------------------------------------------------------------------------------------------------------------");

                        // Print the Citation line with proper alignment
                        System.out.println("|                                                Citation:                                                  |");
                        System.out.println("|                                                                                                           |");

                        String citation = String.join(" ", laureate.getCitation());
                        printCenteredCitation(citation, 105); // Adjusted formatting here

                        // Print the separator line between citation and next record
                        System.out.println("------------------------------------------------------------------------------------------------------------");
                    }
                }
            }
        }
        if (!findWinners) {
            System.out.println("No prize winner found for the year " + year);
        }
    }

// Helper method to print citation centered and aligned with '|'
    private void printCenteredCitation(String citation, int width) {
        String[] lines = citation.split("\\r?\\n");
        for (String line : lines) {
            // Split the line into segments that fit within the width
            StringBuilder currentLine = new StringBuilder();
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (currentLine.length() + word.length() + 1 <= width) { // Add 1 for the space after each word
                    if (currentLine.length() > 0) {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                } else {
                    printCenteredTextWithAlignment(currentLine.toString(), width);
                    currentLine = new StringBuilder(word);
                }
            }
            // Print the remaining part of the line
            if (currentLine.length() > 0) {
                printCenteredTextWithAlignment(currentLine.toString(), width);
            }
        }
    }

// Helper method to print text centered with '|' characters aligned
    private void printCenteredTextWithAlignment(String text, int width) {

        int totalSpaces = width - text.length(); // Total available spaces
        int sideSpaces = totalSpaces / 2; // Equal spaces on both sides of the text
        int remainingSpaces = totalSpaces % 2; // Check for any remaining spaces
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < sideSpaces; i++) {
            padding.append(" ");
        }
        // Adjust the format to align '|' characters with the rest of the text
        System.out.printf("|%s %s %s%s|\n", padding, text, remainingSpaces > 0 ? " " : "", padding);
    }

    private List<String> splitStringIntoLines(List<String> input, int lineLength) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        for (String word : input) {
            if (currentLine.length() + word.length() + 1 <= lineLength) {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        return lines;
    }

    public void searchByGenre(Scanner scanner) {
        System.out.println("Enter search term for writing genre > ");
        String searchGenre = scanner.nextLine().toLowerCase();

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-38s | %-62s | %-4s |\n", "Name", "Genres", "Year");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (LiteraturePrize prize : ReadInPrizes) {
            for (Laureate laureate : prize.getWinners()) {
                List<String> genres = laureate.getGenres();
                if (genres != null) {
                    for (String genre : genres) {
                        if (genre.toLowerCase().contains(searchGenre)) {
                            System.out.printf("| %-38s | %-62s | %-4s |\n", laureate.getName(), genre, prize.getYear());
                            System.out.println("------------------------------------------------------------------------------------------------------------------");
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean yearPatternCorrect(String year) {
        String regex = "^[0123456789]{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(year).matches();
    }

}
