package behaviors;

public class ClickOpeningBehavior implements OpeningBehavior {
    @Override
    public void start() {
        System.out.println("Click! Pen is ready to write.");
    }

    @Override
    public void close() {
        System.out.println("Click! Pen is retracted and closed.");
    }
}
