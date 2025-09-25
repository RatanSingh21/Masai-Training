// src/main/java/in/ratansgh/Assignment_LMS/controller/ReservationController.java
package in.ratansgh.Assignment_LMS.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private static final List<Map<String, Object>> reservations = new ArrayList<>();

    static {
        reservations.add(new HashMap<>(Map.of(
                "id", 1L,
                "bookId", 101L,
                "user", "student",
                "status", "PENDING"
        )));
        reservations.add(new HashMap<>(Map.of(
                "id", 2L,
                "bookId", 102L,
                "user", "student",
                "status", "APPROVED"
        )));
    }

    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    @GetMapping
    public List<Map<String, Object>> getAllReservations() {
        return reservations;
    }

    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    @PostMapping("/{id}/approve")
    public String approveReservation(@PathVariable Long id, @RequestParam boolean approve) {
        for (Map<String, Object> reservation : reservations) {
            if (reservation.get("id").equals(id)) {
                reservation.put("status", approve ? "APPROVED" : "REJECTED");
                return "Reservation " + id + " " + (approve ? "approved" : "rejected");
            }
        }
        return "Reservation not found";
    }
}
