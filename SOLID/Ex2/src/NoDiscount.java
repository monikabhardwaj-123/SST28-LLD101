public class NoDiscount implements DiscountRule {
    @Override
    public double calculateDiscount(double subtotal, int distinctLines) {
        return 0.0;
    }
}
