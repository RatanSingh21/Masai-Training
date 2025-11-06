
# Low-Level Design (LLD) — Movie Ticketing System

## Database Schema (PostgreSQL)
- user
  - user_id (bigserial PK)
  - name (varchar)
  - email (varchar) UNIQUE
  - password_hash (varchar)
  - created_at (timestamp)

- movie
  - movie_id (bigserial PK)
  - title, language, genre, duration_minutes

- theater
  - theater_id (bigserial PK)
  - name, city

- show
  - show_id (bigserial PK)
  - movie_id FK -> movie(movie_id)
  - theater_id FK -> theater(theater_id)
  - show_time (timestamp)
  - screen_no (int)

- seat
  - seat_id (bigserial PK)
  - show_id FK -> show(show_id)
  - seat_number (varchar)
  - status (ENUM: AVAILABLE, BOOKED, HELD)
  - version (int) -- for optimistic locking
  - UNIQUE (show_id, seat_number)

- booking
  - booking_id (bigserial PK)
  - user_id FK
  - show_id FK
  - seat_id FK
  - status (CONFIRMED, CANCELLED)
  - booking_time (timestamp)

## APIs
- POST /users/register  -> register user
- POST /users/login     -> authenticate (returns a simple token/mock)

- GET /movies?city=xyz  -> list movies in city
- GET /shows?movieId=123&theaterId=456 -> list showtimes
- GET /shows/{id}/seats -> list seats with status
- POST /bookings -> body: { userId, showId, seatIds[] } -> return booking confirmation

## Sequence Diagram (seat booking)
<img width="3840" height="2309" alt="Untitled diagram _ Mermaid Chart-2025-09-16-152546" src="https://github.com/user-attachments/assets/13442165-278d-4b6e-aff6-ce7022927c25" />
## Concurrency Handling
Options:
- **Pessimistic Locking (chosen)**: Acquire a DB row-level lock (SELECT ... FOR UPDATE) on the Seat rows being booked. Implemented via JPA `@Lock(LockModeType.PESSIMISTIC_WRITE)` to prevent concurrent updates during the transaction.
- **Optimistic Locking**: Use `@Version` field and retry on `OptimisticLockException`.
- **Unique Constraint**: Enforce unique (show_id, seat_number) and insert to a reservations table; use constraint violation as an indicator of concurrent booking.

In this implementation, we demonstrate pessimistic locking in the `BookingService` to ensure atomic seat reservation.

