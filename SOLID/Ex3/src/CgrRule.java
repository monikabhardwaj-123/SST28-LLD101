public class CgrRule implements EligibilityRule {
    private final double threshold;

    public CgrRule(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String check(StudentProfile s) {
        if (s.cgr < threshold) {
            return "CGR below " + threshold;
        }
        return null;
    }
}
