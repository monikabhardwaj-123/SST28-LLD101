public class TransportBookingService {
    private final IDistanceCalculator dist;
    private final IDriverAllocator alloc;
    private final IPaymentGateway pay;
    private final IFareCalculator fareCalc;
    private final IConsoleUi console;

    public TransportBookingService(IDistanceCalculator dist, IDriverAllocator alloc, IPaymentGateway pay,
            IFareCalculator fareCalc, IConsoleUi console) {
        this.dist = dist;
        this.alloc = alloc;
        this.pay = pay;
        this.fareCalc = fareCalc;
        this.console = console;
    }

    public void book(TripRequest req) {
        double km = dist.km(req.from, req.to);
        console.print("DistanceKm=" + km);

        String driver = alloc.allocate(req.studentId);
        console.print("Driver=" + driver);

        double fare = fareCalc.calculateFare(km);

        String txn = pay.charge(req.studentId, fare);
        console.print("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        console.print("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
