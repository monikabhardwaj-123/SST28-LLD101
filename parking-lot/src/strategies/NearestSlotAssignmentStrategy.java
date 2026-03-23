package strategies;
import models.SlotType;
import models.Gate;
import models.Slot;
import services.DistanceMatrix;
import java.util.List;

public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {
    private DistanceMatrix distanceMatrix;

    public NearestSlotAssignmentStrategy(DistanceMatrix distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    @Override
    public Slot getSlot(Gate gate, SlotType type, List<Slot> slots) {
        Slot nearestSlot = null;
        double minDistance = Double.MAX_VALUE;

        for (Slot slot : slots) {
            if (slot.getType() == type && slot.isAvailable()) {
                double distance = distanceMatrix.getDistance(gate.getId(), slot.getId());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestSlot = slot;
                }
            }
        }
        return nearestSlot;
    }
}
