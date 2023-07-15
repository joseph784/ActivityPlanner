package persistence;

import model.Activity;
import model.Agenda;
import model.InProgress;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Reads the created Json files
//Inspiration for the JsonReader Class was taken from the JsonSerializationDemo project

public class JsonReader {

    private String source;
    InProgress inProgress = new InProgress();

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

        this.source = source;
    }

    // EFFECTS: reads Agenda from file and returns it;
    // throws IOException if error occurs reading data from file
    public Agenda read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAgenda(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Agenda from JSON object and returns it
    private Agenda parseAgenda(JSONObject jsonObject) {
        Agenda agenda = new Agenda(inProgress);
        addActivities(agenda, jsonObject);
        return agenda;
    }

    // MODIFIES: agenda
    // EFFECTS: parses Activities from JSON object and adds them to Agenda
    private void addActivities(Agenda agenda, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Activities");
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActivity(agenda, nextActivity);
        }
    }

    // MODIFIES: agenda
    // EFFECTS: parses Activity from JSON object and adds it to Agenda
    private void addActivity(Agenda agenda, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String desc = jsonObject.getString("Description");
        Boolean priority = jsonObject.getBoolean("Priority");
        int time = jsonObject.getInt("Time");
        Activity activity = new Activity(title, desc, priority, time);
        agenda.addActivity(activity);
    }
}


