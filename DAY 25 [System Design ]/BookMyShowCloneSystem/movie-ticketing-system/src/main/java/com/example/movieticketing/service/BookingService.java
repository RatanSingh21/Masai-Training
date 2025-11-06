
package com.example.movieticketing.service;

import com.example.movieticketing.model.Booking;
import java.util.List;

public interface BookingService {
    Booking bookSeats(Long userId, Long showId, List<Long> seatIds);
}
