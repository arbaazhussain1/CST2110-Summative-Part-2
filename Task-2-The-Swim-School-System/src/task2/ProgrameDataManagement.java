package task2;

import java.util.Scanner;

public class ProgrameDataManagement {

    // Console-based user interface menu
    public void Start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Swim School Management System");
            System.out.println("----------------------------------------");
            System.out.println("1. View swim student information");
            System.out.println("2. View swim lesson details");
            System.out.println("3. View instructor schedule");
            System.out.println("4. Add new swim student");
            System.out.println("5. Award swim qualification");
            System.out.println("6. Move swim student from waiting list");
            System.out.println("7. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim(); // Trim the input to remove leading and trailing spaces
            if (input.matches("\\d+")) { // Check if the input contains only digits
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        viewStudentInformation();
                        break;
                    case 2:
                        viewLessonDetails();
                        break;
                    case 3:
                        viewInstructorSchedule();
                        break;
                    case 4:
                        addNewStudent();
                        break;
                    case 5:
                        awardSwimQualification();
                        break;
                    case 6:
                        moveStudentFromWaitingList();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }
        } while (choice != 7);
    }

    private void viewStudentInformation() {
        // Implementation for use case 1
        // Prompt user to select a swim student from the list
        // Display swim student information
    }

    private void viewLessonDetails() {
        // Implementation for use case 2
        // Prompt user to select the day, time, and level of the swim class
        // Display swim lesson details
    }

    private void viewInstructorSchedule() {
        // Implementation for use case 3
        // Prompt user to select a swim instructor from the list
        // Display instructor's schedule
    }

    private void addNewStudent() {
        // Implementation for use case 4
        // Allow user to add a new swim student
        // Display available classes for novice level and allocate the student
    }

    private void awardSwimQualification() {
        // Implementation for use case 5
        // Allow user to award swim qualification to a student
        // Check if qualification leads to level upgrade and manage waiting list
    }

    private void moveStudentFromWaitingList() {
        // Implementation for use case 6
        // Allow user to move a swim student from waiting list to a class
        // Manage class allocation and waiting list
    }
}
