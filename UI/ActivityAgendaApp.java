package ui;

import model.Activity;
import model.Agenda;
import model.EventLog;
import model.InProgress;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

//This is the main UI console from which the program is run
//It takes in user input and outputs based on that, carrying out the methods laid out in the Agenda class
//Inspiration for the Save and Load methods was taken from the JsonSerializationDemo project
public class ActivityAgendaApp {
    private static final String JSON_STORE = "./data/agenda.json";
    boolean runApp = true;
    InProgress inProgress = new InProgress();
    Agenda agenda = new Agenda(inProgress);
    private final Scanner input = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs ActivityAgenda application
    public ActivityAgendaApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAgendaTracker();
    }

    // MODIFIES: this
    // EFFECTS: gets first input from user
    private void runAgendaTracker() {
        System.out.println("Welcome to the Agenda Tracker!");

        while (runApp) {
            showAgendaMenu();
            String firstInput = input.nextLine();
            processInput(firstInput);
        }
    }

    //EFFECTS: Displays all Agenda Menu options that the user can choose
    private void showAgendaMenu() {
        System.out.println("Please type in one of the following commands");
        System.out.println("Add -> If you want to add an Activity");
        System.out.println("Delete -> If you want to delete an Activity");
        System.out.println("See -> If you want to see your current Agenda");
        System.out.println("Start -> If you want to start your next Activity");
        System.out.println("Finish -> If you want to complete your current Activity");
        System.out.println("Current -> If you want to see the Activity you currently are working on");
        System.out.println("Number -> If you want to see the total number of Activities in your Agenda");
        System.out.println("Time -> If you want to see the time it will take to complete all Activities in you Agenda");
        System.out.println("Save -> If you want to save your current Agenda");
        System.out.println("Load -> If you want to load your saved Agendas");
        System.out.println(("Quit -> If you want to quit the application"));
    }

    //EFFECTS: processes the user input
    private void processInput(String inputFromConsole) {
        String input = inputFromConsole.toLowerCase(Locale.ROOT);
        if (input.equals("add")) {
            addNewActivity();
        } else if (input.equals("delete")) {
            deleteNewActivity();
        } else if (input.equals("see")) {
            seeCurrentAgenda();
        } else if (input.equals("start")) {
            startNewActivity();
        } else if (input.equals("finish")) {
            finishNewActivity();
        } else if (input.equals("current")) {
            seeInProgress();
        } else if (input.equals("number")) {
            System.out.println("The number of Activities in your Agenda is " + agenda.getNumberOfTotalActivities()
                    + "\n");
        } else {
            processInputHelper(input);
        }
    }

    public void processInputHelper(String input) {

        if (input.equals("time")) {
            System.out.println("The total minutes it will take is " + agenda.getTotalHours() + "\n");
        } else if (input.equals("save")) {
            saveAgenda();
        } else if (input.equals("load")) {
            loadAgenda();
        } else if (input.equals("quit")) {
            System.out.println(EventLog.getInstance());
            System.exit(0);
        } else {
            System.out.println("That was not valid \n");
        }
    }

    //REQUIRES: Inputted time must be an integer
    //MODIFIES: this
    //EFFECTS: Adds an Activity do Agenda
    private void addNewActivity() {
        System.out.println("Add the title of your Activity here: ");
        String title = input.nextLine();
        System.out.println("Add the description of your Activity here: ");
        String description = input.nextLine();
        System.out.println("Is this Activity a priority? Enter Y/N");
        boolean priority = false;
        String priorityInput = input.nextLine();
        if (priorityInput.toLowerCase(Locale.ROOT).equals("y")) {
            priority = true;
            System.out.println("This Activity is high priority");
        } else if (priorityInput.toLowerCase(Locale.ROOT).equals("n")) {
            System.out.println("This Activity is low priority");
        } else {
            System.out.println("Invalid entry \n");
            addNewActivity();
            return;
        }
        System.out.print("How many minutes will this Activity take? Enter an integer value: ");
        String time = input.nextLine();
        int intTime = Integer.parseInt(time);
        Activity activity = new Activity(title, description, priority, intTime);
        agenda.addActivity(activity);
        System.out.println("Your Activity has been added \n");
    }

    ///MODIFIES: this
    ///EFFECTS: Deletes the Activity with the given title. If no Activity with given title exists,
    /// then nothing happens. If there are no Activities in Agenda, user is informed and returned to Menu
    private void deleteNewActivity() {
        if (agenda.seeAgenda().isEmpty()) {
            System.out.println("There are no Activities for you to delete" + "\n");
        } else {
            System.out.println("Type in the title of the Activity you would like to delete.");
            String soonToBeDeleted = input.nextLine();
            agenda.removeActivity(soonToBeDeleted);
            System.out.println("The Activity with that title will not be in the Agenda \n");
        }
    }

    //EFFECTS: shows user the current agenda
    private void seeCurrentAgenda() {
        System.out.println("Your current Agenda looks like: ");
        for (Activity a : agenda.seeAgenda()) {
            System.out.println("(" + a.getTitle() + ", " + a.getDescription() + ", " + "Priority: "
                    + a.getPriority() + ", " + "Time:" + a.getTime() + ")" + "\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes first Activity from Agenda and adds it to inProgress list. If Agenda is empty,
    // the user is informed and returned to Activity Menu
    private void startNewActivity() {
        if (agenda.seeAgenda().isEmpty()) {
            System.out.println("You don't have any Activities added to you Agenda yet"
                    + "\n");
        } else {
            System.out.println("Let's start your next Activity" + "\n");
            agenda.getFirstActivity();
        }
    }

    //MODIFIES: this
    // EFFECTS: Removes the Activity currently being worked on, from the inProgressList

    public void finishNewActivity() {
        if (inProgress.seeInProgressList().isEmpty()) {
            System.out.println("Looks like you haven't started any Activities yet"
                    + "\n");
        } else if (!inProgress.seeInProgressList().isEmpty()) {
            System.out.println("Your current Activity is completed!" + "\n");
            inProgress.removeFirst();
        }
    }

    //EFFECTS: returns list of Activities currently being worked on (only 1 Activity ever)
    public void seeInProgress() {
        System.out.println("Your current Activity is ");
        if (inProgress.seeInProgressList().isEmpty()) {
            System.out.println("Empty. Looks like you aren't working on anything at the moment"
                    + "\n");
        } else {
            Activity firstCurrent = inProgress.seeInProgressList().getFirst();
            System.out.println("(" + firstCurrent.getTitle() + ", " + firstCurrent.getDescription() + ", "
                    + "Priority: " + firstCurrent.getPriority() + ", " + "Time:" + firstCurrent.getTime() + ")"
                    + "\n");
        }

    }

    // EFFECTS: saves the agenda to JASON_STORE
    private void saveAgenda() {
        try {
            jsonWriter.open();
            jsonWriter.write(agenda);
            jsonWriter.close();
            System.out.println("Saved your agenda to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to add Agenda to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads agenda from file
    private void loadAgenda() {
        try {
            agenda = jsonReader.read();
            System.out.println("Loaded your agenda from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to load Agenda from file: " + JSON_STORE + "\n");
        }
    }
}


