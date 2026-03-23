import core.Pen;
import factory.PenFactory;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter pen type (ball/gel/ink): ");
        String type = scanner.nextLine();
        
        System.out.println("Enter pen color (e.g., blue, red, black): ");
        String color = scanner.nextLine();
        
        System.out.println("Does it have a cap? (true/false): ");
        boolean withCap = scanner.nextBoolean();
        
        System.out.println("\n--- Manufacturing Pen ---");
        try {
            Pen myPen = PenFactory.createPen(type, color, withCap);
            
            System.out.println("\n--- Testing Pen ---");
            myPen.start();
            myPen.write();
            myPen.close();
            
            System.out.println("\n--- Refilling Pen ---");
            System.out.println("Refilling with green ink...");
            myPen.refill("green");
            
            System.out.println("\n--- Testing Refilled Pen ---");
            myPen.start();
            myPen.write();
            myPen.close();
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
        
        scanner.close();
    }
}
