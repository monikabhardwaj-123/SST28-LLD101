package entities;

public class Snake extends BoardEntity {
    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) {
            throw new IllegalArgumentException("Snake start must be greater than end");
        }
    }

    @Override
    public String getEncounterMessage() {
        return "bitten by a snake at " + getStart() + ", going down to " + getEnd();
    }
}
