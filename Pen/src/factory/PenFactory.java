package factory;

import core.Pen;
import behaviors.*;

public class PenFactory {
    public static Pen createPen(String type, String color, boolean withCap) {
        WritingBehavior writingBehavior;
        switch (type.toLowerCase()) {
            case "ball":
                writingBehavior = new BallWritingBehavior();
                break;
            case "gel":
                writingBehavior = new GelWritingBehavior();
                break;
            case "ink":
                writingBehavior = new InkWritingBehavior();
                break;
            default:
                throw new IllegalArgumentException("Unknown pen type: " + type);
        }

        OpeningBehavior openingBehavior = withCap ? new CapOpeningBehavior() : new ClickOpeningBehavior();

        return new Pen(color, writingBehavior, openingBehavior);
    }
}
