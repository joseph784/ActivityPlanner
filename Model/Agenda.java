package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
// This is the agenda which is a LinkedList that can have Activities added to it
// Activities can be deleted, or moved from the Agenda to the inProgress List
// Inspiration for the toJson and activitiesToJson methods was taken from the JsonSerializationDemo project

public class Agenda implements Writable {
    private LinkedList<Activity> agendaList;
    InProgress inProgress;
    private int totalHours = 0;
    private int numberOfActivity = 0;
    Event addActivityEvent;
    Event removeActivityEvent;

    // EFFECTS: Creates a new agenda
    public Agenda(InProgress inProgress) {
        agendaList = new LinkedList<>();
        this.inProgress = inProgress;
    }

    //REQUIRES:
    // MODIFIES: this
    // EFFECTS: Adds an activity to the agenda, ordering it in terms of priority status.
    // If it has high priority, then it is placed at the top of the agenda,
    // if low, it is placed at the bottom of the agenda
    public void addActivity(Activity activity) {
        if (activity.priority) {
            agendaList.addFirst(activity);
        } else {
            agendaList.addLast(activity);
        }
        numberOfActivity = numberOfActivity + 1;
        totalHours = totalHours + activity.time;
        addActivityEvent = new Event("Activity added: " + activity.getTitle());
        EventLog.getInstance().logEvent(addActivityEvent);
    }

    //REQUIRES: Agenda is not empty. Can only work on 1 activity at a time
    //MODIFIES: this and InProgress
    //EFFECTS: Removes the first Activity from the Agenda, adds it to inProgress
    // and changes field, isBeingWorkedOn to true
    public void getFirstActivity() {
        Activity firstActivity = agendaList.removeFirst();
        firstActivity.isBeingWorkedOn = true;
        inProgress.addFirst(firstActivity);
        numberOfActivity = numberOfActivity - 1;
        totalHours = totalHours - firstActivity.time;
    }

    //EFFECTS: returns all the Activities in the Agenda. If empty, returns empty List
    public LinkedList<Activity> seeAgenda() {
        if (agendaList.isEmpty()) {
            return new LinkedList<>();
        } else {
            return agendaList;
        }
    }


    //REQUIRES: must have 1 and only 1 Activity that is under "Current Status"
    //MODIFIES: this and InProgress
    //EFFECTS:Once the Activity is completed, it's field, isBeingWorkedOn changes to false
    public void doneActivity() {
        inProgress.removeFirst();

    }

    //REQUIRES: Agenda must have at least 1 Activity, given Title must be in Agenda
    // MODIFIES: this
    //EFFECTS: removes Activity with given Title from Agenda
    public void removeActivity(String title) {
        Activity removed = null;
        for (Activity a : agendaList) {
            if (title.equals(a.title)) {
                removed = a;
            }
        }
        if (removed != null) {
            agendaList.remove(removed);
            totalHours = totalHours - removed.time;
            numberOfActivity = numberOfActivity - 1;
            removeActivityEvent = new Event("Activity removed: " + removed.getTitle());
            EventLog.getInstance().logEvent(removeActivityEvent);
        }
    }

    //EFFECTS: returns the total number of Activities in the Agenda
    public int getNumberOfTotalActivities() {
        return numberOfActivity;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the combined total hours of all Activities in Agenda
    public int getTotalHours() {
        return totalHours;
    }

    // EFFECTS: returns an unmodifiable list of Activities in this Agenda
    public List<Activity> getActivities() {
        return Collections.unmodifiableList(agendaList);
    }

    // EFFECT: returns JSONObject with Activities
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Activities", activitiesToJson());
        return json;
    }

    // EFFECTS: returns Activities in this Agenda as a JSON array
    private JSONArray activitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Activity activity : agendaList) {
            jsonArray.put(activity.toJson());
        }
        return jsonArray;
    }

}
