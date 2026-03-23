package strategies;
import models.SlotType;
import models.Gate;
import models.Slot;
import java.util.List;

public interface SlotAssignmentStrategy {
    Slot getSlot(Gate gate, SlotType type, List<Slot> slots);
}
