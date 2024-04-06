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
        studentList.addStudent(new SwimStudent("Alice", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Bob", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Charlie", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("David", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Emma", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Frank", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Grace", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Henry", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Isabella", SwimLevel.NOVICE));
        studentList.addStudent(new SwimStudent("Jack", SwimLevel.NOVICE));

// Add 10 improver level students
        studentList.addStudent(new SwimStudent("Liam", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Olivia", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Noah", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Ava", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("William", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Sophia", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("James", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Charlotte", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Benjamin", SwimLevel.IMPROVER));
        studentList.addStudent(new SwimStudent("Mia", SwimLevel.IMPROVER));

// Add 10 advanced level students
        studentList.addStudent(new SwimStudent("Michael", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Emily", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Alexander", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Abigail", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Ethan", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Evelyn", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Daniel", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Samantha", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Matthew", SwimLevel.ADVANCED));
        studentList.addStudent(new SwimStudent("Madison", SwimLevel.ADVANCED));

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

        // Create sample students
        SwimStudent alice = new SwimStudent("Alice", SwimLevel.NOVICE);
        SwimStudent sam = new SwimStudent("Sam", SwimLevel.NOVICE);

        SwimStudent bob = new SwimStudent("Bob", SwimLevel.IMPROVER);
        SwimStudent charlie = new SwimStudent("Charlie", SwimLevel.ADVANCED);

        // Create sample swim lessons
        SwimLesson lesson1 = new SwimLesson("Monday", "17:00", SwimLevel.NOVICE, instructor1);
        SwimLesson lesson2 = new SwimLesson("Wednesday", "18:00", SwimLevel.IMPROVER, instructor2);
        SwimLesson lesson3 = new SwimLesson("Friday", "19:00", SwimLevel.ADVANCED, instructor3);

//        public SwimLesson(String dayOfWeek, String startTime, SwimLevel level, Instructor instructor) {
        // Add students to lessons
        lesson1.addStudents(Arrays.asList(alice, sam));
        lesson2.addStudents(Arrays.asList(bob));
        lesson3.addStudents(Arrays.asList(charlie));
        // Add lessons to the lesson list
        lessonList.addLesson(lesson1);
        lessonList.addLesson(lesson2);
        lessonList.addLesson(lesson3);

        // Return the list of swim lessons
        return lessonList.getLessons();
    }

}
