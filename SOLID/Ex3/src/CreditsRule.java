public class CreditsRule implements EligibilityRule {
    private final int minimumCredits;

    public CreditsRule(int minimumCredits) {
        this.minimumCredits = minimumCredits;
    }

    @Override
    public String check(StudentProfile s) {
        if (s.earnedCredits < minimumCredits) {
            return "credits below " + minimumCredits;
        }
        return null;
    }
}
