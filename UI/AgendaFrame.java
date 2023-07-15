package ui;

import model.Agenda;
import model.InProgress;

import javax.swing.*;
import java.awt.*;

// inspiration for this class was taken from the SpaceInvaders project, from class
// Creates a new JFrame which is the 'main' Frame showcasing all the buttons to choose from
public class AgendaFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int LENGTH = 200;

    ButtonPanel buttonPanel;
    ScreenTitle screenTitle;
    static Agenda agenda;
    InProgress inProgress;

    // MODIFIES: this
    // EFFECTS: Constructor, creates main JFrame
    public AgendaFrame() {

        buttonPanel = new ButtonPanel();
        screenTitle = new ScreenTitle();
        inProgress = new InProgress();
        agenda = new Agenda(inProgress);
        //numberOf = new JLabel("You have " + agenda.seeAgenda().size() + " Activities");
        setTitle("Agenda Activity Tracker");
        setSize(new Dimension(WIDTH, LENGTH));
        setLayout(new FlowLayout());
        add(screenTitle);
        add(buttonPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
