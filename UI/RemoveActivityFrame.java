package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a new JFrame allowing the user to remove an Activity from the Agenda
// The Frame has 1 input JTextField and 1 JButton
public class RemoveActivityFrame extends JFrame implements ActionListener {

    private static final int WIDTH = 420;
    private static final int LENGTH = 350;
    JTextField removeTitle;
    JLabel removeActivityPrompt;
    JButton removeActivityButton;
    JLabel eyebrowRock;

    // MODIFIES: this
    // EFFECTS: Constructs a new JFrame with JTextField, JButton and ImageIcon
    public RemoveActivityFrame() {
        setSize(new Dimension(WIDTH, LENGTH));
        setTitle("Remove an Activity");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        eyebrowRock = new JLabel(new ImageIcon("Media/dwayne-rock.gif"));
        removeActivityPrompt = new JLabel("Enter the Title of the Activity you want removed");
        removeActivityPrompt.setVerticalTextPosition(SwingConstants.TOP);

        removeTitle = new JTextField();
        removeTitle.addActionListener(this);
        removeTitle.setActionCommand("Title");
        removeTitle.setPreferredSize(new Dimension(10,5));

        add(removeActivityPrompt, BorderLayout.CENTER);
        add(removeTitle, BorderLayout.CENTER);
        add(eyebrowRock, BorderLayout.CENTER);

        removeActivityButton = new JButton("Remove Activity");
        removeActivityPrompt.setPreferredSize(new Dimension(50, 20));
        removeActivityButton.addActionListener(this);
        removeActivityButton.setActionCommand("Done");
        removeActivityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(removeActivityButton);
        setVisible(true);
    }

    // MODIFIES:
    // EFFECTS: Action Performed method, allows user to input Title of Activity they want to remove,
    // removing it from the Agenda if Title matches with an Activity title in Agenda.
    // Closes the new frame if JButton is pressed.
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        String title = removeTitle.getText();
        if (action == "Done") {
            AgendaFrame.agenda.removeActivity(title);
            dispose();
        }
    }
}
