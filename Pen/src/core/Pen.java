package core;

import behaviors.OpeningBehavior;
import behaviors.WritingBehavior;

public class Pen {
    private String color;
    private WritingBehavior writingBehavior;
    private OpeningBehavior openingBehavior;

    public Pen(String color, WritingBehavior writingBehavior, OpeningBehavior openingBehavior) {
        this.color = color;
        this.writingBehavior = writingBehavior;
        this.openingBehavior = openingBehavior;
    }

    public void start() {
        openingBehavior.start();
    }

    public void close() {
        openingBehavior.close();
    }

    public void write() {
        writingBehavior.write(color);
    }

    public void refill(String newColor) {
        this.color = newColor;
        writingBehavior.refill(newColor);
    }
    
    public String getColor() {
        return color;
    }
}
