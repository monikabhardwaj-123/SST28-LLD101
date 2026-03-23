package entities;

public class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException("Ladder start must be less than end");
        }
    }

    @Override
    public String getEncounterMessage() {
        return "climbed a ladder at " + getStart() + ", going up to " + getEnd();
    }
}
