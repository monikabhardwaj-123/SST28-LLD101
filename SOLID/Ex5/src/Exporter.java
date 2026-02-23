public abstract class Exporter {
    private final FormatEncoder encoder;
    private final ExportConstraint constraint;

    protected Exporter(FormatEncoder encoder, ExportConstraint constraint) {
        this.encoder = encoder;
        this.constraint = constraint;
    }

    // Explicit contract enforced for all sub-types
    public final ExportResult export(ExportRequest req) {
        if (req == null)
            throw new IllegalArgumentException("Request cannot be null");
        if (req.title == null || req.body == null)
            throw new IllegalArgumentException("Title and body cannot be null");

        constraint.validate(req);
        return encoder.encode(req);
    }
}
