package task1;

// Importing necessary classes and utilities for data management
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The ProgrameDataManagement class manages literature prize data and provides
 * various functionalities.
 */
public class ProgrameDataManagement {

    // List to store literature prize data
    List<LiteraturePrize> ReadInPrizes = new ArrayList<>();

    /**
     * Starts the program and presents a menu to the user.
     */
    public void start() {
        // Initialize data reader
        ReadData reader = new ReadData();
        // Read prizes from file
        ReadInPrizes = reader.readPrizesFromFile();
        Scanner scanner = new Scanner(System.in);

        // Present menu options to the user
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

            // Process user choice
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

    /**
     * Lists prize winners within a specified year range.
     *
     * @param scanner Scanner object for user input
     */
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

        // Print the separator line to separate the header from the data
        System.out.println("-----------------------------------------------------------------------------------------------------");
        // Print the header line with the year and prize winners
        System.out.println("| Year | Prize winners (and associated nations)                                                     |");
        // Print another separator line to separate the header from the data
        System.out.println("-----------------------------------------------------------------------------------------------------");

        // Iterate through the list of literature prizes
        for (LiteraturePrize prize : ReadInPrizes) {
            // Extract the year of the current prize and convert it to an integer
            int prizeYear = Integer.parseInt(prize.getYear());
            // Check if the prize year falls within the specified start and end year range
            if (prizeYear >= startYear && prizeYear <= endYear) {
                // StringBuilder to construct the formatted string of prize winners and associated nations
                StringBuilder winnersBuilder = new StringBuilder();
                // Iterate through each winner of the current prize
                for (Laureate winner : prize.getWinners()) {
                    // Extract the name of the winner and remove unnecessary year annotations
                    String winnerName = winner.getName().replaceAll("\\s*\\(\\d{4}-\\d{4}\\)\\s*", "").replaceAll("\\s*\\(b\\.\\s*\\d{4}\\)\\s*", "");
                    winnersBuilder.append(winnerName); // Append the winner's name
                    // Get the list of nations associated with the winner, if available
                    List<String> nations = winner.getNations();
                    // Check if nations exist and are not empty
                    if (nations != null && !nations.isEmpty()) {
                        // Append the associated nations in square brackets
                        winnersBuilder.append(" [").append(String.join(", ", nations)).append("]");
                    }
                    // Append separator for multiple winners
                    winnersBuilder.append(" | ");
                }

                // Convert the prize year to a string
                String prizeYearString = prize.getYear();
                String formattedYear;
                // Check if there are winners for the prize
                if (winnersBuilder.length() > 0) {
                    // If there are winners, remove the last " | " separator and format the string
                    formattedYear = String.format("| %-4s | %-90s |", prizeYearString, winnersBuilder.substring(0, winnersBuilder.length() - 3));
                } else {
                    // If no winners, mark as "NOT AWARDED" and format the string
                    formattedYear = String.format("| %-4s | %-90s |", prizeYearString, "NOT AWARDED");
                }
                // Print the formatted string representing the current prize year and its winners
                System.out.println(formattedYear);
            }
        }
        // Print the bottom border after listing the prize winners
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    /**
     * Selects prize winner(s) for a specified year and displays their
     * information.
     *
     * @param scanner Scanner object for user input
     */
    public void selectPrizeWinner(Scanner scanner) {
        int year = 0; // Initialize year outside the loop

        // Loop until a valid year within the specified range is entered
        do {
            System.out.println("Enter year of prize > (1901-2022): ");
            String yearInput = scanner.nextLine().trim(); // Get the input and remove leading/trailing whitespace
            // Validate the input year format and range
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
        // Iterate through each literature prize to find the winners for the specified year
        for (LiteraturePrize prize : ReadInPrizes) {
            int prizeYear = Integer.parseInt(prize.getYear());

            if (prizeYear == year) {
                if (prize != null) {
                    findWinners = true;
                    // Iterate through each winner of the prize to print their information
                    for (Laureate laureate : prize.getWinners()) {
                        // Print winner information
                        List<String> languages = laureate.getLanguages();
                        List<String> genres = laureate.getGenres();

                        // Determine the maximum number of lines needed for languages and genres
                        int maxLines = Math.max(languages.size(), genres.size());

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
        // If no winners were found for the specified year, inform the user
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

    /**
     * Splits a list of words into lines of specified length.
     *
     * @param input The list of words to be split into lines
     * @param lineLength The maximum length of each line
     * @return A list of lines
     */
    private List<String> splitStringIntoLines(List<String> input, int lineLength) {
        List<String> lines = new ArrayList<>(); // Initialize a list to hold the lines
        StringBuilder currentLine = new StringBuilder();  // Initialize a StringBuilder to build each line
        for (String word : input) { // Iterate through each word in the input list
            if (currentLine.length() + word.length() + 1 <= lineLength) { // Check if adding the word exceeds the line length
                if (currentLine.length() > 0) { // Append a space if not the first word in the line
                    currentLine.append(" ");
                }
                currentLine.append(word); // Append the word to the current line
            } else { // If adding the word exceeds the line length
                lines.add(currentLine.toString());  // Add the current line to the list of lines
                currentLine = new StringBuilder(word); // Start a new line with the current word
            }
        }
        if (currentLine.length() > 0) { // Add the last line if not empty
            lines.add(currentLine.toString());
        }
        return lines; // Return the list of lines
    }

    /**
     * Searches prize winners by genre.
     *
     * @param scanner Scanner object for user input
     */
    public void searchByGenre(Scanner scanner) {
        String[] allowedGenres = {"Poetry", "Essay", "History", "Law", "Novel", "Drama",
            "Philology", "Short Story", "Biography", "Translation",
            "Screenplay", "Memoir", "Philosophy", "Song Lyrics", "Autobiography"}; // Define an array of allowed genres

        // Display available genres to the user
        System.out.println("All Genres That Are Available:");
        for (String genre : allowedGenres) { // Iterate through each allowed genre
            System.out.println(genre); // Print the genre
        }

        String searchGenre;  // Declare a variable to store the user's search term
        boolean validInput = false; // Initialize a flag to track valid input
        // Prompt user to enter the genre to search for
        do {
            System.out.println("Enter search term for writing genre > ");
            searchGenre = scanner.nextLine();  // Get user input
            if (searchGenre.matches("[a-zA-Z]+")) { // Check if the input contains only letters
                validInput = true; // Set the flag to true if the input is valid
            } else {
                System.out.println("Invalid input. Please enter only characters."); // Display an error message for invalid input
            }
        } while (!validInput); // Continue prompting until valid input is provided

        // Declare the final search term for use in lambda expression
        final String finalSearchGenre = searchGenre;

        List<String> displayGenres = new ArrayList<>(); // Initialize a list to hold the genres to be displayed
        boolean findGenre = false; // Initialize a flag to track if a matching genre is found
        // Print header and column labels for displaying search results
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-38s | %-70s | %-4s |\n", "Name", "Genres", "Year");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

        for (LiteraturePrize prize : ReadInPrizes) {  // Iterate through each literature prize
            for (Laureate laureate : prize.getWinners()) { // Iterate through each winner of the current prize
                List<String> genres = laureate.getGenres(); // Get the list of genres associated with the winner
                if (genres != null && genres.stream().anyMatch(genre -> genre.toLowerCase().contains(finalSearchGenre.toLowerCase()))) { // Check if genres exist for the winner and if any genre contains the search term
                    for (String genre : genres) { // Iterate through each genre
                        // Capitalize the matched part of the genre
                        if (genre.toLowerCase().contains(finalSearchGenre.toLowerCase())) {
                            genre = capitalizeMatchedPart(genre, finalSearchGenre);
                        }
                        displayGenres.add(genre); // Add the genre to the list of genres to be displayed
                    }
                    // Print the laureate's information along with the genres and the prize year
                    System.out.printf("| %-38s | %-70s | %-4s |\n", laureate.getName(), String.join(", ", displayGenres), prize.getYear());
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                    displayGenres.clear(); // Clear the list of genres to prepare for the next iteration
                    findGenre = true; // Set the flag to true since a matching genre is found
                }
            }
        }

        // Inform the user if no matching genre is found
        if (!findGenre) {
            System.out.println("No matching genre found.");
        }
    }

    // Helper method to capitalize the matched part of the genre
    private String capitalizeMatchedPart(String genre, String searchGenre) {
        int index = genre.toLowerCase().indexOf(searchGenre.toLowerCase());  // Find the index of the matched part
        String matchedPart = genre.substring(index, index + searchGenre.length()); // Extract the matched part of the genre
        return genre.replaceFirst(matchedPart, matchedPart.toUpperCase()); // Capitalize the matched part and return the modified genre
    }

    /**
     * Prints the allowed genres.
     */
    public void printAllowedGenres() {
        String[] allowedGenres = {"Poetry", "Essay", "History", "Law", "Novel", "Drama",
            "Philology", "Short Story", "Biography", "Translation",
            "Screenplay", "Memoir", "Philosophy", "Song Lyrics", "Autobiography"};

        // Display the allowed genres to the user
        System.out.println("Allowed Genres:");
        for (String genre : allowedGenres) {
            System.out.println(genre);
        }
    }

    /**
     * Checks if the input year matches the correct pattern.
     *
     * @param year The year to be checked
     * @return true if the pattern is correct, false otherwise
     */
    public static boolean yearPatternCorrect(String year) {
        String regex = "^[0123456789]{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(year).matches();
    }

}
