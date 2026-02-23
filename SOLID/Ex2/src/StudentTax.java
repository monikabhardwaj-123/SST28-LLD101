public class StudentTax implements TaxRule {
    @Override
    public double getTaxPercent() {
        return 5.0;
    }

    @Override
    public double calculateTax(double subtotal) {
        return subtotal * (5.0 / 100.0);
    }
}
