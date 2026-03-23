package strategies;
import models.Ticket;
import models.Bill;
import models.Gate;
import java.util.Date;
import java.util.UUID;

public class DynamicDurationFeeStrategy implements FeeCalculationStrategy {
    @Override
    public Bill generateBill(Ticket ticket, Gate exitGate, Date outTime) {
        long durationMillis = outTime.getTime() - ticket.getInTime().getTime();
        // Assume minimum 1 hour of charge
        double hours = Math.ceil(Math.max((double)durationMillis / (1000.0 * 60 * 60), 1.0));
        
        double ratePerHour = 0;
        switch(ticket.getSlot().getType()) {
            case SMALL: ratePerHour = 10; break;
            case MEDIUM: ratePerHour = 20; break;
            case LARGE: ratePerHour = 40; break;
        }

        double amount = hours * ratePerHour;
        return new Bill(UUID.randomUUID().toString(), ticket, exitGate, outTime, amount);
    }
}
