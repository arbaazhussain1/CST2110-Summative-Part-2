package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimSchoolManagementSystem {

    private List<SwimStudent> students;
    private List<Instructor> instructors;
    private List<SwimLesson> lessons;
    private WaitingList waitingList;

    public SwimSchoolManagementSystem() {
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        lessons = new ArrayList<>();
        waitingList = new WaitingList();

    }
}
