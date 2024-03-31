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
                Matcher yearMatcher = Pattern.compile("^\\d{4}$").matcher(line);
                if (yearMatcher.find()) {
                    String year = yearMatcher.group(0).trim();
                    currentPrize = new LiteraturePrize(year);
                } else if (line.equals("Not awarded")) {
                    Laureate notAwarded = new Laureate("Not awarded", null, null, null, null, null);
                    currentPrize.setWinners(notAwarded);
                    prizes.add(currentPrize);
                } else if (line.equals(endOfPrize) || line == null) {
                    prizes.add(currentPrize);
                } else {
                    List<String> birthDeathList = new ArrayList<>();
                    List<String> nationsList = new ArrayList<>();
                    List<String> languagesList = new ArrayList<>();
                    List<String> citationList = new ArrayList<>();
                    List<String> genresList = new ArrayList<>();

                    Matcher laureateMatcher = Pattern.compile("^(.+)\\|(.+)\\|(.+)$").matcher(line);
                    if (laureateMatcher.matches()) {
                        String name = laureateMatcher.group(1).trim();
                        String birth_death = laureateMatcher.group(3).trim();
                        String nations = laureateMatcher.group(2).trim();
                        String languages = laureateMatcher.group(3).trim();

                        // Process birth_death
                        if (birth_death != null && birth_death.startsWith("b.")) {
                            String birthYear = birth_death.substring(3, 7);
                            birthDeathList.add(birthYear);
                            birthDeathList.add("----");
                        } else {
                            String[] birthDeathArray = birth_death.split("-");
                            for (String yearElement : birthDeathArray) {
                                birthDeathList.add(yearElement);
                            }
                        }

                        // Process nations
                        String[] nationsArray = nations.split(",");
                        for (String nationsElement : nationsArray) {
                            nationsList.add(nationsElement);
                        }

                        // Process languages
                        String[] languagesArray = languages.split(",");
                        for (String languagesElement : languagesArray) {
                            languagesList.add(languagesElement);
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

                        // Create and add the laureate to the list of winners for the current prize
                        Laureate laureate = new Laureate(name, birthDeathList, nationsList, languagesList, citationList, genresList);
                        currentPrize.setWinners(laureate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prizes;
    }

}
