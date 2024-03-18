package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReadData {

    String fileLocation = System.getProperty("user.dir");
    String dataPath = fileLocation + File.separator + "literature-prizes.txt";

    public List<LiteraturePrize> readPrizesFromFile() {
        List<LiteraturePrize> prizes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            String year = null;
            List<String> winners = new ArrayList<>();
            List<Laureate> laureates = new ArrayList<>();

            // Define regex patterns for matching laureate information
//          Pattern laureatePattern = Pattern.compile("^(.*?)\\((\\d{4}-\\d{0,4}|b\\.\\s*\\d{4})?\\)\\|(.*?)\\|(.*?)$");
            Pattern laureatePattern = Pattern.compile("^(.*?)\\((\\d{4}-\\d{0,4}|b\\.\\s*\\d{4})?\\)\\|([^|]+)\\|([^|]+)$");

            Pattern yearFile = Pattern.compile("^\\d{4}$");
            Pattern nameFile = Pattern.compile("^[a-zA-Z\\s]+$");
            Pattern birth_deathFile = Pattern.compile("^\\((\\d{4}-\\d{4}|b\\.\\s*\\d{4}|\\d{4}\\s+[-]+\\s+)\\)$");
            Pattern nationsFile = Pattern.compile("^\\|([^|]+)\\|$");
            Pattern languagesFile = Pattern.compile("^\\|([^|]+)\\|$");
            Pattern citationFile = Pattern.compile("^(.*?)$");
            Pattern genresFile = Pattern.compile("^([^|]+)$");

            while ((line = reader.readLine()) != null) { // while lines are there to be read
                if (line.matches("\\d{4}")) { // if its a year
                    if (year != null) {
//                        LiteraturePrize prize = new LiteraturePrize(year, winners.toArray(new String[0])); // what does this line do?
//                        prizes.add(prize); // add the year to prizes
                    }
                    year = line;
                    winners.clear();
                } else {
                    Matcher laureateMatcher = laureatePattern.matcher(line);
                    if (laureateMatcher.matches()) {
                        String name = laureateMatcher.group(1).trim();
                        String birthDeath = laureateMatcher.group(2) != null ? laureateMatcher.group(2).trim() : "Unknown";
                        String[] nations = laureateMatcher.group(3).split(",");
                        String[] languages = laureateMatcher.group(4).split(",");
                        String citation = laureateMatcher.group(5).trim();
                        String[] genres = laureateMatcher.group(6).split(",");

                       

                        winners.add(name);

//change to regex or work out what parts[] does?
//Regex x.no rulesets .group(), Pattern, Matcher. 
//Account for years that are 'NOT AWARDED'
                        System.out.println("year" + year);
                        System.out.println("name" + name);
//                        System.out.println("birth_death" + birth_death);
                        System.out.println("nations" + nations);
                        System.out.println("languages" + languages);
                        System.out.println("citation" + citation);
                        System.out.println("genres" + genres);

                        List<String> nationList = new ArrayList<>();
                        for (String nation : nations) {
                            nationList.add(nation.trim());
                        }

                        List<String> genreList = new ArrayList<>();
                        for (String genre : genres) {
                            genreList.add(genre.trim());
                        }

//                        laureates.add(new Laureate(name, birth_death, nationList, genreList, citation));
                        winners.add(name);
                    }
                }

                if (year != null) {
//                    LiteraturePrize prize = new LiteraturePrize(year, winners.toArray(new String[0]));
//                    prizes.add(prize);
                }
            } 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(prizes);
            return prizes;
    }

    // Nations, Languages, Genres, birthAndDeath -- Lists
//      -----
//      1907
//      Rudyard Kipling(1865-1936)|United Kingdom|English
//      "in consideration of the power of observation, originality of imagination, virility of ideas and remarkable talent for narration that characterize the creations of this world-famous author"
//      novel, short story, poetry
//      -----
//      -----
//      2019
//      Peter Handke (b. 1942)|Austria|German
//      "for an influential work that with linguistic ingenuity has explored the periphery and the specificity of human experience"
//      novel, short story, drama, essay, translation, screenplay
//      -----
    public void readAndProcessFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        // Process each line here
        System.out.println(line); // For demonstration
    }

//    public static boolean yearPattern(String year) {
//        String regex = "^[0123456789]{4}$";     
//        Pattern pattern = Pattern.compile(regex);    
//        return pattern.matcher(year).matches();
//    }
}
