
# High-Level Design (HLD) — Movie Ticketing System

## Functional Requirements
- User registration / login (email + password).
- Browse movies by city / theater / date.
- View showtimes for a movie in a selected theater.
- View available seats for a show and select seats.
- Book tickets and receive confirmation.

## Non-Functional Requirements
- Target concurrency: support >= 1000 concurrent users.
- Availability target: 99.9%.
- Browsing/search latency: < 500 ms.
- Data durability: booking data must be persisted reliably.

## High-Level Components
- Clients (Browsers / Mobile)
- Load Balancer
- Web / App Servers (Spring Boot microservice)
- Authentication Service (JWT-based)
- Movie Catalog Service (read-optimized, backed by DB + cache)
- Booking Service (transactional, strong consistency)
- Payment Gateway (mocked)
- PostgreSQL (persistent storage)
- Redis (caching, optional distributed locks / seat cache)

## Architecture Diagram

<img width="3840" height="2318" alt="Untitled diagram _ Mermaid Chart-2025-09-16-152349" src="https://github.com/user-attachments/assets/ff1d6eda-d370-4b70-88c3-4d862250f358" />
<img width="3840" height="2530" alt="Untitled diagram _ Mermaid Chart-2025-09-16-152428" src="https://github.com/user-attachments/assets/227262c3-b1f2-4564-bf42-219b9a8f8819" />
<img width="3840" height="2080" alt="Untitled diagram _ Mermaid Chart-2025-09-16-152517" src="https://github.com/user-attachments/assets/cf4a81d8-d45a-4297-9e4d-84e56be29c1a" />

## Data Flow: Search Movies
1. Client sends GET /movies?city=XYZ
2. App Server -> Movie Catalog Service
3. Movie Catalog reads from Redis cache; on miss reads from Postgres
4. Return movie list to client

## Data Flow: Booking Ticket
1. Client selects seats and calls POST /bookings
2. App Server authenticates user (JWT)
3. Booking Service:
   - Validate seat availability (PESSIMISTIC or optimistic locking).
   - Reserve/update seat row(s) -> create Booking row.
   - Mock Payment (always success).
   - Commit transaction -> return confirmation.

