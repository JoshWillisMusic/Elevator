package org.example.elevator;
import org.example.elevator.Door;

enum State {IDLE, DOOR_CLOSING, ELEVATOR_DOWN, ELEVATOR_UP, MOVING, STOPPING, DOOR_OPENING, AT_FLOOR}
public class Elevator {
    private int currentFloor = 0;
    private int totalFloors = 0;
    private final Door door = new Door();

    public Elevator(int totalFloors, int currentFloor){

    }

    public void moveUp(){

    }
    public void moveDown(){

    }
    public void selectDestinationFloor(){

    }

    // Commands on a specific level that trigger elevator
    public void selectUp(int requestFloor){

    }
    public void selectDown(int requestFloor){

    }

    // commands that happen inside elevator
    public void requestCloseDoor(){

    }
    public void requestOpenDoor(){

    }
    public void selectDestinationFloors(int[] floors){

    }

}
