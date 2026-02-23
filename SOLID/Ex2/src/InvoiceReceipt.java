import java.util.List;

public class InvoiceReceipt {
    public final String invoiceId;
    public final List<String> formattedLineItems;
    public final double subtotal;
    public final double taxPercent;
    public final double taxAmount;
    public final double discountAmount;
    public final double total;

    public InvoiceReceipt(String invoiceId, List<String> formattedLineItems, double subtotal, double taxPercent,
            double taxAmount, double discountAmount, double total) {
        this.invoiceId = invoiceId;
        this.formattedLineItems = formattedLineItems;
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.total = total;
    }
}
