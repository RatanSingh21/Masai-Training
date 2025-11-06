
package com.example.movieticketing.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private String language;
    private String genre;
    private Integer durationMinutes;
}
