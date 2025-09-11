package org.example.elevator.service;

import org.example.elevator.model.Elevator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ElevatorService {
    private final Elevator elevator;

    public ElevatorService() {
        // Initialize one elevator for this example
        this.elevator = new Elevator(10, 0);
    }

    public Elevator getElevatorStatus() {
        return this.elevator;
    }

    public boolean requestElevator(int requestFloor) {
        return this.elevator.requestElevator(requestFloor);
    }

    public boolean selectDestinationFloors(ArrayList<Integer> floors) {
        return this.elevator.selectDestinationFloors(floors);
    }
}
