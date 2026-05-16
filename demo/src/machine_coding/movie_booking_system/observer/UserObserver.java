package machine_coding.movie_booking_system.observer;

import machine_coding.movie_booking_system.entities.Movie;
import machine_coding.movie_booking_system.entities.User;

public class UserObserver implements MovieObserver {
    private final User user;
    private Movie movie;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Movie movie) {
        this.movie = movie;
        System.out.println("User " + user.getName() + " notified about movie: " + movie.getTitle());
    }

    @Override
    public Movie getMovie() {
        // TODO Auto-generated method stub
        return movie;
    }
}