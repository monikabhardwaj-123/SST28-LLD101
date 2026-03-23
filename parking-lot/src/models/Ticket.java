package models;
import java.util.Date;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private Slot slot;
    private Gate entryGate;
    private Date inTime;

    public Ticket(String id, Vehicle vehicle, Slot slot, Gate entryGate, Date inTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryGate = entryGate;
        this.inTime = inTime;
    }

    public String getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public Slot getSlot() { return slot; }
    public Gate getEntryGate() { return entryGate; }
    public Date getInTime() { return inTime; }
}
