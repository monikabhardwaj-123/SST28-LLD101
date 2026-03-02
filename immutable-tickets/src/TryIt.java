import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate correctly returning copies instead of mutating
        IncidentTicket t2 = service.assign(t, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nAfter service mutations (returns new objects): ");
        System.out.println("Original: " + t);
        System.out.println("Latest:   " + t3);

        // Demonstrate external mutation via leaked list reference is now blocked
        try {
            List<String> tags = t3.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nCaught expected UnsupportedOperationException when mutating tags externally!");
            System.out.println("After external tag mutation block: " + t3);
        }
    }
}
