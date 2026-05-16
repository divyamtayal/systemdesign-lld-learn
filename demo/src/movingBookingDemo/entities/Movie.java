package movingBookingDemo.entities;

public class Movie {
    private final String movieId;
    private final String title;
    private final int duration;

    public Movie(String movieId, String title, int duration) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}
