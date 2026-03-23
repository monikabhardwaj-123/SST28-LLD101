package models;

public class Slot {
    private String id;
    private int floorId;
    private SlotType type;
    private boolean isAvailable;

    public Slot(String id, int floorId, SlotType type) {
        this.id = id;
        this.floorId = floorId;
        this.type = type;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public int getFloorId() { return floorId; }
    public SlotType getType() { return type; }
    public boolean isAvailable() { return isAvailable; }

    public synchronized boolean reserve() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        isAvailable = true;
    }
}
