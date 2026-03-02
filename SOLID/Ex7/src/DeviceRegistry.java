import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) {
        devices.add(d);
    }

    public <T> T getFirstImplementing(Class<T> type) {
        for (Object d : devices) {
            if (type.isInstance(d))
                return type.cast(d);
        }
        throw new IllegalStateException("Missing device implementing: " + type.getSimpleName());
    }

    public <T> List<T> getAllImplementing(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Object d : devices) {
            if (type.isInstance(d))
                result.add(type.cast(d));
        }
        return result;
    }
}
