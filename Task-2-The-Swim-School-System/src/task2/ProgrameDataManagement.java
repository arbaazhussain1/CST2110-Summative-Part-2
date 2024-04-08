package task2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static task2.InitialData.*;

public class ProgrameDataManagement {

    // Console-based user interface menu
    public void Start() {
        Scanner scanner = new Scanner(System.in);
        InitialData.populateData();
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
        // Display header for swim student information
        System.out.println("|----------------------------------------|");
        System.out.println("|     Swim Student Information           |");
        System.out.println("|----------------------------------------|");
        System.out.println("Please select a swim student:");

        // Get the list of swim students sorted alphabetically by name
        List<SwimStudent> students = studentList.getAllSwimStudents();
        Collections.sort(students, Comparator.comparing(SwimStudent::getName));

        // Display the list of swim students for selection
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        // Get user input for selecting a student
        Scanner scanner = new Scanner(System.in);
        int selectedStudentIndex = -1;
        do {
            System.out.print("Enter the number of the student you want to view information for (or 0 to cancel): ");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                selectedStudentIndex = Integer.parseInt(input) - 1;
                if (selectedStudentIndex >= 0 && selectedStudentIndex < students.size()) {
                    // Display information for the selected student
                    SwimStudent selectedStudent = students.get(selectedStudentIndex);
                    System.out.println("|----------------------------------------|");
                    System.out.println("Name: " + selectedStudent.getName());
                    System.out.println("Level: " + selectedStudent.getLevel());
                    // Check if the student is assigned to a class or on waiting list
                    if (!studentList.isStudentOnWaitingList(selectedStudent)) {
                        System.out.println("Assigned Class Details : " + selectedStudent.getSwimLesson());
                        System.out.println("Instructor: " + selectedStudent.getSwimLesson().getInstructor());
                    } else {
                        System.out.println("Student is on the waiting list");
                        if (selectedStudent.getSwimLesson() != null)
                            System.out.println("Assigned Class Details : " + selectedStudent.getSwimLesson());
                    }
                    // Display qualifications if available
                    if (selectedStudent.getQualifications().size() > 0) {
                        System.out.println("Qualifications:");
                        for (Qualification qualification : selectedStudent.getQualifications()) {
                            System.out.println("- " + qualification.getName() + " (Awarded by: " + qualification.getInstructor().getName() + ")");
                        }
                    }
                    System.out.println("|----------------------------------------|");
                } else if (selectedStudentIndex == -1) {
                    System.out.println("Operation cancelled.");
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + students.size() + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (selectedStudentIndex < 0 || selectedStudentIndex >= students.size());
    }



    public void viewLessonDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("|----------------------------------------|");
        System.out.println("|       Swim Lesson Details             |");
        System.out.println("|----------------------------------------|");

        // Prompt the user to select the day of the week
        System.out.println("Select the day of the week:");
        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        for (int i = 0; i < daysOfWeek.length; i++) {
            System.out.println((i + 1) + ". " + daysOfWeek[i]);
        }
        int selectedDayIndex = -1;
        while (selectedDayIndex < 0 || selectedDayIndex >= daysOfWeek.length) {
            System.out.print("Enter the number of the day: ");
            if (scanner.hasNextInt()) {
                selectedDayIndex = scanner.nextInt() - 1;
                if (selectedDayIndex < 0 || selectedDayIndex >= daysOfWeek.length) {
                    System.out.println("Invalid day. Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        DayOfWeek selectedDay = daysOfWeek[selectedDayIndex];

        // Prompt the user to select the time of the lesson
        System.out.println("Select the time of the lesson:");
        LocalTime[] startTimes = {LocalTime.of(17, 0), LocalTime.of(17, 30), LocalTime.of(18, 0), LocalTime.of(18, 30), LocalTime.of(19, 0), LocalTime.of(19, 30)};
        for (int i = 0; i < startTimes.length; i++) {
            System.out.println((i + 1) + ". " + startTimes[i]);
        }
        int selectedTimeIndex = -1;
        while (selectedTimeIndex < 0 || selectedTimeIndex >= startTimes.length) {
            System.out.print("Enter the number of the time: ");
            if (scanner.hasNextInt()) {
                selectedTimeIndex = scanner.nextInt() - 1;
                if (selectedTimeIndex < 0 || selectedTimeIndex >= startTimes.length) {
                    System.out.println("Invalid time. Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        LocalTime selectedTime = startTimes[selectedTimeIndex];

        // Prompt the user to select the level of the lesson
        System.out.println("Select the level of the lesson:");
        SwimLevel[] swimLevels = SwimLevel.values();
        for (int i = 0; i < swimLevels.length; i++) {
            System.out.println((i + 1) + ". " + swimLevels[i]);
        }
        int selectedLevelIndex = -1;
        while (selectedLevelIndex < 0 || selectedLevelIndex >= swimLevels.length) {
            System.out.print("Enter the number of the level: ");
            if (scanner.hasNextInt()) {
                selectedLevelIndex = scanner.nextInt() - 1;
                if (selectedLevelIndex < 0 || selectedLevelIndex >= swimLevels.length) {
                    System.out.println("Invalid level. Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        SwimLevel selectedLevel = swimLevels[selectedLevelIndex];

        // Find the lesson based on the selected criteria
        SwimLesson selectedLesson = lessonList.getLesson(selectedDay, selectedTime, selectedLevel);
        if (selectedLesson != null) {
            // Display information for the selected lesson
            System.out.println("|----------------------------------------|");
            System.out.println("Day: " + selectedLesson.getDayOfWeek());
            System.out.println("Time: " + selectedLesson.getStartTime());
            System.out.println("Level: " + selectedLesson.getLevel());
            System.out.println("Instructor: " + selectedLesson.getInstructor().getName());
            System.out.println("Allocated Students: ");
            List<SwimStudent> allocatedStudents = selectedLesson.getStudents();
            if (allocatedStudents.isEmpty()) {
                System.out.println("No students allocated to this class.");
            } else {
                for (SwimStudent student : allocatedStudents) {
                    System.out.println("- " + student.getName());
                }
            }
            System.out.println("Available Spaces: " + selectedLesson.getAvailableSpaces());
            System.out.println("|----------------------------------------|");
        } else {
            System.out.println("No lesson found for the selected criteria.");
        }
    }

    public void viewInstructorSchedule() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to select a swim instructor from the list
        System.out.println("Select a swim instructor:");
        List<Instructor> sortedInstructors = instructorList.stream()
                .sorted(Comparator.comparing(Instructor::getName))
                .collect(Collectors.toList());
        for (int i = 0; i < sortedInstructors.size(); i++) {
            System.out.println((i + 1) + ". " + sortedInstructors.get(i).getName());
        }

        // Get user input for instructor selection
        int instructorIndex;
        do {
            System.out.print("Enter the number of the instructor: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            instructorIndex = scanner.nextInt();
        } while (instructorIndex < 1 || instructorIndex > sortedInstructors.size());

        // Retrieve the selected instructor
        Instructor selectedInstructor = sortedInstructors.get(instructorIndex - 1);

        // Display the instructor's allocated classes for the week
        System.out.println("Instructor Schedule for " + selectedInstructor.getName() + ":");
        for (SwimLesson lesson : selectedInstructor.getSwimLessonList()) {
            System.out.println("Day: " + lesson.getDayOfWeek() +
                    ", Time: " + lesson.getStartTime() +
                    ", Level: " + lesson.getLevel());
            if (!lesson.getStudents().isEmpty()) {
                System.out.println("Students:");
                for (SwimStudent student : lesson.getStudents()) {
                    System.out.println("- " + student.getName());
                }
            } else {
                System.out.println("No students assigned to this lesson.");
            }
            System.out.println();
        }
    }



    /**
     * 4. Add new swim student
     * The User (i.e., administrator) provides the name of a new student to the system. The system then displays a
     * weekly schedule of classes for the novice level. The display indicates if there are any spaces available in each
     * class or if it is currently full. If there are classes with availability, the administrator can select one of those
     * sessions (by selecting the day and time) and the new student is allocated to that group, which is recorded by
     * the system, and an appropriate confirmation message is displayed. If there is no space available (on the day
     * and time that the student wishes to attend), then the system will allow the User to add the new student to
     * the waiting list (which is recorded by the system and an appropriate message is displayed).
     */
    public void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the new student:");
        String studentName = scanner.nextLine().trim();

        SwimStudent newStudent = new SwimStudent(studentName, SwimLevel.NOVICE);
        studentList.addStudent(newStudent);

        System.out.println("Available classes for novice level:");
        List<SwimLesson> noviceLessons = lessonList.getLessonsByLevel(SwimLevel.NOVICE);
        for (SwimLesson lesson : noviceLessons) {
            int availableSlots = lesson.getAvailableSpaces();
            System.out.println(lesson.getDayOfWeek() + " " + lesson.getStartTime() + ": " + availableSlots + " slots available");
        }

        System.out.println("Select a class (day and time) for the new student:");
        System.out.print("Enter the day (e.g., MONDAY): ");
        DayOfWeek selectedDay = DayOfWeek.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter the time (e.g., 17:00): ");
        LocalTime selectedTime = LocalTime.parse(scanner.nextLine().trim());

        SwimLesson selectedLesson = null;
        for (SwimLesson lesson : noviceLessons) {
            if (lesson.getDayOfWeek() == selectedDay && lesson.getStartTime().equals(selectedTime)) {
                selectedLesson = lesson;
                break;
            }
        }

        if (selectedLesson != null && selectedLesson.addStudent(newStudent)) {
            newStudent.setSwimLesson(selectedLesson);
            System.out.println("Student " + studentName + " successfully added to the class.");
        } else {
            studentList.addStudentToWaitingList(newStudent);
            System.out.println("No available slots in the selected class. Student added to the waiting list.");
        }
    }


    public void awardSwimQualification() {
        Scanner scanner = new Scanner(System.in);




        // Prompt user to select student
        System.out.println("Select a student:");
        List<SwimStudent> students = studentList.getAllSwimStudents();
        Collections.sort(students, Comparator.comparing(SwimStudent::getName));
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName() + " (" + students.get(i).getLevel() + ")");
        }
        int studentIndex = scanner.nextInt() - 1;
        SwimStudent selectedStudent = students.get(studentIndex);


        // Prompt user to select instructor
        //create instructors with same swim level
        List<Instructor> instructors=new ArrayList<>();
        for (Instructor ins:instructorList)
        {
            if(ins.hasLessonWithLevel(selectedStudent.getLevel()))
                instructors.add(ins);
        }
        System.out.println("Select an instructor:");
        Collections.sort(instructors, Comparator.comparing(Instructor::getName));
        for (int i = 0; i < instructors.size(); i++) {
            System.out.println((i + 1) + ". " + instructors.get(i).getName());
        }
        int instructorIndex = scanner.nextInt() - 1;
        Instructor selectedInstructor = instructors.get(instructorIndex);



        // Award qualification
        if (selectedStudent.getLevel() == SwimLevel.ADVANCED) {
            System.out.println("Select the type of qualification:");
            System.out.println("1. Distance Swim");
            System.out.println("2. Personal Survival");
            int qualificationType = scanner.nextInt();
            if (qualificationType == 1) {
                awardDistanceSwim(selectedInstructor, selectedStudent);
            } else if (qualificationType == 2) {
                awardPersonalSurvival(selectedInstructor, selectedStudent);
            } else {
                System.out.println("Invalid option.");
            }
        } else {
            awardDistanceSwim(selectedInstructor, selectedStudent);
        }
    }

    private void awardDistanceSwim(Instructor instructor, SwimStudent student) {
        Scanner scanner = new Scanner(System.in);

        List<Qualification> availableQualifications = getAvailableDistanceSwimQualifications(student,instructor);
        if(availableQualifications.size()<=0)
        {
            System.out.println("No Qualification Available with selected Instructor");
            return;
        }
        System.out.println("Select a distance qualification:");
        for (int i = 0; i < availableQualifications.size(); i++) {

            System.out.println((i + 1) + ". " + availableQualifications.get(i));
        }
        int qualificationIndex = scanner.nextInt() - 1;
        Qualification selectedQualification = availableQualifications.get(qualificationIndex);
        student.addQualification(selectedQualification);
        System.out.println("Qualification awarded successfully.");
    }

    private void awardPersonalSurvival(Instructor instructor, SwimStudent student) {
        Scanner scanner = new Scanner(System.in);

        List<Qualification> availableQualifications = getAvailablePersonalSurvivalQualifications(student,instructor);
        if(availableQualifications.size()<=0)
        {
            System.out.println("No personal survival qualification available for the selected instructor");
        }
        System.out.println("Select a personal survival qualification:");
        for (int i = 0; i < availableQualifications.size(); i++) {
            System.out.println((i + 1) + ". " + availableQualifications.get(i));
        }
        int qualificationIndex = scanner.nextInt() - 1;
        Qualification selectedQualification = availableQualifications.get(qualificationIndex);
        student.addQualification(selectedQualification);
        System.out.println("Qualification awarded successfully.");
    }

    private List<Qualification> getAvailableDistanceSwimQualifications(SwimStudent student,Instructor instructor) {
        List<Qualification> availableQualifications = new ArrayList<>();
        for (Qualification qualification : qualificationList) {
            if (qualification instanceof PersonalSurvival && !student.hasAchieved(qualification) && qualification.getInstructor().equals(instructor) && qualification.getLevel().equals(student.getLevel())) {
                availableQualifications.add(qualification);
            }
        }
        return availableQualifications;
    }

    private List<Qualification> getAvailablePersonalSurvivalQualifications(SwimStudent student,Instructor instructor) {
        List<Qualification> availableQualifications = new ArrayList<>();
        for (Qualification qualification : qualificationList) {
            if (qualification instanceof PersonalSurvival && !student.hasAchieved(qualification) && qualification.getInstructor().equals(instructor)  && qualification.getLevel().equals(student.getLevel())) {

                availableQualifications.add(qualification);
            }
        }
        return availableQualifications;
    }
    void moveStudentFromWaitingList() {
        // Display the waiting lists of swim student names organized according to criteria
        System.out.println("|----------------------------------------|");
        System.out.println("|   Swim Student Waiting Lists          |");
        System.out.println("|----------------------------------------|");
        Map<SwimLevel, List<SwimStudent>> organizedWaitingList = organizeWaitingList();

        // Display available waiting lists
        int waitingListIndex = 1;
        for (SwimLevel level : organizedWaitingList.keySet()) {
            System.out.println(waitingListIndex + ". Waiting list for " + level + " level");
            waitingListIndex++;
        }

        // Allow the user to select a waiting list
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a waiting list by entering the index (or 0 to cancel): ");
        int selectedWaitingListIndex = scanner.nextInt();
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
        System.out.println("|   Swim Student Waiting List           |");
        System.out.println("|----------------------------------------|");
        for (int i = 0; i < selectedWaitingList.size(); i++) {
            System.out.println((i + 1) + ". " + selectedWaitingList.get(i).getName());
        }

        // Allow the user to select a swim student from the waiting list
        System.out.print("Select a swim student from the waiting list by entering the index (or 0 to cancel): ");
        int selectedStudentIndex = scanner.nextInt();
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
        List<SwimLesson> schedule = lessonList.getLessonsByLevel(selectedStudent.getLevel());
        System.out.println("|----------------------------------------|");
        System.out.println("|   Weekly Schedule of Classes           |");
        System.out.println("|----------------------------------------|");
        int sessionIndex = 1;
        for (SwimLesson lesson : schedule) {
            System.out.println(sessionIndex + ". " + lesson); // Display lesson details
            sessionIndex++;
        }

        // Allow the user to select a session if there is availability
        System.out.print("Select a session with availability by entering the index (or 0 to cancel): ");
        int selectedSessionIndex = scanner.nextInt();
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

            System.out.println("Swim student " + selectedStudent.getName() + " has been allocated to the class.");
        } else {
            System.out.println("No available spaces in the selected class.");
        }
    }

    // Helper method to organize waiting list according to criteria
    private Map<SwimLevel, List<SwimStudent>> organizeWaitingList() {
        Map<SwimLevel, List<SwimStudent>> organizedWaitingList = new HashMap<>();
        List<SwimStudent> waitingList = studentList.getWaitingList();
        for (SwimStudent student : waitingList) {
            SwimLevel level = student.getLevel();
            organizedWaitingList.computeIfAbsent(level, k -> new ArrayList<>()).add(student);
        }
        return organizedWaitingList;
    }


}
