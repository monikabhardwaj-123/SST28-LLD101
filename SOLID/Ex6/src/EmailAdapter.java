public class EmailAdapter implements ChannelAdapter {
    @Override
    public void validate(Notification n) {
        // No strict channel constraints for email in this system
    }

    @Override
    public String formatParams(Notification n) {
        String body = n.body;
        if (body.length() > 40)
            body = body.substring(0, 40);
        return "EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body;
    }

    @Override
    public String getAuditName() {
        return "email";
    }
}
