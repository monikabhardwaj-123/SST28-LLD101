package behaviors;

public class GelWritingBehavior implements WritingBehavior {
    @Override
    public void write(String color) {
        System.out.println("Writing thickly and vibrantly with " + color + " gel ink.");
    }

    @Override
    public void refill(String color) {
        System.out.println("Refilling gel pen with " + color + " gel refill tube.");
    }
}
