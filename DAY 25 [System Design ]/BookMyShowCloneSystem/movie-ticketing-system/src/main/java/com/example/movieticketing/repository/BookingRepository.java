
package com.example.movieticketing.repository;

import com.example.movieticketing.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
