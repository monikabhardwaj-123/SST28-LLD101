public interface TaxRule {
    double getTaxPercent();

    double calculateTax(double subtotal);
}
