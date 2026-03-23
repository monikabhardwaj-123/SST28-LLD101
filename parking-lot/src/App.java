import models.*;
import services.*;
import strategies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Booting Multi-Level Parking Lot ---");

        DistanceMatrix matrix = new DistanceMatrix();
        
        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot("F1-S1", 1, SlotType.SMALL));
        slots.add(new Slot("F1-M1", 1, SlotType.MEDIUM));
        slots.add(new Slot("F1-L1", 1, SlotType.LARGE));
        slots.add(new Slot("F2-S1", 2, SlotType.SMALL));
        slots.add(new Slot("F2-M1", 2, SlotType.MEDIUM));

        Gate g1 = new Gate("G1", 1, GateType.ENTRY);
        Gate g2 = new Gate("G2", 2, GateType.ENTRY);
        Gate exit1 = new Gate("EXIT1", 1, GateType.EXIT);

        matrix.addDistance("G1", "F1-S1", 10.0);
        matrix.addDistance("G1", "F1-M1", 20.0);
        matrix.addDistance("G1", "F1-L1", 30.0);
        matrix.addDistance("G1", "F2-S1", 100.0); 
        matrix.addDistance("G1", "F2-M1", 120.0);

        matrix.addDistance("G2", "F2-S1", 10.0);
        matrix.addDistance("G2", "F2-M1", 20.0);
        matrix.addDistance("G2", "F1-S1", 100.0);
        matrix.addDistance("G2", "F1-M1", 110.0);
        matrix.addDistance("G2", "F1-L1", 120.0);

        SlotAssignmentStrategy nearestStrategy = new NearestSlotAssignmentStrategy(matrix);
        FeeCalculationStrategy feeStrategy = new DynamicDurationFeeStrategy();

        ParkingLotService parkingLot = new ParkingLotService(slots, nearestStrategy, feeStrategy);

        parkingLot.showStatus();

        System.out.println("\n--- Simulating 3 Concurrent Small Vehicles Entering G1 ---");
        
        Runnable task1 = () -> {
            Vehicle v1 = new Vehicle("C-111", "Red", "Honda", VehicleType.TWO_WHEELER);
            parkingLot.generateParkingTicket(v1, new Date(), SlotType.SMALL, g1);
        };

        Runnable task2 = () -> {
            Vehicle v2 = new Vehicle("C-222", "Blue", "Yamaha", VehicleType.TWO_WHEELER);
            parkingLot.generateParkingTicket(v2, new Date(), SlotType.SMALL, g1);
        };

        Runnable task3 = () -> {
            Vehicle v3 = new Vehicle("C-333", "Black", "Suzuki", VehicleType.TWO_WHEELER);
            parkingLot.generateParkingTicket(v3, new Date(), SlotType.SMALL, g1); 
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStatus after concurrent entries:");
        parkingLot.showStatus();

        System.out.println("\n--- Processing Medium Car Standard Lifecycle ---");
        Vehicle car = new Vehicle("CAR-001", "White", "Toyota", VehicleType.CAR);
        // Simulate entry of exactly 125 mins ago (2 hours and 5 minutes -> bills for 3 hours)
        long oldTimeMillis = System.currentTimeMillis() - (1000L * 60 * 125); 
        Ticket t = parkingLot.generateParkingTicket(car, new Date(oldTimeMillis), SlotType.MEDIUM, g2); 
        
        if (t != null) {
            parkingLot.generateBill(t, exit1, new Date());
        }

        System.out.println("\nFinal Status:");
        parkingLot.showStatus();
    }
}
