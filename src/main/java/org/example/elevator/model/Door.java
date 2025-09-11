package org.example.elevator.model;

enum DoorState {
    OPEN, OPENING, CLOSED, CLOSING
}

final class Door {
    private DoorState state = DoorState.CLOSED;

    public DoorState state() {
        return state;
    }

    public boolean isOpen() {
        return state == DoorState.OPEN;
    }

    public boolean isClosed() {
        return state == DoorState.CLOSED;
    }

    public void open() {
        if (state == DoorState.OPEN || state == DoorState.OPENING) return;
        state = DoorState.OPENING;
        // Delay
        // check for door close
        state = DoorState.OPEN;
    }

    public void close() {
        if (state == DoorState.CLOSED || state == DoorState.CLOSING) return;
        state = DoorState.CLOSING;
        // Delay
        // check if open is called
        // check if there is an obstacle
        state = DoorState.CLOSED;
    }
}

