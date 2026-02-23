public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit, new SmsAdapter());
    }
}
