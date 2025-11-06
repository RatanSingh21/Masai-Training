
package com.example.movieticketing.controller;

import com.example.movieticketing.model.Show;
import com.example.movieticketing.model.Seat;
import com.example.movieticketing.repository.ShowRepository;
import com.example.movieticketing.repository.SeatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowRepository showRepo;
    private final SeatRepository seatRepo;

    public ShowController(ShowRepository showRepo, SeatRepository seatRepo) {
        this.showRepo = showRepo;
        this.seatRepo = seatRepo;
    }

    @GetMapping
    public List<Show> list(@RequestParam Long movieId, @RequestParam Long theaterId) {
        return showRepo.findByMovieIdAndTheaterId(movieId, theaterId);
    }

    @GetMapping("/<built-in function id>/seats")
    public List<Seat> seats(@PathVariable Long id) {
        return seatRepo.findByShowId(id);
    }
}
