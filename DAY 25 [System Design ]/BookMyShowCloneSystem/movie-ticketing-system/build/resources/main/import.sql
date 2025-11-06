
-- sample data
INSERT INTO movie (movie_id, title, language, genre, duration_minutes) VALUES (1, 'Matrix Resurrections', 'English', 'Sci-Fi', 148);
INSERT INTO theater (theater_id, name, city) VALUES (1, 'PVR Phoenix', 'Mumbai');
INSERT INTO "show" (show_id, movie_id, theater_id, show_time, screen_no) VALUES (1, 1, 1, now() + interval '1 day', 1);

-- create seats for show 1
INSERT INTO seat (seat_id, show_id, seat_number, status, version) VALUES (1, 1, 'A1', 'AVAILABLE', 0);
INSERT INTO seat (seat_id, show_id, seat_number, status, version) VALUES (2, 1, 'A2', 'AVAILABLE', 0);
INSERT INTO seat (seat_id, show_id, seat_number, status, version) VALUES (3, 1, 'A3', 'AVAILABLE', 0);
