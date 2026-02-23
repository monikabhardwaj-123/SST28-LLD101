public class MaxLengthConstraint implements ExportConstraint {
    private final int maxLength;

    public MaxLengthConstraint(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void validate(ExportRequest req) {
        if (req.body.length() > maxLength) {
            throw new IllegalArgumentException("PDF cannot handle content > " + maxLength + " chars");
        }
    }
}
