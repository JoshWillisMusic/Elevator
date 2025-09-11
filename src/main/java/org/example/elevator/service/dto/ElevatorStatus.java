package org.example.elevator.service.dto;

import org.example.elevator.model.Elevator;

public record ElevatorStatus(
        int currentFloor,
        String state
) {
    public static ElevatorStatus from(Elevator elevator) {
        return new ElevatorStatus(
                elevator.getCurrentFloor(),
                elevator.getState()
        );
    }
}
