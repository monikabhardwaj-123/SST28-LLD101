package behaviors;

public class InkWritingBehavior implements WritingBehavior {
    @Override
    public void write(String color) {
        System.out.println("Writing elegantly like a fountain pen with " + color + " liquid ink.");
    }

    @Override
    public void refill(String color) {
        System.out.println("Refilling ink pen by soaking " + color + " ink directly into the reservoir.");
    }
}
