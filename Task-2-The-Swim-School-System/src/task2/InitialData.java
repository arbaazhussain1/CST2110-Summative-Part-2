package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        System.out.println("List of Students:");
        for (SwimStudent student : studentList.getAllStudents()) {
            System.out.println("Name: " + student.getName());
        }
        // Assign the sorted list of students to swimStudents
        swimStudents = students;

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
        Instructor instructor3 = new Instructor("Alex Smith");

        // Retrieve the list of students
        List<SwimStudent> students = getSwimStudents();

        // Find the students by name
        SwimStudent aliceSmith = null;
        SwimStudent bobJohnson = null;
        SwimStudent michaelKing = null;
        for (SwimStudent student : students) {
            if (student.getName().equals("Alice Smith")) {
                aliceSmith = student;
            } else if (student.getName().equals("Bob Johnson")) {
                bobJohnson = student;
            } else if (student.getName().equals("Michael King")) {
                michaelKing = student;
            }
        }

        // Create sample swim lessons
        SwimLesson lesson1 = new SwimLesson("Monday", "17:00", SwimLevel.NOVICE, instructor1);
        SwimLesson lesson2 = new SwimLesson("Wednesday", "18:00", SwimLevel.IMPROVER, instructor2);
        SwimLesson lesson3 = new SwimLesson("Friday", "19:00", SwimLevel.ADVANCED, instructor3);

        // Add students to lessons
        lesson1.addStudents(Arrays.asList(aliceSmith, bobJohnson));
        lesson3.addStudents(Arrays.asList(michaelKing));

        // Add lessons to the lesson list
        lessonList.addLesson(lesson1);
        lessonList.addLesson(lesson2);
        lessonList.addLesson(lesson3);

        // Return the list of swim lessons
        return lessonList.getLessons();
    }

}
