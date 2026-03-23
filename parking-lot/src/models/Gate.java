package models;

public class Gate {
    private String id;
    private int floorId;
    private GateType type;

    public Gate(String id, int floorId, GateType type) {
        this.id = id;
        this.floorId = floorId;
        this.type = type;
    }

    public String getId() { return id; }
    public int getFloorId() { return floorId; }
    public GateType getType() { return type; }
}
