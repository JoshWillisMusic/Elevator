package org.example.elevator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;


enum ElevatorState {IDLE, PREPARE_MOVE, WAITING, MOVING, STOPPING, AT_FLOOR}


public class Elevator implements Runnable {
    private static Elevator instance;
    protected final Thread elevatorThread;
    private final Door door;
    private final BlockingQueue<Integer> elevatorCallsQueue;
    private final BlockingQueue<Integer> selectDestinationFloorsQueue;
    private int currentFloor = 0;
    private int totalFloors = 0;
    private ElevatorState state = ElevatorState.IDLE;

    private Elevator(int totalFloors, int currentFloor) {
        this.currentFloor = currentFloor;
        this.totalFloors = totalFloors;
        this.elevatorThread = new Thread(this);
        this.door = new Door(this.elevatorThread);
        this.elevatorCallsQueue = new java.util.concurrent.ArrayBlockingQueue<>(10);
        this.selectDestinationFloorsQueue = new java.util.concurrent.ArrayBlockingQueue<>(10);
        this.elevatorThread.start();
    }

    public static synchronized Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator(10, 0);
        }
        return instance;
    }

    @Override
    public void run() {
        while (true) {
            try {
                switch (state) {
                    case IDLE: {
                        Integer result = elevatorCallsQueue.take();
                        processElevatorRequest(result);
                        break;
                    }
                    case WAITING: {
                        Integer result = selectDestinationFloorsQueue.poll(10000, java.util.concurrent.TimeUnit.MILLISECONDS);
                        if (result == null) {
                            System.out.println("No Floor Selection Received");
                            this.door.close();
                            this.state = ElevatorState.IDLE;
                            continue;
                        }
                        elevatorMove(result);
                        if (selectDestinationFloorsQueue.isEmpty()) {
                            this.door.close();
                            this.state = ElevatorState.IDLE;
                        } else {
                            this.state = ElevatorState.WAITING;
                        }
                        break;
                    }
                    // Add Case for MOVING state with Elevator Call Along the Way
                }
            } catch (InterruptedException e) {
                elevatorThread.interrupt();
            }
        }
    }

    public void elevatorProcessingDelay(int delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            this.elevatorThread.interrupt();
        }
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public String getState() {
        return state.name();
    }

    private void elevatorMove(int target) {
        this.state = ElevatorState.PREPARE_MOVE;
        System.out.println(this.state.name());
        if (target > this.totalFloors || target < 0)
            return;
        if (this.currentFloor != target) {
            if (this.door.isOpen())
                this.door.close();
            this.state = ElevatorState.MOVING;
            System.out.println(this.state.name());

            System.out.println("Moving Elevator from " + currentFloor + " to " + target);
            if (currentFloor < target) {
                // Move Up
                moveUp(target);
            } else if (currentFloor > target) {
                // Move Down
                moveDown(target);
            }
            this.state = ElevatorState.AT_FLOOR;
            System.out.println(this.state.name() + " - " + currentFloor);
            this.door.open();
            // wait for users to enter or exit elevator
            elevatorProcessingDelay(3000);
        }
    }

    private void moveUp(int target) {
        for (int i = currentFloor; i <= target; i++) {
            if (i == target) {
                this.state = ElevatorState.STOPPING;
                System.out.println(this.state.name());
            }
            elevatorProcessingDelay(3000);
            currentFloor = i;
            System.out.println("Current Floor - " + currentFloor);
        }
    }

    private void moveDown(int target) {
        for (int i = currentFloor; i >= target; i--) {
            if (i == target) {
                this.state = ElevatorState.STOPPING;
                System.out.println(this.state.name());
            }
            elevatorProcessingDelay(3000);
            currentFloor = i;
            System.out.println("Current Floor - " + currentFloor);
        }
    }

    private void processElevatorRequest(int requestFloor) {
        System.out.println("Processing Request - " + requestFloor);
        elevatorMove(requestFloor);
        this.state = ElevatorState.WAITING;
        System.out.println(this.state.name());
    }

    public boolean requestElevator(int requestFloor) {
        System.out.println("Elevator Requested - " + requestFloor);
        return this.elevatorCallsQueue.offer(requestFloor);
    }

    public boolean selectDestinationFloors(ArrayList<Integer> floors) {
        System.out.println("Setting Destination Floors" + floors.toString());
        if (this.currentFloor < Collections.min(floors)) {
            floors.sort(Comparator.naturalOrder());
        } else if (this.currentFloor > Collections.max(floors)) {
            floors.sort(Comparator.reverseOrder());
        }
        boolean success = true;
        for (int floor : floors) {
            boolean result = this.selectDestinationFloorsQueue.offer(floor);
            success = success && result;
        }
        return success;
    }


}
