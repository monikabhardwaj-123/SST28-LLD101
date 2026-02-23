import java.nio.charset.StandardCharsets;

public class CsvEncoder implements FormatEncoder {
    @Override
    public ExportResult encode(ExportRequest req) {
        String body = req.body == null ? "" : req.body;
        // Properly escape CSV by quoting if it contains newlines or commas
        if (body.contains(",") || body.contains("\n")) {
            body = "\"" + body.replace("\"", "\"\"") + "\"";
        }

        String title = req.title == null ? "" : req.title;
        if (title.contains(",") || title.contains("\n")) {
            title = "\"" + title.replace("\"", "\"\"") + "\"";
        }

        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
