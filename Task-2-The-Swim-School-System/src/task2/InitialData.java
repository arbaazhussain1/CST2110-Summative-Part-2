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
        
        // Populate student data if not already done
        if (swimStudents.isEmpty()) {
            populateStudentData();
        }

        // Create a lesson list instance
        LessonList lessonList = new LessonList();

        // Define lesson times and days
        String[] days = {"Monday", "Wednesday", "Friday"};
        String[] times = {"17:00", "17:30", "18:00", "18:30", "19:00", "19:30"};

        // Create sample instructors
        Instructor instructor1 = new Instructor("John Doe");
        Instructor instructor2 = new Instructor(" Kai Smith");
        Instructor instructor3 = new Instructor(" cole wick");

        // Map students by name for easy retrieval
        Map<String, SwimStudent> studentsByName = new HashMap<>();
        for (SwimStudent student : swimStudents) {
            studentsByName.put(student.getName(), student);
        }

        // Create lessons for each day and time slot
        for (String day : days) {
            for (String time : times) {
                // Define lesson level based on the day
                SwimLevel level;
                if (day.equals("Monday") || day.equals("Wednesday") || day.equals("Friday") ) {
                    level = SwimLevel.IMPROVER;
                } else {
                    level = SwimLevel.ADVANCED;
                }

                // Create lesson with appropriate instructor and duration
                SwimLesson lesson = new SwimLesson(day, time, level, instructor1, 30); // Duration: 30 minutes

                // Add students to the lesson based on their level
         
                List<SwimStudent> studentsToAdd = new ArrayList<>();
                for (SwimStudent student : swimStudents) {
                    if (student.getLevel() == level) {
                        studentsToAdd.add(student);
                    }
                }
                lesson.addStudents(studentsToAdd);

                // Add lesson to the lesson list
                lessonList.addLesson(lesson);
            }
        }

        // Return the list of swim lessons
        return lessonList.getLessons();
    }
    
//        // Create a lesson list instance
//        LessonList lessonList = new LessonList();
//
//        // Create sample instructors
//        Instructor instructor1 = new Instructor("John Doe");
//        Instructor instructor2 = new Instructor("Jane Smith");
//        Instructor instructor3 = new Instructor("Jin Smith");
//
//        // Retrieve the list of students
//        List<SwimStudent> students = getSwimStudents();
//
//        // Find the students by name
////        SwimStudent aliceSmith = null;
////        SwimStudent bobJohnson = null;
////        SwimStudent michaelKing = null;
////        for (SwimStudent student : students) {
////            if (student.getName().equals("Alice Smith")) {
////                aliceSmith = student;
////            } else if (student.getName().equals("Bob Johnson")) {
////                bobJohnson = student;
////            } else if (student.getName().equals("Michael King")) {
////                michaelKing = student;
////            }
////        }
//        List<String> targetNames = Arrays.asList(
//                "Alice Smith", "Bob Johnson", "Charlie Brown", "David Wilson", "Emma Davis",
//                "Frank Miller", "Grace Martinez", "Henry Taylor", "Isabella Clark", "Jack Thomas",
//                "Liam Anderson", "Olivia White", "Noah Harris", "Ava Martin", "William Thompson",
//                "Sophia Garcia", "James Martinez", "Charlotte Robinson", "Benjamin Lee", "Mia Scott",
//                "Michael King", "Emily Green", "Alexander Evans", "Abigail Hughes", "Ethan Wright",
//                "Evelyn Baker", "Daniel Hill", "Samantha Rivera", "Matthew Cooper", "Madison Long"
//        );
//
//        Map<String, SwimStudent> studentsByName = new HashMap<>();
//        for (SwimStudent student : students) {
//            studentsByName.put(student.getName(), student);
//        }
//
//        Map<String, SwimStudent> foundStudents = new HashMap<>();
//        for (String name : targetNames) {
//            SwimStudent student = studentsByName.get(name);
//            if (student != null) {
//                foundStudents.put(name, student);
//            }
//        }
//
//        // Create lessons
//        // Novice
//        SwimLesson lessonNoviceMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
////        SwimLesson lessonNoviceMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.NOVICE, instructor1, 30); // Duration: 30 minutes
//
//        SwimLesson lessonNoviceWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
////        SwimLesson lessonNoviceWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.NOVICE, instructor2, 30); // Duration: 30 minutes
//
//        SwimLesson lessonNoviceFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonNoviceFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
////        SwimLesson lessonNoviceFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.NOVICE, instructor3, 30); // Duration: 30 minutes
//
//        // Improver
//        SwimLesson lessonImproverMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
////        SwimLesson lessonImproverMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.IMPROVER, instructor2, 30); // Duration: 30 minutes
//
//        SwimLesson lessonImproverWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
////        SwimLesson lessonImproverWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.IMPROVER, instructor1, 30); // Duration: 30 minutes
//
//        SwimLesson lessonImproverFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//        SwimLesson lessonImproverFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
////        SwimLesson lessonImproverFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.IMPROVER, instructor3, 30); // Duration: 30 minutes
//
//        //Advanced
//        SwimLesson lessonAdvancedMonday1 = new SwimLesson("Monday", "17:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedMonday2 = new SwimLesson("Monday", "17:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedMonday3 = new SwimLesson("Monday", "18:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedMonday4 = new SwimLesson("Monday", "18:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedMonday5 = new SwimLesson("Monday", "19:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedMonday6 = new SwimLesson("Monday", "19:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
////        SwimLesson lessonAdvancedMonday7 = new SwimLesson("Monday", "20:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//
//        SwimLesson lessonAdvancedWednesday1 = new SwimLesson("Wednesday", "17:00", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedWednesday2 = new SwimLesson("Wednesday", "17:30", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedWednesday3 = new SwimLesson("Wednesday", "18:00", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedWednesday4 = new SwimLesson("Wednesday", "18:30", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedWednesday5 = new SwimLesson("Wednesday", "19:00", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedWednesday6 = new SwimLesson("Wednesday", "19:30", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
////        SwimLesson lessonAdvancedWednesday7 = new SwimLesson("Wednesday", "20:00", SwimLevel.ADVANCED, instructor2, 30); // Duration: 30 minutes
//
//        SwimLesson lessonAdvancedFriday1 = new SwimLesson("Friday", "17:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedFriday2 = new SwimLesson("Friday", "17:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedFriday3 = new SwimLesson("Friday", "18:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedFriday4 = new SwimLesson("Friday", "18:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedFriday5 = new SwimLesson("Friday", "19:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//        SwimLesson lessonAdvancedFriday6 = new SwimLesson("Friday", "19:30", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
////        SwimLesson lessonAdvancedFriday7 = new SwimLesson("Friday", "20:00", SwimLevel.ADVANCED, instructor1, 30); // Duration: 30 minutes
//
//        // Add students to lessons
////        lessonNoviceMonday1.addStudents(Arrays.asList(aliceSmith));
////        lessonImproverMonday1.addStudents(Arrays.asList(bobJohnson));
////        lessonAdvancedMonday1.addStudents(Arrays.asList(michaelKing));
//// Use the found students to add them to swim lessons
//        //Novice  
//        //Novice for Monday
//        lessonNoviceMonday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceMonday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceMonday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceMonday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceMonday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceMonday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonNoviceMonday7.addStudents(new ArrayList<>(foundStudents.values()));
//
//        //Novice for Wednesday
//        lessonNoviceWednesday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceWednesday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceWednesday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceWednesday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceWednesday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceWednesday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonNoviceWednesday7.addStudents(new ArrayList<>(foundStudents.values()));
//
//        //Novice for Friday
//        lessonNoviceFriday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceFriday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceFriday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceFriday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceFriday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonNoviceFriday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonNoviceFriday7.addStudents(new ArrayList<>(foundStudents.values()));
//
////Improver
//// Improver for Monday
//        lessonImproverMonday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverMonday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverMonday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverMonday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverMonday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverMonday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonImproverMonday7.addStudents(new ArrayList<>(foundStudents.values()));
//
//// Improver for Wednesday
//        lessonImproverWednesday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverWednesday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverWednesday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverWednesday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverWednesday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverWednesday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonImproverWednesday7.addStudents(new ArrayList<>(foundStudents.values()));
//
//// Improver for Friday
//        lessonImproverFriday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverFriday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverFriday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverFriday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverFriday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonImproverFriday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonImproverFriday7.addStudents(new ArrayList<>(foundStudents.values()));
//
////Advanced
////Advanced for Monday
//        lessonAdvancedMonday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedMonday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedMonday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedMonday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedMonday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedMonday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonAdvancedMonday7.addStudents(new ArrayList<>(foundStudents.values()));
////Advanced for Wednesday
//        lessonAdvancedWednesday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedWednesday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedWednesday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedWednesday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedWednesday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedWednesday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonAdvancedWednesday7.addStudents(new ArrayList<>(foundStudents.values()));
//
////Advanced for Friday        
//        lessonAdvancedFriday1.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedFriday2.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedFriday3.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedFriday4.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedFriday5.addStudents(new ArrayList<>(foundStudents.values()));
//        lessonAdvancedFriday6.addStudents(new ArrayList<>(foundStudents.values()));
////lessonAdvancedFriday7.addStudents(new ArrayList<>(foundStudents.values()));
//
//        // Add lessons to the lesson list
//        //Novice for Monday
//        lessonList.addLesson(lessonNoviceMonday1);
//        lessonList.addLesson(lessonNoviceMonday2);
//        lessonList.addLesson(lessonNoviceMonday3);
//        lessonList.addLesson(lessonNoviceMonday4);
//        lessonList.addLesson(lessonNoviceMonday5);
//        lessonList.addLesson(lessonNoviceMonday6);
//
//        //Novice for Wednesday
//        lessonList.addLesson(lessonNoviceWednesday1);
//        lessonList.addLesson(lessonNoviceWednesday2);
//        lessonList.addLesson(lessonNoviceWednesday3);
//        lessonList.addLesson(lessonNoviceWednesday4);
//        lessonList.addLesson(lessonNoviceWednesday5);
//        lessonList.addLesson(lessonNoviceWednesday6);
//
//        //Novice for  Friday
//        lessonList.addLesson(lessonNoviceFriday1);
//        lessonList.addLesson(lessonNoviceFriday2);
//        lessonList.addLesson(lessonNoviceFriday3);
//        lessonList.addLesson(lessonNoviceFriday4);
//        lessonList.addLesson(lessonNoviceFriday5);
//        lessonList.addLesson(lessonNoviceFriday6);
//
//        //Improver
//        // Improver for Monday
//        lessonList.addLesson(lessonImproverMonday1);
//        lessonList.addLesson(lessonImproverMonday2);
//        lessonList.addLesson(lessonImproverMonday3);
//        lessonList.addLesson(lessonImproverMonday4);
//        lessonList.addLesson(lessonImproverMonday5);
//        lessonList.addLesson(lessonImproverMonday6);
//
//        //Improver for Wednesday
//        lessonList.addLesson(lessonImproverWednesday1);
//        lessonList.addLesson(lessonImproverWednesday2);
//        lessonList.addLesson(lessonImproverWednesday3);
//        lessonList.addLesson(lessonImproverWednesday4);
//        lessonList.addLesson(lessonImproverWednesday5);
//        lessonList.addLesson(lessonImproverWednesday6);
//
//        //Improver for Friday
//        lessonList.addLesson(lessonImproverFriday1);
//        lessonList.addLesson(lessonImproverFriday2);
//        lessonList.addLesson(lessonImproverFriday3);
//        lessonList.addLesson(lessonImproverFriday4);
//        lessonList.addLesson(lessonImproverFriday5);
//        lessonList.addLesson(lessonImproverFriday6);
//
//        //Advanced
//        // Advanced for Monday
//        lessonList.addLesson(lessonAdvancedMonday1);
//        lessonList.addLesson(lessonAdvancedMonday2);
//        lessonList.addLesson(lessonAdvancedMonday3);
//        lessonList.addLesson(lessonAdvancedMonday4);
//        lessonList.addLesson(lessonAdvancedMonday5);
//        lessonList.addLesson(lessonAdvancedMonday6);
//
//        // Advanced for Wednesday
//        lessonList.addLesson(lessonAdvancedWednesday1);
//        lessonList.addLesson(lessonAdvancedWednesday2);
//        lessonList.addLesson(lessonAdvancedWednesday3);
//        lessonList.addLesson(lessonAdvancedWednesday4);
//        lessonList.addLesson(lessonAdvancedWednesday5);
//        lessonList.addLesson(lessonAdvancedWednesday6);
//
//        // Advanced for Friday
//        lessonList.addLesson(lessonAdvancedFriday1);
//        lessonList.addLesson(lessonAdvancedFriday2);
//        lessonList.addLesson(lessonAdvancedFriday3);
//        lessonList.addLesson(lessonAdvancedFriday4);
//        lessonList.addLesson(lessonAdvancedFriday5);
//        lessonList.addLesson(lessonAdvancedFriday6);
//
//        // Return the list of swim lessons
//        return lessonList.getLessons();
//    }

}
