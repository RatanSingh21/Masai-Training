
import java.util.*;

public class App {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        system.addUser("u1", "Alice");
        system.addRoom("r1", "Room 1", 10);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        Date start = cal.getTime();
        cal.add(Calendar.HOUR, 2);
        Date end = cal.getTime();

        System.out.println("Available rooms: ");
        for (ConferenceRoom r : system.viewAvailableRooms(start, end)) {
            System.out.println(r.getName());
        }

        String bookingId = system.bookRoom("u1", "r1", start, end);
        System.out.println("Booking ID: " + bookingId);

        System.out.println("User bookings: ");
        for (Booking b : system.listUserBookings("u1")) {
            System.out.println(b.getBookingId());
        }

        system.cancelBooking("u1", bookingId);

        System.out.println("User bookings after cancel: ");
        for (Booking b : system.listUserBookings("u1")) {
            System.out.println(b.getBookingId());
        }
    }
}
