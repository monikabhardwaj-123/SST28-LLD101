public class WhatsAppAdapter implements ChannelAdapter {
    @Override
    public void validate(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
    }

    @Override
    public String formatParams(Notification n) {
        return "WA -> to=" + n.phone + " body=" + n.body;
    }

    @Override
    public String getAuditName() {
        return "wa"; // Or 'WA' depending on what is stored in log? Audit asserts "WA failed" on
                     // throw, but success is "wa sent"?
    }
}
