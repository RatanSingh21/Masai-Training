
package com.example.movieticketing.service.impl;

import com.example.movieticketing.model.*;
import com.example.movieticketing.repository.*;
import com.example.movieticketing.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    private final SeatRepository seatRepo;
    private final BookingRepository bookingRepo;

    public BookingServiceImpl(SeatRepository seatRepo, BookingRepository bookingRepo) {
        this.seatRepo = seatRepo;
        this.bookingRepo = bookingRepo;
    }

    @Override
    @Transactional
    public Booking bookSeats(Long userId, Long showId, List<Long> seatIds) {
        // Pessimistic lock on chosen seats
        List<Seat> seats = seatRepo.findAllByIdForUpdate(seatIds);

        // Validate all seats belong to the show and are AVAILABLE
        for (Seat s : seats) {
            if (!Objects.equals(s.getShowId(), showId)) {
                throw new IllegalArgumentException("Seat does not belong to show");
            }
            if (s.getStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("Seat " + s.getSeatNumber() + " is not available");
            }
        }

        // Mark as BOOKED
        for (Seat s : seats) {
            s.setStatus(SeatStatus.BOOKED);
        }
        seatRepo.saveAll(seats);

        // Create bookings (one per seat)
        Booking last = null;
        for (Seat s : seats) {
            Booking b = new Booking();
            b.setUserId(userId);
            b.setShowId(showId);
            b.setSeatId(s.getSeatId());
            b.setStatus("CONFIRMED");
            last = bookingRepo.save(b);
        }

        return last;
    }
}
