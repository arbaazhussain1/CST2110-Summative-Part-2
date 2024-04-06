package task2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    public void viewStudentInformation() {
    Scanner scanner = new Scanner(System.in);
    StudentList studentList = new StudentList();

    // Populate student data
    InitialData.populateStudentData(); // Call the method to populate student data

    // Retrieve the list of students after population
    List<SwimStudent> students = InitialData.getSwimStudents();

    // Retrieve the list of lessons
    List<SwimLesson> lessons = InitialData.createSwimLessons();

    // Sort the list of students alphabetically by name
    Collections.sort(students, Comparator.comparing(SwimStudent::getName));

    // Display the sorted list of students
    System.out.print("Enter the student's name: ");
    String inputName = scanner.nextLine().trim();

    // Find the selected student by name
    SwimStudent selectedStudent = null;
    for (SwimStudent student : students) {
        if (student.getName().equalsIgnoreCase(inputName)) {
            selectedStudent = student;
            break;
        }
    }

    // Find the selected lesson containing the student by name
    SwimLesson selectedLesson = null;
    for (SwimLesson lesson : lessons) {
        if (lesson.getStudents().contains(selectedStudent)) {
            selectedLesson = lesson;
            break;
        }
    }

    // Display student information if found, otherwise, print an error message
    if (selectedStudent != null) {
        System.out.println("----------------------------------------");
        System.out.println("Student Information:");
        System.out.println("----------------------------------------");
        System.out.println("Name: " + selectedStudent.getName());
        System.out.println("Level: " + selectedStudent.getLevel()); // Print the student's level

        // Check if the student is enrolled in any lesson
        if (selectedLesson != null) {
            // Check if the lesson has an instructor assigned
            Instructor instructor = selectedLesson.getInstructor();
            if (instructor != null) {
                System.out.println("Lesson Day: " + selectedLesson.getDayOfWeek());
                System.out.println("Start Time: " + selectedLesson.getStartTime());
                System.out.println("Duration: " + selectedLesson.getDuration() + " minutes");
                System.out.println("Instructor: " + instructor.getName());
            } else {
                System.out.println("Lesson information not available: No instructor assigned.");
            }
        } else {
            System.out.println("The student is not enrolled in any lesson.");
        }
        System.out.println("----------------------------------------");

        // Add code to display other information of the selected student
    } else {
        System.out.println("Student with the name '" + inputName + "' not found.");
    }
}



    public void viewLessonDetails() {
        // Implementation for use case 2
        // Prompt user to select the day, time, and level of the swim class
        // Display swim lesson details
    }

    public void viewInstructorSchedule() {
        // Implementation for use case 3
        // Prompt user to select a swim instructor from the list
        // Display instructor's schedule
    }

    public void addNewStudent() {
        // Implementation for use case 4
        // Allow user to add a new swim student
        // Display available classes for novice level and allocate the student
    }

    public void awardSwimQualification() {
        // Implementation for use case 5
        // Allow user to award swim qualification to a student
        // Check if qualification leads to level upgrade and manage waiting list
    }

    void moveStudentFromWaitingList() {
        // Implementation for use case 6
        // Allow user to move a swim student from waiting list to a class
        // Manage class allocation and waiting list
    }
}
