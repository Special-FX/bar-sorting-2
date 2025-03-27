import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LayoutPanel extends JPanel implements ActionListener {

    private OptionsPanel optionP;
    private BarPanel barP;

    public LayoutPanel() {
        this.setLayout(new BorderLayout());

        optionP = new OptionsPanel(this);
        this.add(optionP, BorderLayout.NORTH);

        barP = new BarPanel(this);
        this.add(barP, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(optionP.isAutorunOn()) barP.stepForward();
    }

    public void performOption(OptionEvent type) {
        if(type == OptionEvent.ADD) barP.addBar();

        if(type == OptionEvent.REMOVE) barP.removeBar();

        if(type == OptionEvent.RANDOMIZE) barP.randomize();

        if(type == OptionEvent.STEP) barP.stepForward();

        if(type == OptionEvent.SORT) {
            if(optionP.getSortChoice().equals("bubble sort")) barP.bubbleSort();
            if(optionP.getSortChoice().equals("selection sort")) barP.selectionSort();
            if(optionP.getSortChoice().equals("insertion sort")) barP.insertionSort();
            if(optionP.getSortChoice().equals("merge sort")) barP.mergeSort();
            if(optionP.getSortChoice().equals("bogo sort")) barP.bogoSort();
        }
    }
}