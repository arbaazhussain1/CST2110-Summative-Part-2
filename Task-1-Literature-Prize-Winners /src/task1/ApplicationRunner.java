package task1;

import java.io.File;

public class ApplicationRunner {

    public static void main(String[] args) {
          ReadData reader = new ReadData();
          reader.readAndProcessFile();
          
        ProgrameDataManagement dataManagement = new ProgrameDataManagement();
        dataManagement.start();

    
    }

}
