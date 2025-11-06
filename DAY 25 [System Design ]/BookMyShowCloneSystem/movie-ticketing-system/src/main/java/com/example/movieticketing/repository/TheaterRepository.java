
package com.example.movieticketing.repository;

import com.example.movieticketing.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCityIgnoreCase(String city);
}
