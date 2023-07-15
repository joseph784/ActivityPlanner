package ui;

import model.Activity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Creates new JFrame with a JTable, allowing user to see their current Agenda
public class SeeActivitiesFrame extends JFrame {
    private static final int WIDTH = 900;
    private static final int LENGTH = 550;
    JLabel seeAgendaNotEmptyGif;
    JLabel seeAgendaEmptyGif;

    // JTable Fields
    JTable agendaTable;
    String[] headers;
    String[] rowEntry;
    JScrollPane scrollP;
    ArrayList<String[]> data;

    // MODIFIES: this
    // EFFECTS: Constructor, creates new JFrame with a JList and JPanel with ImageIcon
    public SeeActivitiesFrame() {
        seeAgendaNotEmptyGif = new JLabel(new ImageIcon("Media/spongeboblist.gif"));
        seeAgendaEmptyGif = new JLabel(new ImageIcon("Media/patrickGif.gif"));
        data = new ArrayList<>();
        enterRow();
        headers = new String[]{"Title", "Description", "Priority", "Time"};
        agendaTable = new JTable(data.toArray(new String[0][]), headers);
        scrollP = new JScrollPane(agendaTable);
        specifyTable();
        if (AgendaFrame.agenda.seeAgenda().isEmpty()) {
            add(seeAgendaEmptyGif, BorderLayout.CENTER);
        } else {
            add(seeAgendaNotEmptyGif, BorderLayout.CENTER);
        }
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets specifications for the JFrame and JTable created as well as JPanel
    public void specifyTable() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, LENGTH));
        setTitle("See my Agenda");
        agendaTable.setBounds(30, 40, 200, 300);
        scrollP.setPreferredSize(new Dimension(400, 100));
        scrollP.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollP, BorderLayout.PAGE_START);
        seeAgendaNotEmptyGif.setPreferredSize(new Dimension(30,30));
        seeAgendaEmptyGif.setPreferredSize(new Dimension(30,30));
    }

    // MODIFIES: this
    // EFFECTS: Adds all Activities in Agenda to JTable, respective of Title, Description,
    // Priority Status and Time
    public void enterRow() {
        for (Activity a : AgendaFrame.agenda.seeAgenda()) {
            String titleString = a.getTitle();
            String descriptionString = a.getDescription();
            String priorityString = a.getPriority().toString();
            String timeString = Integer.toString(a.getTime());
            rowEntry = new String[]{titleString, descriptionString, priorityString, timeString};
            data.add(rowEntry);
        }
    }
}

