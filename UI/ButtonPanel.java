package ui;


import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// JPanel consisting of 6 buttons
public class ButtonPanel extends JPanel implements ActionListener {
    private JButton addActivity;
    private JButton removeActivity;
    private JButton seeAgenda;
    private JButton saveAgenda;
    private JButton loadAgenda;
    private JButton quit;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static final String JSON_STORE = "./data/agenda.json";

    // MODIFIES: this
    // EFFECTS: Creates a Panel of Buttons which allow the user to Add and Remove Activities,
    // as well as Load, Save and See their Agenda, and Quit
    public ButtonPanel() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setBounds(0, 200, 2000, 200);
        setBackground(Color.black);
        setVisible(true);

        addActivity = new JButton("Add Activity");
        this.add(addActivity);
        addActivity.addActionListener(this);
        addActivity.setActionCommand("Add");

        removeActivity = new JButton("Remove Activity");
        this.add(removeActivity);
        removeActivity.addActionListener(this);
        removeActivity.setActionCommand("Remove");

        seeAgenda = new JButton("See Agenda");
        this.add(seeAgenda);
        seeAgenda.addActionListener(this);
        seeAgenda.setActionCommand("See");

        buttonPanelHelper1();
    }

    // MODIFIES: this
    // EFFECTS: Helper method in order to abide by checkstyle rules
    // Helps constructor create Button Panel
    public void buttonPanelHelper1() {

        saveAgenda = new JButton("Save Agenda");
        this.add(saveAgenda);
        saveAgenda.addActionListener(this);
        saveAgenda.setActionCommand("Save");

        loadAgenda = new JButton("Load Agenda");
        this.add(loadAgenda);
        loadAgenda.addActionListener(this);
        loadAgenda.setActionCommand("Load");

        quit = new JButton("Quit");
        this.add(quit);
        quit.addActionListener(this);
        quit.setActionCommand("Quit");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Calls methods based on which Button is pressed.
    // For Add, Remove Activity, and See Agenda, calls method to create new JFrame
    // For Load and Save, calls jsonReader and Writer. For Quit, exits program
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if (action == "Add") {
            new AddActivityFrame();
        } else if (action == "Remove") {
            new RemoveActivityFrame();
        } else if (action == "Quit") {
            System.out.println("Your Event Log Looks Like: \n");
            for (Event e : EventLog.getInstance()) {
                System.out.println(e.getDate() + " " + e.getDescription() + "\n");
            }
            System.exit(0);
        } else if (action == "See") {
            new SeeActivitiesFrame();
        } else {
            actionPerformedHelper(action);
        }
    }

    // EFFECTS: Helper method for the actionPerformed method,
    // For Load and Save, calls jsonReader and Writer is button is pressed
    public void actionPerformedHelper(String action) {
        if (action == "Save") {
            try {
                jsonWriter.open();
                jsonWriter.write(AgendaFrame.agenda);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not saved");
            }
        } else if (action == "Load") {
            try {
                AgendaFrame.agenda = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Unable to load Agenda from file");
            }
        }

    }

}



