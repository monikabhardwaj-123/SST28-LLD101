import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        Map<Integer, Double> roomPrices = Map.of(
                LegacyRoomTypes.SINGLE, 14000.0,
                LegacyRoomTypes.DOUBLE, 15000.0,
                LegacyRoomTypes.TRIPLE, 12000.0,
                LegacyRoomTypes.DELUXE, 16000.0);

        Map<AddOn, Double> addOnPrices = Map.of(
                AddOn.MESS, 1000.0,
                AddOn.LAUNDRY, 500.0,
                AddOn.GYM, 300.0);

        List<FeeComponent> feeComponents = List.of(
                new RoomFeeComponent(roomPrices, 16000.0),
                new AddOnFeeComponent(addOnPrices));

        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), feeComponents);
        calc.process(req);
    }
}
