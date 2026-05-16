package machine_coding.movie_booking_system.entities;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenId;
    private final List<Seat> capacity;

    public Screen(String screenId) {
        this.screenId = screenId;
        this.capacity = new ArrayList<>();
    }

    public String getScreenId() {
        return screenId;
    }

    public List<Seat> getCapacity() {
        return capacity;
    }

    public void addSeat(Seat seat) {
        capacity.add(seat);
    }
}
