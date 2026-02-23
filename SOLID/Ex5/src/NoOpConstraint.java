public class NoOpConstraint implements ExportConstraint {
    @Override
    public void validate(ExportRequest req) {
        // No constraints
    }
}
