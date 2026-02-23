public class JsonExporter extends Exporter {
    public JsonExporter() {
        super(new JsonEncoder(), new NoOpConstraint());
    }
}
