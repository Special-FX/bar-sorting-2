import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class BarPanel extends JPanel {
    // constants
    private static final int STEPSIZE = 30;

    // fields
    private ArrayList<Bar> bars;
    private LayoutPanel layoutP;
    private ArrayList<Step> steps;
    int stepCounter;
    ArrayList<Bar> startBars;
    ArrayList<Bar> buffer;

    // constructor
    public BarPanel(LayoutPanel lp) {
        this.layoutP = lp;
        this.bars = new ArrayList<Bar>();
        this.steps = new ArrayList<Step>();
        stepCounter = -1;
        this.startBars = new ArrayList<Bar>();
        this.buffer = new ArrayList<Bar>();
    }

    // bar methods
    public void addBar() {
        int height = (int) (Math.random() * (this.getHeight() - STEPSIZE) + STEPSIZE);
        Bar b = new Bar(height);
        bars.add(b);
        buffer.add(null);
        this.repaint();
    }

    public void removeBar() {
        bars.remove(bars.size()-1);
        buffer.remove(null);
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bars.isEmpty()) return;

        int barWidth, totalBarWidth, startX;
        barWidth = Math.min(300, this.getWidth() / bars.size());
        totalBarWidth = barWidth * bars.size();
        startX = (this.getWidth() / 2) - totalBarWidth / 2;

        for(int i = 0; i < bars.size(); i++) {
            Bar temp = bars.get(i);
            g.setColor(temp.getColor());
            g.fillRect(getBarX(i), this.getHeight() - temp.getHeight(), barWidth, temp.getHeight());
            startX += barWidth;
        }

        drawBuffer(g);
        drawStepMark(g);
    }

    // sorting methods
    public void bubbleSort() {
        if(bars.isEmpty()) return;
        else clearSteps();

        storeStartBars();
        for (int sortedIndex = bars.size(); sortedIndex > 0; sortedIndex--) {

            for (int index = 0; index < sortedIndex - 1; index++) {
                Bar bar1 = bars.get(index);
                Bar bar2 = bars.get(index + 1);

                Step compareStep = new Step(index, index+1, Step.COMPARE);
                steps.add(compareStep);

                if (bar1.getHeight() > bar2.getHeight()) {
                    Step swapStep = new Step(index, index+1, Step.SWAP);
                    steps.add(swapStep);

                    swapBar(index, index + 1);
                }
            }

        }
        restoreStart();
        this.repaint();
    }

    public void selectionSort() {
        if(bars.isEmpty()) return;

        clearSteps();
        storeStartBars();
        for(int i = 0; i < bars.size(); i++) {
            int minIndex = i;

            for(int j = i+1; j < bars.size(); j++) {
                Step compareStep = new Step(minIndex, j, Step.COMPARE);
                steps.add(compareStep);

                if(bars.get(minIndex).getHeight() > bars.get(j).getHeight()) minIndex = j;
            }
            Step swapStep = new Step(minIndex, i, Step.SWAP);
            steps.add(swapStep);

            swapBar(minIndex, i);
        }
        restoreStart();
        this.repaint();
    }

    public void insertionSort() {
        if(bars.isEmpty()) return;

        clearSteps();
        storeStartBars();
        for(int pass = 0; pass < bars.size()-1; pass++) {

            for (int i = pass+1; i > 0; i--) {
                Step compareStep = new Step(i, i-1, Step.COMPARE);
                steps.add(compareStep);

                if (bars.get(i).getHeight() < bars.get(i-1).getHeight()) {
                    Step swapStep = new Step(i, i-1, Step.SWAP);
                    steps.add(swapStep);

                    swapBar(i, i-1);
                } else break;
            }

        }
        restoreStart();
        this.repaint();
    }

    public void mergeSort() {
        clearSteps();
        storeStartBars();

        mergeSortRecursion(0, bars.size()-1);

        restoreStart();
        this.repaint();
    }

    public void mergeSortRecursion(int low, int high) {
        //base cases
        if(low >= high) return;

        if(low+1 == high) {
            Step compareStep = new Step(low, high, Step.COMPARE);
            steps.add(compareStep);

            int height1 = bars.get(low).getHeight(), height2 = bars.get(high).getHeight();
            if(height1 > height2) {
                Step swapStep = new Step(low, high, Step.SWAP);
                steps.add(swapStep);

                swapBar(high, low);
                return;
            }
        }

        //recursive
        int mid = (low + high) / 2;
        mergeSortRecursion(low, mid);
        mergeSortRecursion(mid+1, high);

        //merging the two sorted arrays
        int front = low, back = mid+1;
        boolean frontDone = false, backDone = false;

        for(int bufferIndex = low; bufferIndex <= high; bufferIndex++) {

            if(frontDone) {
                bufferSetAndSave(bufferIndex, back);
                back++;
                continue;
            } else if(backDone) {
                bufferSetAndSave(bufferIndex, front);
                front++;
                continue;
            }

            Bar frontBar = bars.get(front), backBar = bars.get(back);

            if(frontBar.getHeight() < backBar.getHeight()) { // the front bar is smaller so transfer it to the buffer array
                bufferSetAndSave(bufferIndex, front);
                front++;
                if(front > mid) frontDone = true;
            } else {
                bufferSetAndSave(bufferIndex, back);
                back++;
                if(back > high) backDone = true;
            }

        }

        for(int i = low; i <= high; i++) {
            Step barStep = new Step(i, i, Step.TOBARS);
            steps.add(barStep);

            Bar b = buffer.get(i);
            bars.set(i, b);
            buffer.set(i, null);
        }
    }

    public void bogoSort() {
        if(bars.isEmpty()) return;

        boolean unsorted = true;
        int sortedCount = 0;
        int[] arr = new int[bars.size()];
/*
        while(unsorted) { //DO NOT RUN BOGOSORT
            randomize();

            for(int i = 0; i < bars.size(); i++) { //populates arr with bar heights
                arr[i] = bars.get(i).getHeight();
            }

            for(int i = 0; i < bars.size()-1; i++) {
                if(arr[i] < arr[i+1]) {
                    sortedCount++;
                }
            }
            if(sortedCount == arr.length) unsorted = false;
            this.repaint();
        }
        this.repaint();
*/
    }

    // auxiliary methods
    private void swapBar(int index1, int index2) {
        Bar a = bars.get(index1);
        Bar b = bars.get(index2);
        bars.set(index1, b);
        bars.set(index2, a);
    }

    public void randomize() {
        for(int i = 0; i < bars.size(); i++) {
            int randIndex = (int) (Math.random() * bars.size());
            swapBar(i, randIndex);
        }
        clearSteps();
        this.repaint();
    }

    public int getBarX(int index) {
        int barWidth, totalBarWidth, startX;
        barWidth = Math.min(300, this.getWidth() / bars.size());
        totalBarWidth = bars.size() * barWidth;
        startX = ((this.getWidth() - totalBarWidth) / 2);
        return startX + (barWidth * index);
    }

    private void drawStepMark (Graphics g) {
        if(stepCounter == -1 || stepCounter == steps.size()) return;

        Step s = steps.get(stepCounter);
        int x1 = getBarX(s.getIndex1()), x2 = getBarX(s.getIndex2());

        if(s.getType() == Step.COMPARE) g.setColor(new Color(230,230,230));
        if(s.getType() == Step.SWAP) g.setColor(new Color(20, 20, 20));

        int barWidth = Math.min(300, this.getWidth() / bars.size());
        g.fillRect(x1, this.getHeight()-20, barWidth, 10);
        g.fillRect(x2, this.getHeight()-20, barWidth, 10);
    }

    private void clearSteps() {
        steps.clear();
        stepCounter = -1;
    }

    private void storeStartBars() {
        startBars.clear();
        for(Bar b : bars) {
            startBars.add(b);
        }
    }

    private void restoreStart() {
        bars.clear();
        for(Bar b : startBars) {
            bars.add(b);
        }
        stepCounter = 0;
    }

    public void stepForward() {
        if(stepCounter == -1 || stepCounter == steps.size()) return;

        Step s = steps.get(stepCounter);

        if(s.getType() == Step.SWAP) swapBar(s.getIndex1(), s.getIndex2());
        if(s.getType() == Step.TOBUFFER) buffer.set(s.getIndex1(), bars.get(s.getIndex2()));
        if(s.getType() == Step.TOBARS) {
            bars.set(s.getIndex1(), buffer.get(s.getIndex2()));
            buffer.set(s.getIndex2(), null);
        }

        stepCounter++;
        this.repaint();
    }

    public void bufferSetAndSave(int bufferIndex, int barIndex) {
        Step bufferStep = new Step(bufferIndex, barIndex, Step.TOBUFFER);
        steps.add(bufferStep);
        buffer.set(bufferIndex, bars.get(barIndex));
    }

    private void drawBuffer(Graphics g) {
        for (int i = 0; i < buffer.size(); i++) {
            Bar b = buffer.get(i);
            if (b == null) continue;
            else {
                g.setColor(new Color(20, 20, 20));
                g.drawRect(getBarX(i), this.getHeight() - b.getHeight(), this.getWidth() / bars.size(), b.getHeight());
            }
        }
    }

}