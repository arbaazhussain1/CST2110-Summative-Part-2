package task1;

// Import File class from java.io package to work with files
import java.io.File;

// Main class responsible for running the application
public class ApplicationRunner {

    // Entry point of the application
    public static void main(String[] args) {

        // Create an instance of ProgrameDataManagement to handle program data
        ProgrameDataManagement dataManagement = new ProgrameDataManagement();
        // Start the data management process
        dataManagement.start();

    }

}
