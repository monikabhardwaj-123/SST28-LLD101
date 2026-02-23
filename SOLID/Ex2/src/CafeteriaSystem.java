import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoicePrinter printer;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, InvoicePrinter printer) {
        this.store = store;
        this.printer = printer;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(TaxRule taxRule, DiscountRule discountRule, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        List<String> formattedLineItems = new ArrayList<>();

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            formattedLineItems.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double taxPct = taxRule.getTaxPercent();
        double taxAmount = taxRule.calculateTax(subtotal);
        double discountAmount = discountRule.calculateDiscount(subtotal, lines.size());
        double total = subtotal + taxAmount - discountAmount;

        InvoiceReceipt receipt = new InvoiceReceipt(invId, formattedLineItems, subtotal, taxPct, taxAmount,
                discountAmount, total);

        String printable = printer.print(receipt);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
