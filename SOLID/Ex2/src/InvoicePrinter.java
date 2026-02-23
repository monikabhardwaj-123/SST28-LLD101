public class InvoicePrinter {
    public String print(InvoiceReceipt receipt) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(receipt.invoiceId).append("\n");

        for (String lineItem : receipt.formattedLineItems) {
            out.append(lineItem).append("\n");
        }

        out.append(String.format("Subtotal: %.2f\n", receipt.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", receipt.taxPercent, receipt.taxAmount));
        out.append(String.format("Discount: -%.2f\n", receipt.discountAmount));
        out.append(String.format("TOTAL: %.2f\n", receipt.total));

        return out.toString();
    }
}
