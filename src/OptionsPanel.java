import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel implements ActionListener {
    //fields
    private JButton[] buttons;
    private LayoutPanel layoutP;
    private JCheckBox autorunToggle;
    private JComboBox<String> sortAlgorithms;

    //constructors
    public OptionsPanel(LayoutPanel lp) {
        layoutP = lp;

        this.autorunToggle = new JCheckBox("autorun");
        this.add(autorunToggle);

        this.sortAlgorithms = new JComboBox<String>();
        this.sortAlgorithms.addItem("merge sort");
        this.sortAlgorithms.addItem("bubble sort");
        this.sortAlgorithms.addItem("selection sort");
        this.sortAlgorithms.addItem("insertion sort");
        this.sortAlgorithms.addItem("bogo sort");
        this.add(sortAlgorithms);


        buttons = new JButton[7];
        buttons[0] = new JButton("sort");
        buttons[1] = new JButton("add");
        buttons[2] = new JButton("add 50");
        buttons[3] = new JButton("remove");
        buttons[4] = new JButton("remove 50");
        buttons[5] = new JButton("randomize");
        buttons[6] = new JButton("step");

        for (JButton b : buttons) {
            this.add(b);
            b.addActionListener(this);
        }
    }

    //methods
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() instanceof JButton) {
            JButton b = (JButton) arg0.getSource();
            System.out.println("button " + b.getText() + " was pressed");

            if(b.getText().equals("add"))
                layoutP.performOption(OptionEvent.ADD);

            if(b.getText().equals("add 50")) {
                for(int i = 0; i < 50; i++) {
                    layoutP.performOption(OptionEvent.ADD);
                }
            }

            if(b.getText().equals("remove"))
                layoutP.performOption(OptionEvent.REMOVE);

            if(b.getText().equals("remove 50")) {
                for(int i = 0; i < 50; i++) {
                    layoutP.performOption(OptionEvent.REMOVE);
                }
            }

            if(b.getText().equals("step"))
                layoutP.performOption(OptionEvent.STEP);

            if(b.getText().equals("randomize"))
                layoutP.performOption(OptionEvent.RANDOMIZE);

            if(b.getText().equals("sort"))
                layoutP.performOption(OptionEvent.SORT);
        }
    }

    public boolean isAutorunOn() {
        return autorunToggle.isSelected();
    }

    public String getSortChoice() {
        return (String) this.sortAlgorithms.getSelectedItem();
    }
}