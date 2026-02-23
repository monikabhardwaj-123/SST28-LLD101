import java.util.List;

public class OnboardingService {
    private final InputParser parser;
    private final StudentValidator validator;
    private final StudentRepository db;
    private final OnboardingPrinter printer;

    public OnboardingService(InputParser parser, StudentValidator validator, StudentRepository db, OnboardingPrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.db = db;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        RegistrationRequest request = parser.parse(raw);
        List<String> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, request.name, request.email, request.phone, request.program);

        db.save(rec);

        printer.printSuccess(rec, db.count());
    }
}
