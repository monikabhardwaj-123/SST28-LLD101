package models;
import java.util.Date;

public class Bill {
    private String id;
    private Ticket ticket;
    private Gate exitGate;
    private Date outTime;
    private double amount;

    public Bill(String id, Ticket ticket, Gate exitGate, Date outTime, double amount) {
        this.id = id;
        this.ticket = ticket;
        this.exitGate = exitGate;
        this.outTime = outTime;
        this.amount = amount;
    }

    public String getId() { return id; }
    public Ticket getTicket() { return ticket; }
    public Gate getExitGate() { return exitGate; }
    public Date getOutTime() { return outTime; }
    public double getAmount() { return amount; }
}
