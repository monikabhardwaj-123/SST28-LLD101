package services;

import models.*;
import strategies.SlotAssignmentStrategy;
import strategies.FeeCalculationStrategy;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public class ParkingLotService {
    private List<Slot> slots;
    private SlotAssignmentStrategy slotStrategy;
    private FeeCalculationStrategy feeStrategy;

    public ParkingLotService(List<Slot> slots, SlotAssignmentStrategy slotStrategy, FeeCalculationStrategy feeStrategy) {
        this.slots = slots;
        this.slotStrategy = slotStrategy;
        this.feeStrategy = feeStrategy;
    }

    public Ticket generateParkingTicket(Vehicle vehicle, Date inTime, SlotType requestedSlotSize, Gate entryGate) {
        while (true) {
            Slot slot = slotStrategy.getSlot(entryGate, requestedSlotSize, slots);
            if (slot == null) {
                System.out.println("Parking Full for type " + requestedSlotSize);
                return null;
            }

            if (slot.reserve()) {
                Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, slot, entryGate, inTime);
                System.out.println("Ticket generated: " + ticket.getId() + " at slot " + slot.getId());
                return ticket;
            }
            // Failed to reserve due to concurrent reservation. Loops to find the NEXT nearest.
        }
    }

    public Bill generateBill(Ticket ticket, Gate exitGate, Date outTime) {
        Bill bill = feeStrategy.generateBill(ticket, exitGate, outTime);
        ticket.getSlot().release();
        System.out.println("Bill generated for Rs. " + bill.getAmount() + ", slot " + ticket.getSlot().getId() + " released.");
        return bill;
    }

    public void showStatus() {
        int small = 0, medium = 0, large = 0;
        for (Slot s : slots) {
            if (s.isAvailable()) {
                switch(s.getType()) {
                    case SMALL: small++; break;
                    case MEDIUM: medium++; break;
                    case LARGE: large++; break;
                }
            }
        }
        System.out.println(String.format("Available Slots -> SMALL: %d, MEDIUM: %d, LARGE: %d", small, medium, large));
    }
}
