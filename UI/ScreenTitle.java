package ui;

import javax.swing.*;
import java.awt.*;


// creates new JPanel with title for AgendaFrame
public class ScreenTitle extends JPanel {

    // MODIFIES: this
    // EFFECTS: Constructor, creates new JPanel
    public ScreenTitle() {
        JLabel screenTitle = new JLabel("Welcome to your Agenda - Activity Tracker");
        screenTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        screenTitle.setHorizontalAlignment(JLabel.CENTER);
        setBackground(Color.cyan);
        this.setBounds(0,0,2000,200);
        setVisible(true);
        this.add(screenTitle);
    }

}
