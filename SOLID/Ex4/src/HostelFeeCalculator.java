import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<FeeComponent> feeComponents;

    public HostelFeeCalculator(FakeBookingRepo repo, List<FeeComponent> feeComponents) {
        this.repo = repo;
        this.feeComponents = feeComponents;
    }

    public void process(BookingRequest req) {
        Money monthly = new Money(0.0);

        for (FeeComponent comp : feeComponents) {
            monthly = monthly.plus(comp.calculate(req));
        }

        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }
}
