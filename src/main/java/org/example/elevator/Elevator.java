package org.example.elevator;
import org.example.elevator.Door;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

enum ElevatorState {IDLE, PREPARE_UP, PREPARE_DOWN, WAITING, MOVING, STOPPING, AT_FLOOR}
public class Elevator {
    private int currentFloor = 0;
    private int totalFloors = 0;
    private ElevatorState state = ElevatorState.IDLE;
    private ArrayList<Integer> queuedFloors = new ArrayList<>();
    private Queue<Integer> requests = new LinkedList<>();
    private final Door door = new Door();

    public Elevator(int totalFloors, int currentFloor){
        this.currentFloor = currentFloor;
        this.totalFloors = totalFloors;
    }
    public int getTotalFloors(){
        return this.totalFloors;
    }

    public int getCurrentFloor(){
        return this.currentFloor;
    }
    public String getState(){
        return state.name();
    }

    public void elevatorUp(int current, int target){
        if (this.door.isOpen())
            this.door.close();
        this.state = ElevatorState.MOVING;
        for(int i = current; i <= target; i++){
            //Add delay for moving
            this.currentFloor = i;
        }
        this.state = ElevatorState.AT_FLOOR;
        this.door.open();
    }
    public void elevatorDown(int current, int target){
        if (this.door.isOpen())
            this.door.close();
        this.state = ElevatorState.MOVING;
        for(int i = current; i >= target; i--){
            //Add delay for moving
            this.currentFloor = i;
        }
        this.state = ElevatorState.AT_FLOOR;
        this.door.open();
    }

    // Commands on a specific level that trigger elevator
    // To-Do add request up and request down for multi-elevator problem
    public void requestElevator(int requestFloor){
        if(this.currentFloor != requestFloor){
            // move elevator to correct floor
            if(this.currentFloor < requestFloor){
                elevatorUp(this.currentFloor, requestFloor);
            }else {
                elevatorDown(this.currentFloor, requestFloor);
            }
        }
        this.door.open();
        //wait for floors selection
        this.state = ElevatorState.WAITING;
    }
    // commands that happen inside elevator
    public void requestCloseDoor(){
        this.door.close();
    }
    public void requestOpenDoor(){
        this.door.open();
    }
    public void selectDestinationFloors(ArrayList<Integer> floors ){
       for(int floor: floors){
           if(floor > this.currentFloor ){
               elevatorUp(this.currentFloor,floor);
               System.out.println("stopping at - " + String.valueOf(this.currentFloor));
           }
           if(floor < this.currentFloor ){
               elevatorDown(this.currentFloor,floor);
               System.out.println("stopping at - " + String.valueOf(this.currentFloor));
           }
       }
    }

}
