package org.example.elevator.controller;

import jakarta.validation.Valid;
import org.example.elevator.model.Elevator;
import org.example.elevator.service.ElevatorService;
import org.example.elevator.service.dto.ElevatorStatus;
import org.example.elevator.service.dto.RequestElevator;
import org.example.elevator.service.dto.SelectDestinationFloors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ElevatorController {
    private final ElevatorService elevatorService;

    @Autowired
    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @GetMapping("/status")
    public ResponseEntity<ElevatorStatus> getStatus() {
        Elevator elevator = elevatorService.getElevatorStatus();
        if (elevator == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ElevatorStatus.from(elevator));
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestElevator(
            @Valid @RequestBody RequestElevator request
    ) {
        boolean success = elevatorService.requestElevator(request.requestFloor());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Elevator Request Failed");
        }
        Elevator updatedElevator = elevatorService.getElevatorStatus();
        return ResponseEntity.ok(ElevatorStatus.from(updatedElevator));
    }

    @PostMapping("/select_floors")
    public ResponseEntity<?> requestElevator(
            @Valid @RequestBody SelectDestinationFloors request
    ) {
        boolean success = elevatorService.selectDestinationFloors(request.floors());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request to go to floors failed");
        }
        Elevator updatedElevator = elevatorService.getElevatorStatus();
        return ResponseEntity.ok(ElevatorStatus.from(updatedElevator));
    }

}
