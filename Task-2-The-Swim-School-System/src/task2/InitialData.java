package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialData {

    // Create a new list to hold the swim students
    private static List<SwimStudent> swimStudents = new ArrayList<>();
// Method to populate student data
    // Method to populate and return student data sorted alphabetically by name

    public static List<SwimStudent> populateStudentData() {
        // Create a new instance of StudentList
        StudentList studentList = new StudentList();
// Add 10 novice level students
        studentList.addStudent(new SwimStudent("Alice Smith", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Bob Johnson", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Charlie Brown", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("David Wilson", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Emma Davis", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Frank Miller", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Grace Martinez", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Henry Taylor", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Isabella Clark", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Jack Thomas", SwimLevel.NOVICE));

// Add 10 improver level students
        studentList.addStudent(new SwimStudent("Liam Anderson", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Olivia White", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Noah Harris", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Ava Martin", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("William Thompson", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Sophia Garcia", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("James Martinez", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Charlotte Robinson", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Benjamin Lee", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Mia Scott", SwimLevel.IMPROVER));

// Add 10 advanced level students
        studentList.addStudent(new SwimStudent("Michael King", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Emily Green", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Alexander Evans", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Abigail Hughes", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Ethan Wright", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Evelyn Baker", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Daniel Hill", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Samantha Rivera", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Matthew Cooper", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Madison Long", SwimLevel.ADVANCED));

        // Get the list of students
        List<SwimStudent> students = studentList.getAllStudents();

        // Sort the list of students alphabetically by name
        Collections.sort(students, Comparator.comparing(SwimStudent::getName));

        // Display the list of students with their levels
//        System.out.println("List of Students:");
//        for (SwimStudent student : studentList.getAllStudents()) {
//            System.out.println("Name: " + student.getName() + ", Level: " + student.getLevel());
//        }
System.out.println("|----------------------------------------------|");
System.out.println("|               List of Students:              |");
System.out.println("|----------------------------------------------|");
System.out.println("|               Name             |    Level    |");
System.out.println("|----------------------------------------------|");
for (SwimStudent student : studentList.getAllStudents()) {
    System.out.printf("| %-30s | %-11s |\n", student.getName(), student.getLevel());
}
System.out.println("|----------------------------------------------| \n");


        // Assign the sorted list of students to swimStudents
        swimStudents = students;

        // Check if novice students are on the waiting list and display relevant information
        System.out.println("Novice Students on Waiting List:");
        for (SwimStudent student : studentList.getWaitingList()) {
            if (student.getLevel() == SwimLevel.NOVICE) {
                System.out.println("Name: " + student.getName());
                System.out.println("Qualifications:");
                for (Qualification qualification : student.getQualifications()) {
                    System.out.println("- " + qualification.getName() + " (Awarded by: " + qualification.getInstructor().getName() + ")");
                }
                if (!student.getDistanceSwimQualifications().isEmpty()) {
                    System.out.println("Distance Swim Qualifications:");
                    for (Qualification qualification : student.getDistanceSwimQualifications()) {
                        System.out.println("- " + qualification.getName() + " (Awarded by: " + qualification.getInstructor().getName() + ")");
                    }
                }
            }
        }
        // Return the sorted list of students
        return students;
    }
    // Getter for swimStudents

    public static List<SwimStudent> getSwimStudents() {
        return swimStudents;
    }

    public static List<SwimLesson> createSwimLessons() {
        // Create a lesson list instance
        LessonList lessonList = new LessonList();

        // Create sample instructors
        Instructor instructor1 = new Instructor("John Doe");
        Instructor instructor2 = new Instructor("Jane Smith");
        Instructor instructor3 = new Instructor("Jin Smith");

        // Retrieve the list of students
        List<SwimStudent> students = getSwimStudents();

        // Find the students by name
//        SwimStudent aliceSmith = null;
//        SwimStudent bobJohnson = null;
//        SwimStudent michaelKing = null;
//        for (SwimStudent student : students) {
//            if (student.getName().equals("Alice Smith")) {
//                aliceSmith = student;
//            } else if (student.getName().equals("Bob Johnson")) {
//                bobJohnson = student;
//            } else if (student.getName().equals("Michael King")) {
//                michaelKing = student;
//            }
//        }

List<String> targetNames = Arrays.asList(
    "Alice Smith", "Bob Johnson", "Charlie Brown", "David Wilson", "Emma Davis",
    "Frank Miller", "Grace Martinez", "Henry Taylor", "Isabella Clark", "Jack Thomas",
    "Liam Anderson", "Olivia White", "Noah Harris", "Ava Martin", "William Thompson",
    "Sophia Garcia", "James Martinez", "Charlotte Robinson", "Benjamin Lee", "Mia Scott",
    "Michael King", "Emily Green", "Alexander Evans", "Abigail Hughes", "Ethan Wright",
    "Evelyn Baker", "Daniel Hill", "Samantha Rivera", "Matthew Cooper", "Madison Long"
);

Map<String, SwimStudent> studentsByName = new HashMap<>();
for (SwimStudent student : students) {
    studentsByName.put(student.getName(), student);
}

Map<String, SwimStudent> foundStudents = new HashMap<>();
for (String name : targetNames) {
    SwimStudent student = studentsByName.get(name);
    if (student != null) {
        foundStudents.put(name, student);
    }
}

        // Create lessons
        // Novice
        SwimLesson lessonNoviceMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes

        SwimLesson lessonNoviceWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes

        SwimLesson lessonNoviceFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonNoviceFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes

        // Improver
        SwimLesson lessonImproverMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
        SwimLesson lessonImproverMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes

        SwimLesson lessonImproverWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes

        SwimLesson lessonImproverFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonImproverFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes

        //Advanced
        SwimLesson lessonAdvancedMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes

        SwimLesson lessonAdvancedWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes

        SwimLesson lessonAdvancedFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
        SwimLesson lessonAdvancedFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes

        // Add students to lessons
//        lessonNoviceMonday1.addStudents(Arrays.asList(aliceSmith));
//        lessonImproverMonday1.addStudents(Arrays.asList(bobJohnson));
//        lessonAdvancedMonday1.addStudents(Arrays.asList(michaelKing));

// Use the found students to add them to swim lessons
lessonNoviceMonday1.addStudents(new ArrayList<>(foundStudents.values()));
lessonImproverMonday1.addStudents(new ArrayList<>(foundStudents.values()));
lessonAdvancedMonday1.addStudents(new ArrayList<>(foundStudents.values()));

        // Add lessons to the lesson list
        lessonList.addLesson(lessonNoviceMonday1);
        lessonList.addLesson(lessonImproverMonday1);
        lessonList.addLesson(lessonAdvancedMonday1);

        // Return the list of swim lessons
        return lessonList.getLessons();
    }

}
