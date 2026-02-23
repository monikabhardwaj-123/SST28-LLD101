public class DefaultTax implements TaxRule {
    @Override
    public double getTaxPercent() {
        return 8.0;
    }

    @Override
    public double calculateTax(double subtotal) {
        return subtotal * (8.0 / 100.0);
    }
}
