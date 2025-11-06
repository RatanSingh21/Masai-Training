import java.util.*;

public class BookingSystem {
    private final Map<String, ConferenceRoom> rooms = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Booking> bookings = new HashMap<>();

    public void addRoom(String roomId, String name, int capacity) {
        if (rooms.containsKey(roomId)) throw new IllegalArgumentException("Room ID exists.");
        rooms.put(roomId, new ConferenceRoom(roomId, name, capacity));
    }

    public void addUser(String userId, String name) {
        if (users.containsKey(userId)) throw new IllegalArgumentException("User ID exists.");
        users.put(userId, new User(userId, name));
    }

    public List<ConferenceRoom> viewAvailableRooms(Date start, Date end) {
        List<ConferenceRoom> available = new ArrayList<>();
        for (ConferenceRoom room : rooms.values()) {
            if (room.isAvailable(start, end)) available.add(room);
        }
        return available;
    }

    public String bookRoom(String userId, String roomId, Date start, Date end) {
        if (!users.containsKey(userId)) throw new IllegalArgumentException("Invalid user ID.");
        if (!rooms.containsKey(roomId)) throw new IllegalArgumentException("Invalid room ID.");
        if (start.before(new Date()) || !end.after(start)) throw new IllegalArgumentException("Invalid time slot.");
        ConferenceRoom room = rooms.get(roomId);
        if (!room.isAvailable(start, end)) throw new IllegalArgumentException("Room not available.");
        String bookingId = roomId + "-" + userId + "-" + start.getTime();
        Booking booking = new Booking(bookingId, userId, roomId, start, end);
        room.addBooking(booking);
        bookings.put(bookingId, booking);
        return bookingId;
    }

    public void cancelBooking(String userId, String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || !booking.getUserId().equals(userId))
            throw new IllegalArgumentException("Booking not found or not owned by user.");
        if (!booking.getStatus().equals("ACTIVE"))
            throw new IllegalArgumentException("Booking already cancelled.");
        if (booking.getStart().before(new Date()))
            throw new IllegalArgumentException("Cannot cancel past bookings.");
        booking.cancel();
    }

    public List<Booking> listUserBookings(String userId) {
        if (!users.containsKey(userId)) throw new IllegalArgumentException("Invalid user ID.");
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings.values()) {
            if (b.getUserId().equals(userId) && b.getStatus().equals("ACTIVE")) result.add(b);
        }
        return result;
    }
}
