package strategies;
import models.Ticket;
import models.Bill;
import models.Gate;
import java.util.Date;

public interface FeeCalculationStrategy {
    Bill generateBill(Ticket ticket, Gate exitGate, Date outTime);
}
