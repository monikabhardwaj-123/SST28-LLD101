import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        InvoiceStore store = new FileStore();
        InvoicePrinter printer = new InvoicePrinter();

        CafeteriaSystem sys = new CafeteriaSystem(store, printer);
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1));

        // Subtotal = 160 + 30 = 190.
        // Tax for student = 5% of 190 = 9.5
        // Discount for student (subtotal >= 180) = 10
        // Total = 190 + 9.5 - 10 = 189.50
        sys.checkout(new StudentTax(), new StudentDiscount(), order);

        // Stretch goal: Add a second invoice for a staff member with different discount
        // policy.
        /*
         * System.out.println("\n--- Staff Example ---");
         * List<OrderLine> staffOrder = List.of(
         * new OrderLine("M1", 1),
         * new OrderLine("C1", 1),
         * new OrderLine("S1", 1)
         * );
         * sys.checkout(new StaffTax(), new StaffDiscount(), staffOrder);
         */
    }
}
