import java.util.ArrayList;
import java.util.List;

public class StudentValidator {
    public List<String> validate(RegistrationRequest request) {
        List<String> errors = new ArrayList<>();
        if (request.name.isBlank()) errors.add("name is required");
        if (request.email.isBlank() || !request.email.contains("@")) errors.add("email is invalid");
        if (request.phone.isBlank() || !request.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!(request.program.equals("CSE") || request.program.equals("AI") || request.program.equals("SWE"))) errors.add("program is invalid");
        return errors;
    }
}
