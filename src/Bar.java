import java.awt.*;
import java.awt.Color;


public class Bar {
    //fields
    private Color color;
    private int height;

    //constructors

    /*
    public Bar(int height) { //random color bars
        this.height = height;
        int r, g, b;

        //you could make it times 256 (* 256), but I did it lower,
        //so it doesn't make bars that are too dark
        //just make sure the two numbers other than Math.random()
        //each add up to 256

        r = (int) (Math.random() * 206 + 50);
        g = (int) (Math.random() * 206 + 50);
        b = (int) (Math.random() * 206 + 50);

        color = new Color(r,g,b);
    }
    */

    public Bar(int height) { //gradient bars
        this.height = height;
        int r, g, b; //make crimson to white somehow

        r = 150; //150
        g = this.getHeight() / 3;
        b = 250; //250

//        r = this.getHeight() / 3;
//        g = 120;
//        b = 200;

        color = new Color(r,g,b);
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }
}
