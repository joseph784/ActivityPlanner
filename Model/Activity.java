package model;

import persistence.Writable;

import org.json.JSONObject;

// This is an activity, which will be added to the Agenda and inProgress Lists
// It consists of 4 fields which describe the title, description, priority status and time it will take to complete
// Inspiration for the toJson method was taken from the JsonSerializationDemo project

public class Activity implements Writable {

    String title;
    String description;
    Boolean priority;
    int time;
    Boolean isCompleted = false;
    Boolean isBeingWorkedOn = false;

    // REQUIRES: time must be greater than 0, title MUST be unique
    // Constructs an Activity object which has a title, description, priority status and
    // the time it will take to complete
    public Activity(String title, String description, Boolean priority, int time) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.time = time;
    }

    //REQUIRES
    //EFFECTS: getters
    public String getTitle() {

        return this.title;
    }

    public String getDescription() {

        return this.description;
    }

    public Boolean getPriority() {

        return this.priority;
    }

    public int getTime() {
        return this.time;
    }

    public Boolean getIsCompleted() {
        return this.isCompleted;
    }

    public Boolean getIsBeingWorkedOn() {
        return this.isBeingWorkedOn;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Description", description);
        json.put("Priority", priority);
        json.put("Time", time);
        return json;
    }


}
