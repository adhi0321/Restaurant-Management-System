package com.rest.models;

public class Table {
    public enum Availability {
        FREE, OCCUPIED
    }

    private final int id;
    private final int capacity;
    private Availability status;

    public Table(int id, int capacity) {
        if (id <= 0) {
            throw new IllegalArgumentException("Table ID must be positive.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Table capacity must be positive.");
        }
        this.id = id;
        this.capacity = capacity;
        this.status = Availability.FREE;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Availability getStatus() {
        return status;
    }

    public void freeTable() {
        status = Availability.FREE;
    }

    public void occupyTable() {
        status = Availability.OCCUPIED;
    }

    @Override
    public String toString() {
        return String.format("Table ID: %d | Capacity: %d | Status: %s",
                id, capacity, status);
    }
}
