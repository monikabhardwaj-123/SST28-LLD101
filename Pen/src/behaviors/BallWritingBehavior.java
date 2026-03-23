package behaviors;

public class BallWritingBehavior implements WritingBehavior {
    @Override
    public void write(String color) {
        System.out.println("Writing smoothly with " + color + " ballpoint ink.");
    }

    @Override
    public void refill(String color) {
        System.out.println("Refilling ballpoint pen with " + color + " ink cartridge.");
    }
}
