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
            Pattern laureatePattern = Pattern.compile("^(.*?)\\((\\d{4}-\\d{0,4}|b\\.\\s*\\d{4})?\\)\\|([^|]+)\\|([^|]+)\\|\"(.*?)\"$");

            Pattern yearFile = Pattern.compile("^\\d{4}$");
            Pattern nameFile = Pattern.compile("^[a-zA-Z\\s]+$");
            Pattern birth_deathFile = Pattern.compile("^\\((\\d{4}-\\d{4}|b\\.\\s*\\d{4}|\\d{4}\\s+[-]+\\s+)\\)$");
            Pattern nationsFile = Pattern.compile("^\\|([^|]+)\\|$");
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

                    System.out.println(year.toString());

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
