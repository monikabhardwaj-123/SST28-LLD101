import java.util.Map;

public class AddOnFeeComponent implements FeeComponent {
    private final Map<AddOn, Double> addOnPrices;

    public AddOnFeeComponent(Map<AddOn, Double> addOnPrices) {
        this.addOnPrices = addOnPrices;
    }

    @Override
    public Money calculate(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += addOnPrices.getOrDefault(a, 0.0);
        }
        return new Money(total);
    }
}
