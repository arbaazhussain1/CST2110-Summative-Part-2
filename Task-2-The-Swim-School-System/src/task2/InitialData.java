package task2;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class InitialData {

    // Create a new list to hold the swim students
    public static StudentList studentList = new StudentList();
    public static LessonList lessonList = new LessonList(); // Create a lesson list instance
    public static List<Instructor> instructorList = new ArrayList<>();
    public static List<Qualification> qualificationList = new ArrayList<>();

    // Map to keep track of the number of times each student is assigned to a lesson on a specific day
    // and for a specific instructor
    private static Map<SwimStudent, Map<DayOfWeek, Map<Instructor, Integer>>> studentAssignments = new HashMap<>();


    // Method to populate student data
    // Method to populate and return student data sorted alphabetically by name
    public static void populateData() {
        populateStudentData();
        populateSwimLessons();
        populateSwimInstructors();
    }

    public static void populateStudentData() {
        // Add novice level students
        String[] noviceNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "William", "Sophia", "James", "Isabella", "Benjamin"};
        for (String name : noviceNames) {
            SwimStudent student = new SwimStudent(name, SwimLevel.NOVICE);
            studentList.addStudent(student);
            // Assign qualifications to novice level students
            DistanceSwim distanceSwim5m = new DistanceSwim(SwimLevel.NOVICE, null, 5);
            DistanceSwim distanceSwim10m = new DistanceSwim(SwimLevel.NOVICE, null, 10);
            DistanceSwim distanceSwim20m = new DistanceSwim(SwimLevel.NOVICE, null, 20);
            qualificationList.add(distanceSwim5m);
            qualificationList.add(distanceSwim10m);
            qualificationList.add(distanceSwim20m);
            student.addQualification(distanceSwim5m);
            student.addQualification(distanceSwim10m);
            student.addQualification(distanceSwim20m);
        }

        // Add improver level students
        String[] improverNames = {"Ethan", "Charlotte", "Mason", "Amelia", "Alexander", "Harper", "Michael", "Evelyn", "Daniel", "Abigail"};
        for (String name : improverNames) {
            SwimStudent student = new SwimStudent(name, SwimLevel.IMPROVER);
            studentList.addStudent(student);
            // Assign qualifications to improver level students
            DistanceSwim distanceSwim100m = new DistanceSwim(SwimLevel.IMPROVER, null, 100);
            DistanceSwim distanceSwim200m = new DistanceSwim(SwimLevel.IMPROVER, null, 200);
            DistanceSwim distanceSwim400m = new DistanceSwim(SwimLevel.IMPROVER, null, 400);
            qualificationList.add(distanceSwim100m);
            qualificationList.add(distanceSwim200m);
            qualificationList.add(distanceSwim400m);
            student.addQualification(distanceSwim100m);
            student.addQualification(distanceSwim200m);
            student.addQualification(distanceSwim400m);
        }

        // Add advanced level students
        String[] advancedNames = {"Jacob", "Emily", "Elijah", "Mia", "Matthew", "Scarlett", "Logan", "Madison", "Jackson", "Grace"};
        for (String name : advancedNames) {
            SwimStudent student = new SwimStudent(name, SwimLevel.ADVANCED);
            studentList.addStudent(student);
            // Assign qualifications to advanced level students
            DistanceSwim distanceSwim800m = new DistanceSwim(SwimLevel.ADVANCED, null, 800);
            DistanceSwim distanceSwim1500m = new DistanceSwim(SwimLevel.ADVANCED, null, 1500);
            DistanceSwim distanceSwim3000m = new DistanceSwim(SwimLevel.ADVANCED, null, 3000);
            PersonalSurvival personalSurvivalBronze = new PersonalSurvival(SwimLevel.ADVANCED, null, "Bronze");
            PersonalSurvival personalSurvivalSilver = new PersonalSurvival(SwimLevel.ADVANCED, null, "Silver");
            PersonalSurvival personalSurvivalGold = new PersonalSurvival(SwimLevel.ADVANCED, null, "Gold");
            qualificationList.add(distanceSwim800m);
            qualificationList.add(distanceSwim1500m);
            qualificationList.add(distanceSwim3000m);
            qualificationList.add(personalSurvivalBronze);
            qualificationList.add(personalSurvivalSilver);
            qualificationList.add(personalSurvivalGold);
            student.addQualification(distanceSwim800m);
            student.addQualification(distanceSwim1500m);
            student.addQualification(distanceSwim3000m);
            student.addQualification(personalSurvivalBronze);
            student.addQualification(personalSurvivalSilver);
            student.addQualification(personalSurvivalGold);
        }
    }

    public static void populateSwimLessons() {
        List<SwimLesson> lessons = new ArrayList<>();

        // Define the days of the week (Monday, Wednesday, and Friday)
        DayOfWeek[] daysOfWeek = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY};

        // Define the start times for the lessons
        LocalTime[] startTimes = {
                LocalTime.of(17, 0), LocalTime.of(17, 30),
                LocalTime.of(18, 0), LocalTime.of(18, 30),
                LocalTime.of(19, 0), LocalTime.of(19, 30)
        };

        // Define the durations for the lessons (30 minutes each)
        int duration = 30;

        // Iterate over each day of the week
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            // Clear the studentAssignments map for each day
            studentAssignments.clear();

            // Iterate over each start time
            for (LocalTime startTime : startTimes) {
                // Create lessons for each level (Novice, Improver, Advanced)
                for (SwimLevel level : SwimLevel.values()) {
                    // Create a lesson for the current day, time, and level
                    SwimLesson lesson = new SwimLesson(dayOfWeek, startTime, level, null, duration);

                    // Assign an instructor to the lesson
                    if (!instructorList.isEmpty()) {
                        Instructor instructor = instructorList.get(lessons.size() % instructorList.size());
                        lesson.setInstructor(instructor);
                    }

                    // Add students to the lesson based on their level and availability
                    List<SwimStudent> students = studentList.getStudentsByLevel(level);
                    for (SwimStudent student : students) {
                        // Check if the student has been assigned twice already on this day for this instructor
                        if (getStudentAssignments(student, dayOfWeek, lesson.getInstructor()) < 2) {
                            if (lesson.addStudent(student)) {
                                // Increment the assignment count for the student on this day for this instructor
                                incrementStudentAssignments(student, dayOfWeek, lesson.getInstructor());
                                // Student added to the lesson
                                student.setSwimLesson(lesson);
                            } else {
                                // If the lesson is full, add the student to the waiting list
                                studentList.addStudentToWaitingList(student);
                            }
                        }
                    }

                    // Add the lesson to the list
                    lessons.add(lesson);
                }
            }
        }
        // Add lessons to the lesson list
        for (SwimLesson lesson : lessons) {
            lessonList.addLesson(lesson);
        }
    }

    // Helper method to get the number of times a student is assigned to a lesson on a specific day for a specific instructor
    private static int getStudentAssignments(SwimStudent student, DayOfWeek day, Instructor instructor) {
        return studentAssignments.computeIfAbsent(student, k -> new HashMap<>())
                .getOrDefault(day, new HashMap<>())
                .getOrDefault(instructor, 0);

    }

    // Helper method to increment the number of times a student is assigned to a lesson on a specific day for a specific instructor
    private static void incrementStudentAssignments(SwimStudent student, DayOfWeek day, Instructor instructor) {
        studentAssignments.computeIfAbsent(student, k -> new HashMap<>())
                .computeIfAbsent(day, k -> new HashMap<>())
                .merge(instructor, 1, Integer::sum);

    }

    public static void populateSwimInstructors() {
        // Define the names of the instructors
        String[] instructorNames = {"John", "Emily", "David", "Sophia", "Michael", "Emma", "James", "Olivia", "Daniel"};

        // Create instructors for the entire week
        for (String name : instructorNames) {
            Instructor instructor = new Instructor(name);
            instructorList.add(instructor);
        }

        // Assign instructors to qualifications
        assignInstructorsToQualifications();

        // Iterate over each lesson and assign an instructor
        int index = 0;
        for (SwimLesson lesson : lessonList.getLessons()) {
            Instructor instructor = instructorList.get(index % instructorList.size());
            lesson.setInstructor(instructor);
            instructor.addLesson(lesson); // Add the lesson to the instructor's schedule
            index++;
        }
    }

    // Helper method to assign instructors to qualifications
    private static void assignInstructorsToQualifications() {
        // Iterate over each qualification and assign an instructor
        int index = 0;
        for (Qualification qualification : qualificationList) {
            Instructor instructor = instructorList.get(index % instructorList.size());
            qualification.setInstructor(instructor);
            index++;
        }
    }
}
