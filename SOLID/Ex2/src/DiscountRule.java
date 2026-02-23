public interface DiscountRule {
    double calculateDiscount(double subtotal, int distinctLines);
}
