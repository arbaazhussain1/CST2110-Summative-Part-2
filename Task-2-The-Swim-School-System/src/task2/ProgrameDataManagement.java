package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static task2.InitialData.*;

// Class definition for managing swim lessons, instructors, and students
public class ProgrameDataManagement {

    // Console-based user interface menu
    public void Start() {
        Scanner scanner = new Scanner(System.in);
        InitialData.populateData();
        int choice;
        do {
            // Display the main menu options
            System.out.println("\n|----------------------------------------|");
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

    // Method to view swim student information
    public void viewStudentInformation() {
        // Display header for swim student information
        System.out.println("|----------------------------------------|");
        System.out.println("|         Swim Student Information       |");
        System.out.println("|----------------------------------------|");
        System.out.println();

        // Display header for student table
        System.out.printf("|----------------------------------------|%n");
        System.out.printf("|    | %-10s | %-20s |%n", "Name", "Level");
        System.out.printf("|----|------------|----------------------|%n");

        // Get the list of swim students sorted alphabetically by name
        List<SwimStudent> students = studentList.getAllSwimStudents();
        Collections.sort(students, Comparator.comparing(SwimStudent::getName));

        // Display the list of swim students for selection
        for (int i = 0; i < students.size(); i++) {
            SwimLevel level = students.get(i).getLevel();
            if (level.equals(SwimLevel.NEW)) {
                level = SwimLevel.NOVICE;
            }
            System.out.printf("| %-2d | %-10s | %-20s |%n", (i + 1), students.get(i).getName(), level);
        }
        System.out.println("|----------------------------------------|");

        // Get user input for selecting a student
        Scanner scanner = new Scanner(System.in);
        int selectedStudentIndex = -1;
        do {
            System.out.print("Select a student (enter the number) or 0 to cancel: ");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                selectedStudentIndex = Integer.parseInt(input) - 1;
                if (selectedStudentIndex >= 0 && selectedStudentIndex < students.size()) {
                    // Display information for the selected student
                    SwimStudent selectedStudent = students.get(selectedStudentIndex);
                    System.out.println("Student Name: " + selectedStudent.getName());
                    System.out.println("|----------------------------------------|");
                    System.out.printf("| %-10s | %-7s | %-15s |%n", "Day", "Time", "Level");
                    System.out.println("------------------------------------------");

                    // Check if the student is assigned to a class or on waiting list
                    SwimLesson lesson = selectedStudent.getSwimLesson();
                    if (lesson == null) {

                    } else {
                        System.out.printf("| %-10s | %-7s | %-15s |%n", lesson.getDayOfWeek(), lesson.getStartTime(), lesson.getLevel());
                        System.out.println("------------------------------------------");
                        System.out.printf("| %-10s | %-25s |%n", "Instructor", lesson.getInstructor().getName());
                        System.out.println("------------------------------------------");
                        System.out.printf("| %-10s | %-25s |%n", "Students", lesson.getStudents().size());
                        System.out.println("------------------------------------------");

                    }

                    // Display qualifications if available
                    System.out.println("|----------------------------------------------------------|");
                    System.out.printf("| %-18s | %-12s | %-20s |%n", "Type", "Award", "Instructor");
                    System.out.println("|----------------------------------------------------------|");

                    if (!selectedStudent.getQualifications().isEmpty()) {
                        for (Qualification qualification : selectedStudent.getQualifications()) {
                            if (qualification instanceof DistanceSwim) {
                                DistanceSwim distanceSwim = (DistanceSwim) qualification;
                                System.out.printf("| %-18s | %-11dm | %-20s |%n", " Distance Swim ", distanceSwim.getDistance(), distanceSwim.getInstructor().getName());
                            } else if (qualification instanceof PersonalSurvival) {
                                PersonalSurvival personalSurvival = (PersonalSurvival) qualification;
                                System.out.printf("| %-18s | %-12s | %-20s |%n", " Personal Survival", personalSurvival.getSurvivalLevel(), personalSurvival.getInstructor().getName());
                            }
                        }
                    }
                    System.out.println("|----------------------------------------------------------|");

                    // Display medals if applicable
                    for (Qualification qualification : selectedStudent.getQualifications()) {
                        if (qualification instanceof DistanceSwim) {
                            DistanceSwim distanceSwim = (DistanceSwim) qualification;
                            if (distanceSwim.getDistance() >= 800) {
                                if (distanceSwim.getDistance() == 800) {
                                    System.out.println("| - Medal: Bronze                                          |");
                                } else if (distanceSwim.getDistance() == 1500) {
                                    System.out.println("| - Medal: Silver                                          |");
                                } else if (distanceSwim.getDistance() == 3000) {
                                    System.out.println("| - Medal: Gold                                            |");
                                    System.out.println("|----------------------------------------------------------|");

                                }
                            }

                        }
                    }
                    if (studentList.isStudentOnWaitingList(selectedStudent)) {
                        System.out.println("Student is on the waiting list");
                    }
                } else if (selectedStudentIndex == -1) {
                    System.out.println("Operation cancelled.");
                    return;
                } else {
                    System.out.printf("Invalid selection. Please enter a number between 1 and %d.%n", students.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (selectedStudentIndex < 0 || selectedStudentIndex >= students.size());
    }

    // Method to view swim lesson details
    public void viewLessonDetails() {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Display header for swim lesson details
        System.out.println("|----------------------------------------|");
        System.out.println("|         Swim Lesson Details            |");
        System.out.println("|----------------------------------------|");
        System.out.println();

        // Initialize variables for selected day, time, and level
        boolean validInput = false;
        DayOfWeek selectedDay = null;
        LocalTime selectedTime = null;
        SwimLevel level = null;

        // Select day of the week
        while (!validInput) {
            // Prompt the user to select the day of the week
            System.out.println("------------------------------------------");
            System.out.println("| Select the day of the week:            |");
            DayOfWeek[] daysOfWeek = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY};
            for (int i = 0; i < daysOfWeek.length; i++) {
                System.out.printf("| %d. %-35s |%n", (i + 1), daysOfWeek[i]);
            }
            System.out.println("------------------------------------------");
            System.out.print("Enter the number of the day: ");
            // Validate user input for day selection
            String input = scanner.nextLine().trim();
            while (!input.matches("\\d+")) {
                System.out.print("Enter Valid number of the day:(1-" + daysOfWeek.length + ")");
                input = scanner.nextLine().trim();
            }
            int selectedDayIndex = Integer.parseInt(input);
            if (selectedDayIndex >= 1 && selectedDayIndex <= daysOfWeek.length) {
                selectedDay = daysOfWeek[selectedDayIndex - 1];
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + daysOfWeek.length + ".");
            }
        }

        // Reset input validation flag
        validInput = false;

        // Select time of the lesson
        while (!validInput) {
            // Prompt the user to select the time of the lesson
            System.out.println("------------------------------------------");
            System.out.println("| Select the time of the lesson:         |");
            LocalTime[] startTimes = {LocalTime.of(17, 0), LocalTime.of(17, 30),
                LocalTime.of(18, 0), LocalTime.of(18, 30),
                LocalTime.of(19, 0), LocalTime.of(19, 30)};
            for (int i = 0; i < startTimes.length; i++) {
                System.out.printf("| %d. %-35s |%n", (i + 1), startTimes[i]);
            }
            System.out.println("------------------------------------------");
            System.out.print("Enter the number of the time: ");
            // Validate user input for time selection
            String input = scanner.nextLine().trim();
            while (!input.matches("\\d+")) {
                System.out.print("Please Enter a valid number of the time(1-" + startTimes.length + ")");
                input = scanner.nextLine().trim();
            }

            int selectedTimeIndex = Integer.parseInt(input);

            if (selectedTimeIndex >= 1 && selectedTimeIndex <= startTimes.length) {
                selectedTime = startTimes[selectedTimeIndex - 1];
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + startTimes.length + ".");
            }
        }

        // Reset input validation flag
        validInput = false;

        // Select level of the lesson
        while (!validInput) {
            // Prompt the user to select the level of the lesson
            System.out.println("------------------------------------------");
            System.out.println("| Select the level of the lesson:        |");
            String[] swimLevels = {"NOVICE", "IMPROVER", "ADVANCED"};
            for (int i = 0; i < swimLevels.length; i++) {
                System.out.printf("| %d. %-35s |%n", (i + 1), swimLevels[i]);
            }
            System.out.println("------------------------------------------");
            System.out.print("Enter the number of the level: ");

            String input = scanner.nextLine().trim();
            while (!input.matches("\\d+")) {
                System.out.print("Please Enter a valid number of the time(1-" + swimLevels.length + ")");
                input = scanner.nextLine().trim();
            }

            int selectedLevelIndex = Integer.parseInt(input);
            if (selectedLevelIndex >= 1 && selectedLevelIndex <= swimLevels.length) {
                String selectedLevel = swimLevels[selectedLevelIndex - 1];
                // Map user input to SwimLevel enum
                level = selectedLevel.equalsIgnoreCase("Novice") ? SwimLevel.NOVICE
                        : (selectedLevel.equalsIgnoreCase("IMPROVER") ? SwimLevel.IMPROVER : SwimLevel.ADVANCED);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + swimLevels.length + ".");
            }
        }

        // Find the lesson based on the selected criteria
        SwimLesson selectedLesson = lessonList.getLesson(selectedDay, selectedTime, level);
        if (selectedLesson != null) {
            // Display information for the selected lesson
            System.out.println("|----------------------------------------|");
            System.out.printf("| %-10s | %-7s | %-15s |%n", "Day", "Time", "Level");
            System.out.println("------------------------------------------");
            System.out.printf("| %-10s | %-7s | %-15s |%n", selectedLesson.getDayOfWeek(), selectedLesson.getStartTime(), selectedLesson.getLevel());
            System.out.println("------------------------------------------");
            System.out.printf("| %-10s | %-25s |%n", "Instructor", selectedLesson.getInstructor().getName());
            System.out.println("------------------------------------------");
            System.out.printf("| %-10s | %-25s |%n", "Students", selectedLesson.getStudents().size());
            System.out.println("------------------------------------------");
            List<SwimStudent> allocatedStudents = selectedLesson.getStudents();
            if (allocatedStudents.isEmpty()) {
                System.out.println("No students allocated to this class.");
            } else {
                System.out.println("|----------------------------------------|");
                System.out.println("| Students:");
                System.out.println("|----------------------------------------|");

                // Display allocated students for the lesson
                for (SwimStudent student : allocatedStudents) {
                    System.out.println("| " + student.getName());
                }
            }
            System.out.printf("| %-17s | %-25s %n", "Available Spaces", selectedLesson.getAvailableSpaces());
            System.out.println("|----------------------------------------|");
        } else {
            // Inform user if no lesson found for the selected criteria
            System.out.println("No lesson found for the selected criteria.");
        }
    }

    public void viewInstructorSchedule() {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to select a swim instructor from the list
        System.out.println("|---------------------------------------|");
        System.out.println("|          Instructor Schedule          |");
        System.out.println("|---------------------------------------|");
        System.out.println("|    | Name                             |");

        // Sort the list of instructors by name
        List<Instructor> sortedInstructors = instructorList.stream()
                .sorted(Comparator.comparing(Instructor::getName))
                .collect(Collectors.toList());

        // Display the list of instructors for selection
        for (int i = 0; i < sortedInstructors.size(); i++) {
            System.out.printf("| %2d | %-32s |%n", (i + 1), sortedInstructors.get(i).getName());
        }
        System.out.println("------------------------------------------");
        // Prompt the user to select an instructor
        int instructorIndex;
        do {
            System.out.print("Enter the number of the instructor: ");
            // Validate user input for instructor selection
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            instructorIndex = scanner.nextInt();
            if (instructorIndex < 1 || instructorIndex > sortedInstructors.size()) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        } while (instructorIndex < 1 || instructorIndex > sortedInstructors.size());

        // Retrieve the selected instructor
        Instructor selectedInstructor = sortedInstructors.get(instructorIndex - 1);

        // Display the instructor's allocated classes for the week
        System.out.println("|----------------------------------------|");
        System.out.printf("|   Instructor Schedule for %-12s   %n", selectedInstructor.getName());

        // Iterate through the instructor's swim lesson list
        for (SwimLesson lesson : selectedInstructor.getSwimLessonList()) {
            System.out.println("|----------------------------------------|");
            System.out.printf("| %-10s | %-7s | %-15s |%n", "Day", "Time", "Level");
            System.out.println("------------------------------------------");
            // Display details of each lesson including day, time, and level
            System.out.printf("| %-10s | %-7s | %-15s |%n", lesson.getDayOfWeek(), lesson.getStartTime(), lesson.getLevel());
            System.out.println("------------------------------------------");
            if (!lesson.getStudents().isEmpty()) {
                System.out.println("|   Students:");
                // Display the students assigned to the lesson
                for (SwimStudent student : lesson.getStudents()) {
                    System.out.println("| - " + student.getName());
                }
                // Check if there are empty spots in the class
                if (lesson.getStudents().size() != 4) {
                    int required = 4 - lesson.getStudents().size();
                    System.out.printf("| %d Students are required to run this class%n", required);
                }
            } else {
                System.out.println("| No students assigned to this lesson.");
            }
            System.out.println("|----------------------------------------|");
        }
    }

    public void addNewStudent() {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("|----------------------------------------|");
        System.out.println("|   Add New Student                      |");
        System.out.println("|----------------------------------------|");

        // Prompt user to enter the name of the new student
        System.out.print("| Enter new student Name: ");
        String studentName = scanner.nextLine().trim();

        // Create a new student with novice level
        SwimStudent newStudent = new SwimStudent(studentName, SwimLevel.NOVICE);
        // Add the new student to the list of students
        studentList.addStudent(newStudent);

        // Display available classes for novice level
        System.out.println("|----------------------------------------|");
        System.out.println("| Available Classes for Novice Level     |");
        System.out.println("|----------------------------------------|");
        System.out.printf("| %-9s | %-8s | %-15s |%n", "Day", "Time", "Slots available");
        // Get lessons available for novice level
        List<SwimLesson> noviceLessons = lessonList.getLessonsByLevel(SwimLevel.NOVICE);
        for (SwimLesson lesson : noviceLessons) {
            int availableSlots = lesson.getAvailableSpaces();
            System.out.printf("| %-9s | %-8s | %-15d |%n", lesson.getDayOfWeek(), lesson.getStartTime(), availableSlots);
        }
        System.out.println("|----------------------------------------|");

        // Prompt user to select a class (day and time) for the new student
        System.out.println("|----------------------------------------|");
        System.out.println("| Select a class (day and time)          |");
        System.out.println("------------------------------------------");
        DayOfWeek selectedDay = null;
        LocalTime selectedTime = null;
        try {
            // Get user input for day
            System.out.print("| Enter the day (e.g., MONDAY): ");
            String input = scanner.nextLine().trim();
            while (!input.matches("Monday|Wednesday|Friday|monday|wednesday|friday|MONDAY|WEDNESDAY|FRIDAY")) {
                System.out.print("| Enter the valid day (e.g.,Monday|Wednesday|Friday): ");
                input = scanner.nextLine().trim();
            }

            selectedDay = DayOfWeek.valueOf(input.toUpperCase());
            // Get user input for time
            System.out.print("| Enter the time (e.g., 17:00): ");
            input = scanner.nextLine().trim();
            while (!input.matches("\\d{2}:\\d{2}")) {
                System.out.print("| Enter valid time (e.g., 17:00): ");
                input = scanner.nextLine().trim();
            }
            selectedTime = LocalTime.parse(input);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }

        // Find the selected lesson
        SwimLesson selectedLesson = null;
        for (SwimLesson lesson : noviceLessons) {
            if (lesson.getDayOfWeek() == selectedDay && lesson.getStartTime().equals(selectedTime)) {
                selectedLesson = lesson;
                break;
            }
        }

        // Add the new student to the selected lesson if there are available slots
        if (selectedLesson != null && selectedLesson.addStudent(newStudent)) {
            newStudent.setSwimLesson(selectedLesson);
            System.out.println("|----------------------------------------|");
            System.out.println("| " + studentName + " successfully added to the class.");
            System.out.println("|----------------------------------------|");
        } else {
            // Add the student to the waiting list if no available slots in the selected class
            newStudent.setLevel(SwimLevel.NEW);
            newStudent.setMaximumAward(0);
            studentList.addStudentToWaitingList(newStudent);
            System.out.println("|----------------------------------------|");
            System.out.println("| No available slots in the selected class. Student added to the waiting list.");
            System.out.println("|----------------------------------------|");
        }
    }

    public void awardSwimQualification() {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to select student
        System.out.println("|----------------------------------------|");
        System.out.println("|           Award Swim Qualification      |");
        System.out.println("|----------------------------------------|");
        System.out.println("Select a student:");
        // Display list of students
        List<SwimStudent> students = studentList.getAllSwimStudents();
        Collections.sort(students, Comparator.comparing(SwimStudent::getName));
        System.out.printf("|    | %-10s | %-20s |%n", "Name", "Level");
        for (int i = 0; i < students.size(); i++) {
            SwimLevel level = students.get(i).getLevel();
            if (level.equals(SwimLevel.NEW)) {
                level = SwimLevel.NOVICE;
            }
            System.out.printf("| %2d | %-10s | %-20s |%n", (i + 1), students.get(i).getName(), level);
        }
        System.out.println("|----------------------------------------|");
        // Select a student
        String input = null;
        int studentIndex = -1;
        boolean validInput = false;
        do {
            System.out.println("Select a Valid student number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                studentIndex = Integer.parseInt(input) - 1;

                if (studentIndex < 0 || studentIndex >= students.size()) {
                    validInput = false;
                }
            }

        } while (!validInput);

        SwimStudent selectedStudent = students.get(studentIndex);

        // Prompt user to select instructor
        List<Instructor> instructors = new ArrayList<>();
        for (Instructor ins : instructorList) {
            if (ins.hasLessonWithLevel(selectedStudent.getLevel())) {
                instructors.add(ins);
            }
        }
        System.out.println("Select an instructor:");
        Collections.sort(instructors, Comparator.comparing(Instructor::getName));
        System.out.println("|---------------------------------------|");
        System.out.println("|       Select instructors             |");
        System.out.println("| --------------------------------------|");
        for (int i = 0; i < instructors.size(); i++) {
            System.out.println("| " + (i + 1) + ". " + instructors.get(i).getName());
        }
        System.out.println(" ---------------------------------------|");

        // Select an instructor
        validInput = false;
        int instructorIndex = -1;
        do {
            System.out.println("Select a Valid instructor number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                instructorIndex = Integer.parseInt(input) - 1;

                if (instructorIndex < 0 || instructorIndex >= instructors.size()) {
                    validInput = false;
                }
            }

        } while (!validInput);

        Instructor selectedInstructor = instructors.get(instructorIndex);
        // Check if the student is assigned to a class
        if (selectedStudent.getLevel().equals(SwimLevel.NEW)) {
            System.out.println("Student is not added to a class yet");
            return;
        }

        // Award qualification based on student's level
        if (selectedStudent.getLevel() == SwimLevel.NOVICE) {
            // Award distance swim qualifications
            if (selectedStudent.getMaximumAward() == 0) {
                System.out.println("Novice students must first complete the 5m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 5);
            } else if (selectedStudent.getMaximumAward() == 5) {
                System.out.println("Novice students must first complete the 10m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 10);
            } else if (selectedStudent.getMaximumAward() == 10) {
                System.out.println("Novice students must first complete the 20m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 20);
            } else if (selectedStudent.getMaximumAward() == 20) {
                System.out.println("Student is in waiting list, and eligible to transfer to the next level. ");
            }

        } else if (selectedStudent.getLevel() == SwimLevel.IMPROVER) {
            // Award distance swim qualifications
            if (selectedStudent.getMaximumAward() == 20) {
                System.out.println("IMPROVER students must first complete the 100m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 100);
            } else if (selectedStudent.getMaximumAward() == 100) {
                System.out.println("IMPROVER students must first complete the 200m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 200);
            }
            if (selectedStudent.getMaximumAward() == 200) {
                System.out.println("IMPROVER students must first complete the 400m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 400);
            } else if (selectedStudent.getMaximumAward() == 400) {
                System.out.println("Student is in waiting list, and eligible to transfer to the next level. ");
            }

        } else if (selectedStudent.getLevel() == SwimLevel.ADVANCED) {
            // Award distance swim qualifications
            if (selectedStudent.getMaximumAward() == 400) {
                System.out.println("ADVANCED students must first complete the 800m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 800);
            } else if (selectedStudent.getMaximumAward() == 800) {
                System.out.println("ADVANCED students must first complete the 150m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 1500);
            }
            if (selectedStudent.getMaximumAward() == 1500) {
                System.out.println("ADVANCED students must first complete the 3000m distance swim qualification.");
                awardDistanceSwim(selectedInstructor, selectedStudent, 3000);
            } else if (selectedStudent.getMaximumAward() == 3000) {
                System.out.println("Student has completed All personal survival medals ");
            }

            // Award personal survival qualifications
            awardPersonalSurvival(selectedInstructor, selectedStudent);
        } else {
            System.out.println("Invalid student level.");
        }
    }

    private void awardDistanceSwim(Instructor instructor, SwimStudent student, int distance) {
        Scanner scanner = new Scanner(System.in);

        // Get available distance swim qualifications for the student with the given instructor and distance
        List<Qualification> availableQualifications = getAvailableDistanceSwimQualifications(student, instructor, distance);
        // If no qualifications are available, print a message and return
        if (availableQualifications.size() <= 0) {
            System.out.println("No Qualification Available with selected Instructor");
            return;
        }

        // Display available distance swim qualifications for selection
        System.out.println("Select a distance qualification:");
        for (int i = 0; i < availableQualifications.size(); i++) {
            System.out.println((i + 1) + ". " + availableQualifications.get(i));
        }

        String input = null;
        int qualificationIndex = -1;
        boolean validInput = false;
        do {
            System.out.println("Select a Valid  number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                qualificationIndex = Integer.parseInt(input) - 1;

                if (qualificationIndex < 0 || qualificationIndex >= availableQualifications.size()) {
                    validInput = false;
                }
            }

        } while (!validInput);

        // Get the selected qualification
        Qualification selectedQualification = availableQualifications.get(qualificationIndex);
        // Add the selected qualification to the student's qualifications list
        student.addQualification(selectedQualification);
        // Update the student's maximum award based on the selected qualification
        if (selectedQualification instanceof DistanceSwim) {
            DistanceSwim ds = (DistanceSwim) selectedQualification;
            student.setMaximumAward(ds.getDistance());
        } else {
            PersonalSurvival ps = (PersonalSurvival) selectedQualification;
            // Update the maximum award based on the survival level
            if (ps.getSurvivalLevel().equalsIgnoreCase("Bronze")) {
                student.setMaximumAward(800);
            } else if (ps.getSurvivalLevel().equalsIgnoreCase("Silver")) {
                student.setMaximumAward(1500);
            } else if (ps.getSurvivalLevel().equalsIgnoreCase("Gold")) {
                student.setMaximumAward(3000);
            }

        }
        // Print a success message
        System.out.println("Qualification awarded successfully.");
        // Move student to the waiting list
        moveToWaitingList();
    }

    private List<Qualification> getAvailableDistanceSwimQualifications(SwimStudent student, Instructor instructor, int distance) {
        // Initialize a list to store available qualifications
        List<Qualification> availableQualifications = new ArrayList<>();
        // Retrieve the student's level
        SwimLevel studentLevel = student.getLevel();

        // Check the student's level and distance to determine available qualifications
        if (studentLevel == SwimLevel.NOVICE && distance <= 20) {
            // Novice students can only attempt the 20m distance swim qualification
            DistanceSwim distanceSwim = new DistanceSwim(SwimLevel.NOVICE, instructor, distance);
            // Check if the student already has this qualification
            if (student.isAlreadyHasQualification(distanceSwim)) {
                System.out.println("Student Already Awarded");
                return availableQualifications;
            } else {
                availableQualifications.add(distanceSwim);
            }
        } else if (studentLevel == SwimLevel.IMPROVER && (distance <= 400)) {
            // Improver students can attempt distance swim qualifications up to 400m
            DistanceSwim distanceSwim = new DistanceSwim(SwimLevel.IMPROVER, instructor, distance);
            // Check if the student already has this qualification
            if (student.isAlreadyHasQualification(distanceSwim)) {
                System.out.println("Student Already Awarded");
                return availableQualifications;
            } else {
                availableQualifications.add(distanceSwim);
            }

        } else if (studentLevel == SwimLevel.ADVANCED && (distance <= 3000)) {
            // Advanced students can attempt 800m, 1500m, and 3000m distance swim qualifications

            PersonalSurvival personalSurvival = new PersonalSurvival(SwimLevel.ADVANCED, instructor, "Bronze");

            // Check if the student already has qualifications for certain distances
            if (student.isAlreadyHasQualification(personalSurvival) && distance == 800) {
                System.out.println("Student Already Awarded");
                return availableQualifications;
            }
            // Set the survival level to Silver and check qualification
            personalSurvival.setSurvivalLevel("Sliver");
            if (student.isAlreadyHasQualification(personalSurvival) && distance == 1500) {
                System.out.println("Student Already Awarded");
                return availableQualifications;
            }
            // Set the survival level to Gold and check qualification
            personalSurvival.setSurvivalLevel("Gold");
            if (student.isAlreadyHasQualification(personalSurvival) && distance == 3000) {
                System.out.println("Student Already Awarded");
                return availableQualifications;
            }

            availableQualifications.add(personalSurvival);

        }
        // Return the list of available qualifications
        return availableQualifications;
    }

    private void awardPersonalSurvival(Instructor instructor, SwimStudent student) {
        // Initialize a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Get available personal survival qualifications for the student and instructor
        List<Qualification> availableQualifications = getAvailablePersonalSurvivalQualifications(student, instructor);
        // Check if there are available qualifications
        if (availableQualifications.size() <= 0) {
            System.out.println("No personal survival qualification available for the selected instructor");
            return;
        }
        // Display available personal survival qualifications to the user
        System.out.println("Select a personal survival qualification:");
        for (int i = 0; i < availableQualifications.size(); i++) {
            System.out.println((i + 1) + ". " + availableQualifications.get(i));
        }
        // Prompt the user to select a personal survival qualification
        int qualificationIndex = scanner.nextInt() - 1;
        // Get the selected qualification from the available list
        Qualification selectedQualification = availableQualifications.get(qualificationIndex);
        // Add the selected qualification to the student's qualifications
        student.addQualification(selectedQualification);
        // Notify the user that the qualification has been awarded successfully
        System.out.println("Qualification awarded successfully.");
    }

    private List<Qualification> getAvailablePersonalSurvivalQualifications(SwimStudent student, Instructor instructor) {
        // Initialize an empty list to store available qualifications
        List<Qualification> availableQualifications = new ArrayList<>();
        // Iterate through each qualification in the qualification list
        for (Qualification qualification : qualificationList) {
            // Check if the qualification is a personal survival qualification,
            // if the student has not already achieved it,
            // if the qualification's instructor matches the selected instructor,
            // and if the qualification's level matches the student's level
            if (qualification instanceof PersonalSurvival && !student.hasAchieved(qualification) && qualification.getInstructor().equals(instructor) && qualification.getLevel().equals(student.getLevel())) {

                // Add the qualification to the list of available qualifications
                availableQualifications.add(qualification);
            }
        }
        // Return the list of available qualifications
        return availableQualifications;
    }

    void moveStudentFromWaitingList() {
        // Display the waiting lists of swim student names organized according to criteria
        System.out.println("|----------------------------------------|");
        System.out.println("|       Swim Student Waiting Lists       |");
        System.out.println("|----------------------------------------|");
        Map<SwimLevel, List<SwimStudent>> organizedWaitingList = organizeWaitingList();

        // Display available waiting lists
        int waitingListIndex = 1;
        for (SwimLevel level : organizedWaitingList.keySet()) {
            System.out.printf("| %d. Waiting list for %-10s level   |\n", waitingListIndex, level);
            waitingListIndex++;
        }

        // Allow the user to select a waiting list
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a waiting list by entering the index (or 0 to cancel): ");

        String input = null;
        int selectedWaitingListIndex = -1;
        boolean validInput = false;
        do {
            System.out.println("Select a Valid  number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                selectedWaitingListIndex = Integer.parseInt(input);

                if (selectedWaitingListIndex < 0 || selectedWaitingListIndex >= 4) {
                    validInput = false;
                }
            }

        } while (!validInput);

        if (selectedWaitingListIndex == 0) {
            System.out.println("Operation cancelled.");
            return;
        } else if (selectedWaitingListIndex < 1 || selectedWaitingListIndex > organizedWaitingList.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        // Find the selected waiting list
        SwimLevel selectedLevel = null;
        int currentIndex = 1;
        for (SwimLevel level : organizedWaitingList.keySet()) {
            if (currentIndex == selectedWaitingListIndex) {
                selectedLevel = level;
                break;
            }
            currentIndex++;
        }

        // Display selected waiting list with swim student indices
        List<SwimStudent> selectedWaitingList = organizedWaitingList.get(selectedLevel);
        System.out.println("|----------------------------------------|");
        System.out.println("|     Swim Student Waiting List          |");
        System.out.println("|----------------------------------------|");
        for (int i = 0; i < selectedWaitingList.size(); i++) {
            System.out.printf("| %d. %-35s |\n", (i + 1), selectedWaitingList.get(i).getName());
        }

        // Allow the user to select a swim student from the waiting list
        System.out.print("Select a swim student  by entering the index (or 0 to cancel): ");

        int selectedStudentIndex = -1;
        validInput = false;
        do {
            System.out.println("Select a Valid student number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                selectedStudentIndex = Integer.parseInt(input);

                if (selectedStudentIndex < 0 || selectedStudentIndex > selectedWaitingList.size()) {
                    validInput = false;
                }
            }

        } while (!validInput);

        if (selectedStudentIndex == 0) {
            System.out.println("Operation cancelled.");
            return;
        } else if (selectedStudentIndex < 1 || selectedStudentIndex > selectedWaitingList.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        // Find the selected swim student
        SwimStudent selectedStudent = selectedWaitingList.get(selectedStudentIndex - 1);

        // Display weekly schedule of classes for the required level
        SwimLevel level = selectedStudent.getLevel().equals(SwimLevel.NEW) ? SwimLevel.NOVICE
                : (selectedStudent.getLevel().equals(SwimLevel.NOVICE) ? SwimLevel.IMPROVER : SwimLevel.ADVANCED);
        List<SwimLesson> schedule = lessonList.getLessonsByLevel(level);
        System.out.println("|----------------------------------------|");
        System.out.println("|    Weekly Schedule of Classes          |");
        System.out.println("|----------------------------------------|");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("|     %10s | %9s | %7s | %11s | %9s | %9s |\n", "Day", "Time", "Level", "Instructor", "Students", "Duration");
        System.out.println("---------------------------------------------------------------------------");
        int sessionIndex = 1;
        for (SwimLesson lesson : schedule) {
            System.out.printf("|%4d. %-10s | %-9s | %-7s | %-11s | %-9d | %-9d  |\n", sessionIndex, lesson.getDayOfWeek(), lesson.getStartTime(),
                    lesson.getLevel(), lesson.getInstructor().getName(), lesson.getStudents().size(), lesson.getDuration());
            sessionIndex++;
        }
        System.out.println("----------------------------------------------------------------------------");

        // Allow the user to select a session if there is availability
        System.out.print("Select a session with availability by entering the index (or 0 to cancel): ");

        int selectedSessionIndex = -1;
        validInput = false;
        do {
            System.out.println("Select a Valid Session number:");
            input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                validInput = true;

                selectedSessionIndex = Integer.parseInt(input);

                if (selectedSessionIndex < 0 || selectedSessionIndex > schedule.size()) {
                    validInput = false;
                }
            }

        } while (!validInput);

        if (selectedSessionIndex == 0) {
            System.out.println("Operation cancelled.");
            return;
        } else if (selectedSessionIndex < 1 || selectedSessionIndex > schedule.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        // Find the selected session
        SwimLesson selectedLesson = schedule.get(selectedSessionIndex - 1);

        // Allocate the waiting swim student to the selected group
        if (selectedLesson.getAvailableSpaces() > 0) {
            selectedLesson.addStudent(selectedStudent);
            selectedStudent.setSwimLesson(selectedLesson);

            // Remove the swim student from the waiting list
            studentList.removeStudentToWaitingList(selectedStudent);

            // Upgrade the level of student
            SwimLevel currentLevel = selectedStudent.getLevel();
            if (currentLevel.equals(SwimLevel.NEW)) {
                selectedStudent.setLevel(SwimLevel.NOVICE);
            }
            if (currentLevel.equals(SwimLevel.NOVICE) && selectedStudent.getMaximumAward() == 0) {
                selectedStudent.setLevel(SwimLevel.NOVICE);
            } else if (currentLevel.equals(SwimLevel.NOVICE)) {
                selectedStudent.setLevel(SwimLevel.IMPROVER);
            } else if (currentLevel.equals(SwimLevel.IMPROVER)) {
                selectedStudent.setLevel(SwimLevel.ADVANCED);
            }

            System.out.println("Swim student " + selectedStudent.getName() + " has been allocated to the class.");
        } else {
            System.out.println("No available spaces in the selected class.");
        }
    }

    // Helper method to organize waiting list according to criteria
    private Map<SwimLevel, List<SwimStudent>> organizeWaitingList() {
        // Create a map to store organized waiting lists
        Map<SwimLevel, List<SwimStudent>> organizedWaitingList = new HashMap<>();
        // Retrieve the waiting list of swim students
        List<SwimStudent> waitingList = studentList.getWaitingList();
        // Iterate through each swim student in the waiting list
        for (SwimStudent student : waitingList) {
            // Determine the level of the swim student
            SwimLevel level = SwimLevel.NEW;
            if (student.getLevel().equals(SwimLevel.NEW)) {
                level = SwimLevel.NOVICE;
            } else if (student.getLevel().equals(SwimLevel.NOVICE)) {
                level = SwimLevel.IMPROVER;
            } else if (student.getLevel().equals(SwimLevel.IMPROVER)) {
                level = SwimLevel.ADVANCED;
            } else if (student.getLevel().equals(SwimLevel.ADVANCED)) {
                level = SwimLevel.ADVANCED;
            }

            // Add the swim student to the appropriate waiting list based on their level
            organizedWaitingList.computeIfAbsent(level, k -> new ArrayList<>()).add(student);
        }
        // Return the map containing the organized waiting lists
        return organizedWaitingList;
    }

}
