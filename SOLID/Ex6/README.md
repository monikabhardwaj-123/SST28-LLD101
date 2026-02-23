# Ex6 — LSP: Notification Sender Inheritance

## 1. Context
A campus system sends notifications via email, SMS, and WhatsApp.

## 2. Current behavior
- `NotificationSender.send(Notification)` is the base method
- `EmailSender` silently truncates messages (changes meaning)
- `WhatsAppSender` rejects non-E.164 numbers (tightens precondition)
- `SmsSender` ignores subject but base type implies subject may be used

## 3. What’s wrong (at least 5 issues)
1. Subtypes impose extra constraints not present in base contract.
2. Subtypes change semantics (truncation, ignoring fields).
3. Callers cannot rely on base behavior without knowing subtype.
4. Runtime surprises (exceptions) force subtype-specific handling.
5. Contract is vague and untested; inheritance is misused.

## 4. Your task
- Make substitutability true: if code works with `NotificationSender`, it should work with any sender.
- Preserve current outputs for the sample inputs in `Main`.

## 5. Constraints
- Preserve console output for current demo.
- No external libs.

## 6. Acceptance criteria
- Base contract is clear and upheld.
- No subtype tightens preconditions compared to base.

## 7. How to run
```bash
cd SOLID/Ex6/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Notification Demo ===
EMAIL -> to=riya@sst.edu subject=Welcome body=Hello and welcome to SST!
SMS -> to=9876543210 body=Hello and welcome to SST!
WA ERROR: phone must start with + and country code
AUDIT entries=3
```

## 9. Hints (OOP-only)
- If channels have different requirements, avoid forcing them into a single inherited contract.
- Consider separating validation/normalization as a responsibility.

## 10. Stretch goals
- Add a new sender without editing existing ones.

## 11. Refactoring Resolution (LSP)
The `NotificationSender` hierarchy violated the Liskov Substitution Principle because each subclass mutated the base contract expectations, e.g., silently dropping fields (SMS), silently truncating bodies (Email), and throwing unexpected new Exceptions for validation (WhatsApp). 

Refactoring utilized Composition over Inheritance to formalize these behaviors explicitly:
- Created the **`ChannelAdapter`** interface encompassing `validate(Notification)` and `formatParams(Notification)`.
- Rebuilt **`NotificationSender`** as a concrete template class that rigorously enforces the sequence: validate -> format -> print -> audit. This legally codifies the contract that senders *may* throw exceptions for invalid data and *will* uniquely format requests based on hardware limitations without those behaviors being a "surprise" mutation of the contract.
- The former subclasses `EmailSender`, `SmsSender`, and `WhatsAppSender` now simply inherit the base class and construct it with their specific `ChannelAdapter` implementations. The Liskov Substitution Principle holds because the variations are explicitly parameterized by the unified rules engine in `NotificationSender`.
