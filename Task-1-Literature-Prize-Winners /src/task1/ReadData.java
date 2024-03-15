
package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    String fileLocation = System.getProperty("user.dir");
    String dataPath = fileLocation + File.separator + "literature-prizes.txt";
    

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


    
}
