
package com.example.movieticketing.controller;

import com.example.movieticketing.model.Booking;
import com.example.movieticketing.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> book(@RequestBody Map<String, Object> body) {
        Long userId = ((Number)body.get("userId")).longValue();
        Long showId = ((Number)body.get("showId")).longValue();
        List<Number> seatsNum = (List<Number>) body.get("seatIds");
        List<Long> seatIds = seatsNum.stream().map(Number::longValue).toList();

        Booking b = bookingService.bookSeats(userId, showId, seatIds);
        return ResponseEntity.ok(Map.of("status","SUCCESS","bookingId", b.getBookingId()));
    }
}
