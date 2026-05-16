package movingBookingDemo.entities;

import java.time.LocalDateTime;

import movingBookingDemo.strategy.pricing.PricingStrategy;

public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final PricingStrategy pricingStrategy;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime startTime, PricingStrategy pricingStrategy2) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.pricingStrategy = pricingStrategy2;
    }

    public String getShowId() {
        return showId;

    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
