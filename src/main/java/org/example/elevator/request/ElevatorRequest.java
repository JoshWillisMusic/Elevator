package org.example.elevator.request;
enum Direction{
    UP, DOWN
}
public record ElevatorRequest(int requestFloor, Direction requestDirection) {

}
