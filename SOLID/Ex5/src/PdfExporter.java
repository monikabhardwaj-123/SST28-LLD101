public class PdfExporter extends Exporter {
    public PdfExporter() {
        super(new PdfEncoder(), new MaxLengthConstraint(20));
    }
}
