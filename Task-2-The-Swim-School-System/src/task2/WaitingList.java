package task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WaitingList {
   private Map<SwimLevel, List<SwimStudent>> waitingList;

    public WaitingList() {
        waitingList = new HashMap<>();
        waitingList.put(SwimLevel.NOVICE, new ArrayList<>());
        waitingList.put(SwimLevel.IMPROVER, new ArrayList<>());
        waitingList.put(SwimLevel.ADVANCED, new ArrayList<>());
    }

    // Method to add student to the waiting list
    public void addStudent(SwimStudent student) {
        waitingList.get(student.getLevel()).add(student);
    }

    // Method to remove student from the waiting list
    public void removeStudent(SwimStudent student) {
        waitingList.get(student.getLevel()).remove(student);
    }

    // Getter for waiting list
    public Map<SwimLevel, List<SwimStudent>> getWaitingList() {
        return waitingList;
    }

}
