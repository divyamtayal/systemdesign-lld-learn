package machine_coding.elevator_system.entities;

import machine_coding.elevator_system.enums.Direction;
import machine_coding.elevator_system.enums.RequestSource;

public class Request {
    private final int targetFloor;
    private final Direction direction;
    private final RequestSource source;

    public Request(int targetFloor, Direction direction, RequestSource source) {
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.source = source;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Request{" +
                "targetFloor=" + targetFloor +
                ", direction=" + direction +
                ", source=" + source +
                '}';
    }

}
