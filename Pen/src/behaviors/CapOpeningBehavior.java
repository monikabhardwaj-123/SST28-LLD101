package behaviors;

public class CapOpeningBehavior implements OpeningBehavior {
    @Override
    public void start() {
        System.out.println("Cap removed. Pen is ready to write.");
    }

    @Override
    public void close() {
        System.out.println("Cap placed back. Pen is securely closed.");
    }
}
