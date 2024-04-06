package task2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProgrameDataManagement {

    // Console-based user interface menu
    public void Start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("|----------------------------------------|");
            System.out.println("|     Swim School Management System      |");
            System.out.println("|----------------------------------------|");
            System.out.println("|1. View swim student information        |");
            System.out.println("|2. View swim lesson details             |");
            System.out.println("|3. View instructor schedule             |");
            System.out.println("|4. Add new swim student                 |");
            System.out.println("|5. Award swim qualification .           |");
            System.out.println("|6. Move swim student from waiting list  |");
            System.out.println("|7. Exit                                 |");
            System.out.println("|----------------------------------------|\n");
            System.out.print("Enter your choice: \n");
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
                        System.out.println("Invalid choice. Please enter a number between 1 and 7. \n");
                }
            } else {
                System.out.println("Invalid input. Please enter a number. \n");
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
        System.out.print("Enter the student's name: \n ");
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
            System.out.println("|------------------------------------------------------|");
            System.out.println("| Student Information:                                 |");
            System.out.println("|------------------------------------------------------|");
            System.out.printf("| Name: %-46s |\n", selectedStudent.getName());
            System.out.println("|------------------------------------------------------|");
            System.out.printf("| Level: %-45s |\n", selectedStudent.getLevel());
            System.out.println("|------------------------------------------------------|");

            // Check if the student is enrolled in any lesson
            if (selectedLesson != null) {
                // Check if the lesson has an instructor assigned
                Instructor instructor = selectedLesson.getInstructor();
                if (instructor != null) {
                    System.out.printf("| Lesson Day: %-40s |\n", selectedLesson.getDayOfWeek());
                    System.out.println("|------------------------------------------------------|");
                    System.out.printf("| Start Time: %-40s |\n", selectedLesson.getStartTime());
                    System.out.println("|------------------------------------------------------|");
                    System.out.printf("| Duration: %-42s |\n", selectedLesson.getDuration() + " minutes");
                    System.out.println("|------------------------------------------------------|");
                    System.out.printf("| Instructor: %-40s |\n", instructor.getName());
                    System.out.println("|------------------------------------------------------|");
                } else {
                    System.out.println("| Lesson information not available: No instructor assigned. |");
                    System.out.println("|------------------------------------------------------|");
                }
            } else {
                System.out.println("| The student is not enrolled in any lesson.          |");
                System.out.println("|------------------------------------------------------|");
            }

            // Add code to display other information of the selected student
        } else {
            System.out.println("Student with the name '" + inputName + "' not found. \n");
          System.out.println("|------------------------------------------------------|");

        }
    }

    public void viewLessonDetails() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to select the day, time, and level of the swim class
        System.out.println("Enter the day of the week (e.g., Monday, Tuesday, etc.):");
        String dayOfWeek = scanner.nextLine().trim();

        System.out.println("Enter the time of the lesson (e.g., 09:00, 14:30, etc.):");
        String time = scanner.nextLine().trim();

        System.out.println("Enter the level of the swim class (e.g., NOVICE, IMPROVER, ADVANCED):");
        String level = scanner.nextLine().trim().toUpperCase(); // Convert to uppercase for enum matching

        // Get the list of all swim lessons
        List<SwimLesson> allLessons = InitialData.createSwimLessons();

        // Filter lessons based on user input
        List<SwimLesson> matchingLessons = allLessons.stream()
                .filter(lesson -> lesson.getDayOfWeek().equalsIgnoreCase(dayOfWeek)
                && lesson.getStartTime().equals(time)
                && lesson.getLevel().equals(SwimLevel.valueOf(level)))
                .collect(Collectors.toList());

        // Display swim lesson details
        if (!matchingLessons.isEmpty()) {
            System.out.println("Swim Lesson Details:");
            for (SwimLesson lesson : matchingLessons) {
                System.out.println("----------------------------------------");
                System.out.println("Day of the Week: " + lesson.getDayOfWeek());
                System.out.println("Start Time: " + lesson.getStartTime());
                System.out.println("Duration: " + lesson.getDuration() + " minutes");
                System.out.println("Level: " + lesson.getLevel());
                Instructor instructor = lesson.getInstructor();
                if (instructor != null) {
                    System.out.println("Instructor: " + instructor.getName());
                } else {
                    System.out.println("Instructor: Not assigned yet");
                }
//                System.out.println("Capacity: " + lesson.getCapacity());
                System.out.println("Enrolled Students: " + lesson.getStudents().size());
            }
        } else {
            System.out.println("No swim lessons found matching the provided criteria.");
        }
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
