package machine_coding.movie_booking_system.observer;

import machine_coding.movie_booking_system.entities.Movie;

public interface MovieObserver {
    void update(Movie movie);

    Movie getMovie();
}