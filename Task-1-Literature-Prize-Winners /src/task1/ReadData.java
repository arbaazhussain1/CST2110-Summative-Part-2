package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReadData {

    String fileLocation = System.getProperty("user.dir");
    String dataPath = fileLocation + File.separator + "literature-prizes.txt";

    public List<LiteraturePrize> readPrizesFromFile() {
        List<LiteraturePrize> prizes = new ArrayList<>();
        LiteraturePrize currentPrize = null;
        String endOfPrize = "-----";
        String notAwardedStr = "Not awarded";

        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            String year = null;

            List<Laureate> winners = new ArrayList<>();

            // Define regex patterns for matching laureate information
            Pattern laureatePattern = Pattern.compile("^(.+)\\|(.+)\\|(.+)$");

            Pattern yearFile = Pattern.compile("^\\d{4}$");
            Pattern nameFile = Pattern.compile("([^\\(]+)\\(");

//            Pattern birth_deathFile = Pattern.compile("\\((\\d{4}-\\d{4}|b\\.\\s*\\d{4})\\)");
//Pattern birth_deathFile = Pattern.compile("\\((\\d{4}-\\d{4}|\\d{4})\\)");
//Pattern birth_deathFile = Pattern.compile("\\((\\d{4}-\\d{4}|\\d{4})\\)");
//Pattern birth_deathFile = Pattern.compile("\\(b\\.\\s*(\\d{4})\\)");
//Pattern birth_deathFile = Pattern.compile("\\((\\d{4}-\\d{4}|b\\.\\s*(\\d{4}))\\)");
//Pattern birth_deathFile = Pattern.compile("\\((?:\\d{4}-\\d{4}|b\\.\\s*)?(\\d{4})\\)");
            Pattern birth_deathFile = Pattern.compile("\\((?:\\d{4}-\\d{4}|b\\.\\s*)?(\\d{4}(?:-\\d{4})?)\\)");

//            Pattern nationsFile = Pattern.compile("^\\|([^|]+)\\|$");
Pattern nationsFile = Pattern.compile("\\|([^|]+)\\|");

            Pattern languagesFile = Pattern.compile("^\\|([^|]+)\\|$");
            Pattern citationFile = Pattern.compile("^(.*?)$");
            Pattern genresFile = Pattern.compile("^([^|]+)$");

            while ((line = reader.readLine()) != null) {
//                 System.out.println(line);
                Matcher yearMatcher = yearFile.matcher(line);

                if (yearMatcher.find()) {

                    year = yearMatcher.group(0).trim();
                    currentPrize = new LiteraturePrize(year);
                    prizes.add(currentPrize);

//                    System.out.println(year.toString());
                } else if (line.equals("Not awarded")) {
                    // If the line indicates the prize was not awarded, move to the next line
                    continue;

                } else if (line.equals(endOfPrize)) {
                    // If the line marks the end of laureate information, move to the next line
                    continue;
                } else {
                    Matcher laureateMatcher = laureatePattern.matcher(line);
//                    System.out.println(laureateMatcher.matches());
                    if (laureateMatcher.matches()) {

                        Matcher nameMatcher = nameFile.matcher(line);
                        String name = "";
                        String birth_death = "";
//                                                System.out.println(nameMatcher.matches());

                        if (nameMatcher.find()) {
                            name = nameMatcher.group(1);
//                            System.out.println(name);
                        }

                        Matcher birth_deathMatcher = birth_deathFile.matcher(line);
//                                                 System.out.println(birth_deathMatcher.matches());
                        if (birth_deathMatcher.find()) {
                            birth_death = birth_deathMatcher.group(1);
//                                  System.out.println(birth_death);

                        }

                        List<String> birthDeathList;

                        if (birth_death != null && birth_death.startsWith("b.")) {
                            String birthYear = birth_death.substring(3, 7);
                            birthDeathList = new ArrayList<>();
                            birthDeathList.add(birthYear);
                            birthDeathList.add("----");
                        } else {
                            // Otherwise, split birth_death normally
                            birthDeathList = new ArrayList<>();
                            String[] birthDeathArray = birth_death.split("-");
                            for (String yearElement : birthDeathArray) {
                                birthDeathList.add(yearElement);
                            }
                        }
//                        System.out.println(birthDeathList);

//                        for (String Element : birthDeathList) {
//                            System.out.println(Element);
//                        } 
                        Matcher nationsMatcher = nationsFile.matcher(line); // Match nations
                         String nations = "";
//                        System.out.println(nationsMatcher.matches());

                        if (nationsMatcher.find()) {
                             nations = nationsMatcher.group(1);
//    System.out.println("Matcher matches: " + nationsMatcher.matches()); // Print result of matches() method
                                                        System.out.println(nations);

                        } else {
                                   System.out.println("No nations found in the line: " + line);

                        }
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(prizes);
        return prizes;
    }

    public static boolean yearPattern(String year) {
        String regex = "^[0123456789]{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(year).matches();
    }
}
