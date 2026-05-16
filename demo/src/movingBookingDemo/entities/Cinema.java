package movingBookingDemo.entities;

import java.util.List;

public class Cinema {
    private final String cinemaId;
    private final String name;
    private final City city;
    private final List<Screen> screens;

    public Cinema(String cinemaId, String name, City city, List<Screen> screens) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }
}
