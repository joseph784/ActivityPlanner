package ui;

import model.Activity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a new JFrame, allowing the user to add an Activity to the agenda.
// The AddActivityFrame has 4 inputs, to specify the Activity
public class AddActivityFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int LENGTH = 600;
    private static final int TEXT_WIDTH = 300;
    private static final int TEXT_LENGTH = 30;

    private JTextField titleInput;
    private JTextField descriptionInput;
    private JTextField timeInput;
    private String title;
    private String description;
    boolean priority = false;
    int time;
    private JButton priorityButton;
    private JButton addActivityButton;
    private JLabel titleQuestion;
    private JLabel descriptionQuestion;
    private JLabel priorityQuestion;
    private JLabel timeQuestion;
    ImageIcon checkmark;


    // MODIFIES: this
    // EFFECTS:Constructs new JFrame, allowing user to add Activity
    public AddActivityFrame() {
        checkmark = new ImageIcon("Media/checkmark.gif");
        titleInput = new JTextField();
        descriptionInput = new JTextField();
        timeInput = new JTextField();
        priorityButton = new JButton("No");
        addActivityButton = new JButton("Add your Activity");
        titleQuestion = new JLabel("Add your Activity title here");
        titleQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionQuestion = new JLabel("Add a description of your Activity");
        descriptionQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        priorityQuestion = new JLabel("Is this a priority?");
        priorityQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        timeQuestion = new JLabel("Enter the number of minutes will it take");
        timeQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        setSize(new Dimension(WIDTH, LENGTH));
        setTitle("Add an Activity");
        setVisible(true);
        setLayout(new GridLayout(0,1,2,1));

        titleInput.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_LENGTH));
        add(titleQuestion);
        add(titleInput);

        addActivityFrameHelper();
    }

    // MODIFIES: this
    // EFFECTS: Helper method in order to abide by checkstyle rules
    // Helps constructor create Add Activity Frame
    public void addActivityFrameHelper() {

        descriptionInput.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_LENGTH));
        add(descriptionQuestion);
        add(descriptionInput);

        timeInput.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_LENGTH));
        add(timeQuestion);
        add(timeInput);

        priorityButton.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_LENGTH));
        priorityButton.setBackground(Color.RED);
        priorityButton.addActionListener(this);
        priorityButton.setActionCommand("Priority");
        add(priorityQuestion);
        add(priorityButton);

        add(addActivityButton);
        addActivityButton.addActionListener(this);
        addActivityButton.setActionCommand("Done");

    }

    // REQUIRES: All JTextFields must be filled out, timeInput JTextField must take integers only
    // JTextFields must be filled out before any JButtons are used
    // MODIFIES: this
    // EFFECTS: Action Performed method, allows user to specify whether Activity is a priority
    // and allows user to commit Activity to Agenda
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        title = titleInput.getText();
        description = descriptionInput.getText();
        time = Integer.parseInt(timeInput.getText());

        if (action == "Priority") {
            priorityButton.setBackground(Color.GREEN);
            priorityButton.setText("Yes");
            priority = true;
        }
        if (action == "Done") {
            Activity activity = new Activity(title, description, priority, time);
            AgendaFrame.agenda.addActivity(activity);
            dispose();
        }

    }
}
