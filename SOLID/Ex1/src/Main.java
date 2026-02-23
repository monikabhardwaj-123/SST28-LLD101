public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        
        InputParser parser = new InputParser();
        StudentValidator validator = new StudentValidator();
        StudentRepository db = new FakeDb();
        OnboardingPrinter printer = new OnboardingPrinter();

        OnboardingService svc = new OnboardingService(parser, validator, db, printer);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        // Stretch goal: Add a second input example that fails validation
        // System.out.println("\n--- Second Example (Fail) ---");
        // String rawFail = "name=;email=bad;phone=abc;program=XYZ";
        // svc.registerFromRawInput(rawFail);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
