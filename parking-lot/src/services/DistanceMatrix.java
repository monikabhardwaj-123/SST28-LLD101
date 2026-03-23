package services;
import java.util.Map;
import java.util.HashMap;

public class DistanceMatrix {
    private Map<String, Double> matrix;

    public DistanceMatrix() {
        this.matrix = new HashMap<>();
    }

    public void addDistance(String gateId, String slotId, double distance) {
        matrix.put(gateId + "-" + slotId, distance);
    }

    public double getDistance(String gateId, String slotId) {
        return matrix.getOrDefault(gateId + "-" + slotId, Double.MAX_VALUE);
    }
}
