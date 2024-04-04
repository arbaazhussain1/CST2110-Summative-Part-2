package task2;

public class ApplicationRunner {

    public static void main(String[] args) {
        SwimSchoolManagementSystem system = new SwimSchoolManagementSystem();
        ProgrameDataManagement program = new ProgrameDataManagement();
        program.Start(); // Call the run method to start the program
    }

}
