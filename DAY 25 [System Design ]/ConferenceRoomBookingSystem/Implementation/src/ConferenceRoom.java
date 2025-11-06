import java.util.*;

public class ConferenceRoom {
    private final String roomId;
    private final String name;
    private final int capacity;
    private final List<Booking> bookings = new ArrayList<>();

    public ConferenceRoom(String roomId, String name, int capacity) {
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
    }

    public String getRoomId() { return roomId; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public List<Booking> getBookings() { return bookings; }

    public boolean isAvailable(Date start, Date end) {
        for (Booking b : bookings) {
            if (b.getStatus().equals("ACTIVE") &&
                    !(end.compareTo(b.getStart()) <= 0 || start.compareTo(b.getEnd()) >= 0)) {
                return false;
            }
        }
        return true;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
