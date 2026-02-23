import java.util.Map;

public class RoomFeeComponent implements FeeComponent {
    private final Map<Integer, Double> roomPrices;
    private final double defaultPrice;

    public RoomFeeComponent(Map<Integer, Double> roomPrices, double defaultPrice) {
        this.roomPrices = roomPrices;
        this.defaultPrice = defaultPrice;
    }

    @Override
    public Money calculate(BookingRequest req) {
        double price = roomPrices.getOrDefault(req.roomType, defaultPrice);
        return new Money(price);
    }
}
