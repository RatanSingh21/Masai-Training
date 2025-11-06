import java.util.Date;

public class Booking {
    private final String bookingId;
    private final String userId;
    private final String roomId;
    private final Date start;
    private final Date end;
    private String status; // ACTIVE or CANCELLED

    public Booking(String bookingId, String userId, String roomId, Date start, Date end) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.start = start;
        this.end = end;
        this.status = "ACTIVE";
    }

    public String getBookingId() { return bookingId; }
    public String getUserId() { return userId; }
    public String getRoomId() { return roomId; }
    public Date getStart() { return start; }
    public Date getEnd() { return end; }
    public String getStatus() { return status; }
    public void cancel() { this.status = "CANCELLED"; }
}
