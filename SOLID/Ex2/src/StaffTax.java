public class StaffTax implements TaxRule {
    @Override
    public double getTaxPercent() {
        return 2.0;
    }

    @Override
    public double calculateTax(double subtotal) {
        return subtotal * (2.0 / 100.0);
    }
}
