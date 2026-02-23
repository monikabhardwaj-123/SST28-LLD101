public class AttendanceRule implements EligibilityRule {
    private final int minimumAttendancePct;

    public AttendanceRule(int minimumAttendancePct) {
        this.minimumAttendancePct = minimumAttendancePct;
    }

    @Override
    public String check(StudentProfile s) {
        if (s.attendancePct < minimumAttendancePct) {
            return "attendance below " + minimumAttendancePct;
        }
        return null;
    }
}
