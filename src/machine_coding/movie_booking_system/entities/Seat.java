package machine_coding.movie_booking_system.entities;

import machine_coding.movie_booking_system.enums.SeatStatus;
import machine_coding.movie_booking_system.enums.SeatType;

public class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final SeatType seatType;
    private SeatStatus seatStatus;

    public Seat(String seatId, int row, int col, SeatType seatType) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}
