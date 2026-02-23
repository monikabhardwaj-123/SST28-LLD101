public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit, new EmailAdapter());
    }
}
