package model;

import java.util.LinkedList;


// This is a LinkedList which can take Activities once they are being worked on
// Once the Activity is completed, it can be removed from the inProgress List
public class InProgress {
    public final LinkedList<Activity> inProgressList;

    //Creates a new InProgress List, for Activities which are currently being worked on
    public InProgress() {
        inProgressList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds an Activity to inProgressList when Activity is being worked on
    public void addFirst(Activity activity) {
        inProgressList.addFirst(activity);
    }

    //MODIFIES: this
    //EFFECTS: Removes an Activity from inProgressList when Activity is completed
    public void removeFirst() {
        inProgressList.removeFirst();
    }

    //EFFECTS: Returns current inProgressList
    public LinkedList<Activity> seeInProgressList() {
        return inProgressList;
    }
}