package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadData {

    String fileLocation = System.getProperty("user.dir");
    String dataPath = fileLocation + File.separator + "literature-prizes.txt";

    public List<LiteraturePrize> readPrizesFromFile() {
        List<LiteraturePrize> prizes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            LiteraturePrize currentPrize = null;
            String endOfPrize = "-----";

            while ((line = reader.readLine()) != null) {
//                System.out.println("Processing line: " + line);
                Matcher yearMatcher = Pattern.compile("^(\\d{4})\\s*$").matcher(line);

                if (yearMatcher.find()) {
                    String year = yearMatcher.group(1).trim();
//                    System.out.println("Found year: " + year);
                    if (currentPrize != null) {
                        prizes.add(currentPrize);
                    }
                    currentPrize = new LiteraturePrize(year);
                } else if (line.equals("Not awarded")) {
                    Laureate notAwarded = new Laureate("Not awarded", null, null, null, null, null);
                    if (currentPrize != null) {
                        currentPrize.setWinners(notAwarded);
                    } else {
                        System.out.println("Error: currentPrize is null");
                    }
                } else if (line.equals(endOfPrize) || line.isBlank()) {
                    if (currentPrize != null) {
                        prizes.add(currentPrize);
                        currentPrize = null;
                    } else {
                        System.out.println("Encountered endOfPrize or blank line but currentPrize is null");
                    }
                } else {
                    List<String> birthDeathList = new ArrayList<>();
                    List<String> nationsList = new ArrayList<>();
                    List<String> languagesList = new ArrayList<>();
                    List<String> citationList = new ArrayList<>();
                    List<String> genresList = new ArrayList<>();

//                    Matcher laureateMatcher = Pattern.compile("^(.+?)\\|(.+?)\\|(.+)$").matcher(line);
                    Matcher laureateMatcher = Pattern.compile("^(.+?)\\((?:\\d{4}-\\d{4}|b\\.\\s*)?(\\d{4}(?:-\\d{4})?)\\)\\|([^|]+)\\|([^|]+)$").matcher(line);

                    if (laureateMatcher.matches()) {
                        String name = laureateMatcher.group(1).trim();
//                       

                        Matcher birth_deathMatcher = Pattern.compile("\\((?:\\d{4}-\\d{4}|b\\.\\s*)?(\\d{4}(?:-\\d{4})?)\\)").matcher(line);
                        if (birth_deathMatcher.find()) { // Check if a match is found
                            String birth_death = birth_deathMatcher.group(1); // Access the matched value
                            // Process birth_death
                            if (birth_death != null && birth_death.startsWith("b.")) {
                                String birthYear = birth_death.substring(3, 7);
                                birthDeathList.add(birthYear);
                                birthDeathList.add("----");
                            } else {
                                String[] birthDeathArray = birth_death.split("-");
                                if (birthDeathArray.length > 1) {
                                    birthDeathList.add(birthDeathArray[0]);
                                    birthDeathList.add(birthDeathArray[1]);
                                } else {
                                    birthDeathList.add(birthDeathArray[0]);
                                    birthDeathList.add("----");
                                }
                            }
                        }

                        // Process nations
                        Matcher nationsMatcher = Pattern.compile("\\|([^|]+)\\|").matcher(line);
                        if (nationsMatcher.find()) {
                            String nations = nationsMatcher.group(1);
                            String[] nationsArray = nations.split(",");
                            for (String nationsElement : nationsArray) {
                                String[] individualNations = nationsElement.split("\\s*,\\s*"); // Split individual nations by comma
                                for (String nation : individualNations) {
                                    nationsList.add(nation.trim()); // Add each nation to the list after trimming whitespace
                                }
                            }
                        }

                        // Process languages
                        Matcher languagesMatcher = Pattern.compile("[^|]+$").matcher(line);
                        if (languagesMatcher.find()) {
                            String languagesData = languagesMatcher.group(); // Access the matched value
                            String[] languagesArray = languagesData.split(",");
                            for (String languagesElement : languagesArray) {
                                String[] individualLanguages = languagesElement.trim().split("\\s*,\\s*"); // Split individual languages by comma
                                for (String language : individualLanguages) {
                                    languagesList.add(language.trim()); // Add each language to the list after trimming whitespace
                                }
                            }
                        }

                        // Read the next line for citation
                        String citationLine = reader.readLine();
                        if (citationLine != null) {
                            Matcher citationMatcher = Pattern.compile("^\"(.+)\"$").matcher(citationLine);
                            if (citationMatcher.find()) {
                                String citation = "\"" + citationMatcher.group(1) + "\""; // Concatenate double quotes
                                String[] citationArray = citation.split(" ");
                                for (String citationElement : citationArray) {
                                    citationList.add(citationElement);
                                }
                            } else {
                                System.out.println("Invalid citation format: " + citationLine);
                            }
                            // Read the next line for genres
                            String genresLine = reader.readLine();
                            if (genresLine != null) {
                                Matcher genresMatcher = Pattern.compile("^([^|]+)$").matcher(genresLine);
                                if (genresMatcher.find()) {
                                    String genres = genresMatcher.group(1);
                                    String[] genresArray = genres.split(",");
                                    for (String genresElement : genresArray) {
                                        genresList.add(genresElement);
                                    }
                                } else {
                                    System.out.println("Invalid genres format: " + genresLine);
                                }
                            }
                        }

                        // Check if currentPrize is null
                        if (currentPrize != null) {
                            // Create and add the laureate to the list of winners for the current prize
                            Laureate laureate = new Laureate(name, birthDeathList, nationsList, languagesList, citationList, genresList);
                            currentPrize.setWinners(laureate);
                        } else {
                            System.out.println("Error: currentPrize is null");
                        }
                    }
                }
            }

            // Add the last prize if it's not null
            if (currentPrize != null) {
                prizes.add(currentPrize);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prizes;
    }
}
