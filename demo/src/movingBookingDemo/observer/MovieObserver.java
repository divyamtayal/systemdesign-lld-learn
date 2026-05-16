package movingBookingDemo.observer;

import movingBookingDemo.entities.Movie;

public interface MovieObserver {
    void update(Movie movie);

    Movie getMovie();
}