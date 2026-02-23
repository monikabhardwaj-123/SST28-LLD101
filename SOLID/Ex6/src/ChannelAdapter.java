public interface ChannelAdapter {
    /**
     * @throws IllegalArgumentException if the notification is invalid for this
     *                                  channel.
     */
    void validate(Notification n);

    String formatParams(Notification n);

    String getAuditName();
}
