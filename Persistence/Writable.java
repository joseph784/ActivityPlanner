package persistence;

import org.json.JSONObject;
// Public interface which is implemented by Activity and Agenda, returns JSON Obejct
// Inspiration for the Writable Interface was taken from the JsonSerializationDemo project

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
