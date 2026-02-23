public class CsvExporter extends Exporter {
    public CsvExporter() {
        super(new CsvEncoder(), new NoOpConstraint());
    }
}
