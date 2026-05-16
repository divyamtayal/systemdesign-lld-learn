package elevatorsystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import elevatorsystem.enums.Direction;
import elevatorsystem.observer.ElevatorObserver;
import elevatorsystem.state.ElevatorState;
import elevatorsystem.state.IdleState;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor;
    private ElevatorState state;
    private volatile boolean isRunning = true;

    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    private final List<ElevatorObserver> observers = new ArrayList<>();

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
        observer.update(this);
    }

    public void notifyObservers() {
        for (ElevatorObserver observer : observers) {
            observer.update(this);
        }
    }

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
        this.state = new IdleState();
    }

    public void setState(ElevatorState state) {
        this.state = state;
        notifyObservers();
    }

    public void move() {
        state.move(this);
    }

    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + id + " received request: " + request);
        state.addRequest(this, request);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        notifyObservers();
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public TreeSet<Integer> getUpRequests() {
        return upRequests;
    }

    public TreeSet<Integer> getDownRequests() {
        return downRequests;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopElevator() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
