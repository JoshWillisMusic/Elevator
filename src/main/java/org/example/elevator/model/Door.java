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
        System.out.println("Opening Door");
        //TODO Delay
        //TODO check for door close
        state = DoorState.OPEN;
        System.out.println("Door Open");
    }

    public void close() {
        if (state == DoorState.CLOSED || state == DoorState.CLOSING) return;
        state = DoorState.CLOSING;
        System.out.println("Closing Door");
        // TODO Delay
        // TODO check if open is called
        // TODO check if there is an obstacle, if so, open the door and wait until no obstacle is detected
        state = DoorState.CLOSED;
        System.out.println("Door Closed");
    }
}

