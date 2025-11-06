
package com.example.movieticketing.controller;

import com.example.movieticketing.model.Movie;
import com.example.movieticketing.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepo;

    public MovieController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public List<Movie> list(@RequestParam(required = false) String city) {
        // For simplicity, ignoring city filter here; would join with theaters in full impl
        return movieRepo.findAll();
    }
}
