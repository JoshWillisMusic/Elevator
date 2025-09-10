package org.example;

import org.example.elevator.Elevator;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {

        Label status = new Label("Elevator Application");
        Button upButton = new Button("↑");
        Button downButton = new Button("↓");

        VBox request = new VBox(10);
        request.getChildren().addAll(status, upButton, downButton );

        Elevator elevator = new Elevator(6, 3);
        ArrayList<Integer> floors = new ArrayList<>();
       status.setText(String.valueOf(elevator.getCurrentFloor()) + '-' + elevator.getState() + '\n');
        upButton.setOnAction(event -> {
            elevator.requestElevator(2);
            floors.add(3);
            floors.add(4);
            elevator.selectDestinationFloors(floors);
            status.setText(String.valueOf(elevator.getCurrentFloor()) + '-' + elevator.getState() + '\n');
        });
        downButton.setOnAction(event -> {
            elevator.requestElevator(4);
            floors.add(2);
            floors.add(1);
            elevator.selectDestinationFloors(floors);
            status.setText(String.valueOf(elevator.getCurrentFloor()) + '-' + elevator.getState() + '\n');
        });

        // Create a Scene and set it on the Stage
        Scene scene = new Scene(request, 300, 200); // Width and height of the window
        primaryStage.setTitle("Elevator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        ArrayList<Integer> floors = new ArrayList<>();

        Elevator elevator = new Elevator(5, 0);
        elevator.requestElevator(3);
        floors.add(2);
        floors.add(0);
        elevator.selectDestinationFloors(floors);
        floors.remove(0);
        floors.remove(0);
        System.out.println(String.valueOf(elevator.getCurrentFloor()) + '-' + elevator.getState() + '\n');
        elevator.requestElevator(1);
        floors.add(3);
        floors.add(4);
        elevator.selectDestinationFloors(floors);
        System.out.println(String.valueOf(elevator.getCurrentFloor()) + '-' + elevator.getState()+ '\n');
    }
}