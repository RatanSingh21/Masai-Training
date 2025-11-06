
package com.example.movieticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "show") // quoted because show is reserved in some DBs
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    private Long movieId;
    private Long theaterId;
    private Instant showTime;
    private Integer screenNo;
}
