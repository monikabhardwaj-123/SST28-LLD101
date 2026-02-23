public class SmsAdapter implements ChannelAdapter {
    @Override
    public void validate(Notification n) {
        // No specific constraint defined for SMS
    }

    @Override
    public String formatParams(Notification n) {
        return "SMS -> to=" + n.phone + " body=" + n.body;
    }

    @Override
    public String getAuditName() {
        return "sms";
    }
}
