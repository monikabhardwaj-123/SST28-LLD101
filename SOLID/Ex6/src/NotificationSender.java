public class NotificationSender {
    protected final AuditLog audit;
    private final ChannelAdapter adapter;

    public NotificationSender(AuditLog audit, ChannelAdapter adapter) {
        this.audit = audit;
        this.adapter = adapter;
    }

    public final void send(Notification n) {
        adapter.validate(n);
        String formattedData = adapter.formatParams(n);

        System.out.println(formattedData);

        audit.add(adapter.getAuditName() + " sent");
    }
}
