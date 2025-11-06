
package com.example.movieticketing.repository;

import com.example.movieticketing.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieIdAndTheaterId(Long movieId, Long theaterId);
}
