package org.example.elevator.model;

import java.util.ArrayList;


enum ElevatorState {IDLE, PREPARE_UP, PREPARE_DOWN, WAITING, MOVING, STOPPING, AT_FLOOR}


public class Elevator {
    private static Elevator instance;
    private final Door door = new Door();
    private int currentFloor = 0;
    private int totalFloors = 0;
    private ElevatorState state = ElevatorState.IDLE;

    private Elevator(int totalFloors, int currentFloor) {
        this.currentFloor = currentFloor;
        this.totalFloors = totalFloors;
    }

    public static synchronized Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator(10, 0);
        }
        return instance;
    }

    public int getTotalFloors() {
        return this.totalFloors;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public String getState() {
        return state.name();
    }

    private void elevatorMove(int current, int target) {
        if (this.door.isOpen())
            this.door.close();
        this.state = ElevatorState.MOVING;
        System.out.println("Moving Elevator from " + current + " to " + target);
        for (int i = current; i <= target; i++) {
            //Add delay for moving
            this.currentFloor = i;
            System.out.println("Current Floor - " + this.currentFloor);
        }
        this.state = ElevatorState.AT_FLOOR;
        System.out.println("Elevator at Floor - " + this.currentFloor);
        this.door.open();
    }

    private void elevatorUp(int current, int target) {
        this.state = ElevatorState.PREPARE_UP;
        elevatorMove(current, target);
    }

    private void elevatorDown(int current, int target) {
        this.state = ElevatorState.PREPARE_DOWN;
        elevatorMove(current, target);
    }

    // Commands on a specific level that trigger elevator
    // To-Do add request up and request down for multi-elevator problem
    public boolean requestElevator(int requestFloor) {
        System.out.println("Requesting Elevator to go to floor - " + requestFloor);
        if (requestFloor > this.totalFloors || requestFloor < 0)
            return false;
        if (this.currentFloor != requestFloor) {
            // move elevator to correct floor
            if (this.currentFloor < requestFloor) {
                elevatorUp(this.currentFloor, requestFloor);
            } else {
                elevatorDown(this.currentFloor, requestFloor);
            }
        }
        this.door.open();
        System.out.println("Elevator is waiting for floors selection");
        this.state = ElevatorState.WAITING;
        return true;
    }

    // commands that happen inside elevator
    public boolean selectDestinationFloors(ArrayList<Integer> floors) {
        // sort the list of floors so that elevator makes stops at the right floors
        for (int floor : floors) {
            if (floor > this.totalFloors || floor < 0) {
                System.out.println("Invalid Floor");
            } else if (floor > this.currentFloor) {
                elevatorUp(this.currentFloor, floor);
            } else {
                elevatorDown(this.currentFloor, floor);
            }
        }
        this.door.close();
        this.state = ElevatorState.IDLE;
        return true;
    }

}
