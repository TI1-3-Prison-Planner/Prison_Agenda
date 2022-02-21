package Gui;

import Data.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;

public class ActivityCreator {
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity  Activity;
    private ErrorPopup errorPopup;


    public void init(Roster roster) {

    }

    public void display(Stage stage) {

        GridPane grid = new GridPane();
        Stage activityPlanner = new Stage();

        activityPlanner.initModality(Modality.APPLICATION_MODAL);
        activityPlanner.setTitle("Activity Planner");


        Label activity = new Label("Activity:");
        Label location = new Label("Location:");
        Label group = new Label("Group:");
        Label timeStart = new Label("Time start: ");
        Label timeEnd = new Label("Time end: ");

        TextField activityName = new TextField();
        ComboBox setLocation = new ComboBox();
        ComboBox setGroup = new ComboBox();
        Spinner setStartTime = new Spinner();
        Spinner setEndTime = new Spinner();
        Button cancel = new Button("Cancel");
        Button add = new Button("Add");

        grid.add(activity,1,10);
        grid.add(location,1,20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        grid.add(activityName, 2, 10);
        grid.add(setLocation, 2, 20);
        grid.add(setGroup, 2, 30);
        grid.add(setStartTime, 2, 40);
        grid.add(setEndTime,2, 50);
        grid.add(cancel,2, 80);
        grid.add(add,1, 80);

        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {
            this.errorPopup = new ErrorPopup("Overlap with other activities");
            errorPopup.display(stage);
//            startTime = setStartTime.getValue();
//            Activity newActivity = new Activity(activityName.getText(), setStartTime);
            //TODO, Code missing until adding function is working
        });

        Scene activityScene = new Scene(grid, 300, 250);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();


        stage.show();
    }

}
