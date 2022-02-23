package Gui;

import Data.Activity;
import Data.Location;
import Data.PrisonGroup;
import Data.Roster;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;

public class ActivityCreator extends Observer{
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity Activity;
    private ErrorPopup errorPopup;
    public ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
    private ArrayList<PrisonGroup> groups = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    private Roster roster;
    private ComboBox<Location> setLocation;
    private ComboBox<PrisonGroup> setGroup;

    public ActivityCreator(Roster roster){
        this.roster = roster;
        this.roster.attach(this);
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
        this.setLocation = new ComboBox<>();
        this.setGroup = new ComboBox<>();
        Spinner setStartTime = new Spinner();
        setStartTime.setEditable(true);
        setLocation.getItems().setAll(roster.getLocationDatabase().values());
        setGroup.getItems().setAll(this.roster.getGroups());

        Spinner setEndTime = new Spinner();
        setEndTime.setEditable(true);

        Button cancel = new Button("Cancel");
        Button add = new Button("Add");


        grid.add(activity, 1, 10);
        grid.add(location, 1, 20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        grid.add(activityName, 2, 10);
        grid.add(setLocation, 2,20);
        grid.add(setGroup, 2, 30);
        grid.add(setStartTime, 2, 40);
        grid.add(setEndTime, 2, 50);
        grid.add(cancel, 2, 80);
        grid.add(add, 1, 80);






        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {


            //creating a timeBlock;
            /**
             * todo: roster is empty, causes NullPointerException
             */
//            timeBlocks.add(new TimeBlock(setGroup.getValue().toString(),setLocation.getValue().toString(),Integer.parseInt(setStartTime.getValue().toString()),Integer.parseInt(setEndTime.getValue().toString()),5));

//            startTime = setStartTime.getValue();
//            Activity newActivity = new Activity(activityName.getText(), setStartTime);
            //TODO, Code missing until adding function is working

            //todo: add if statement to check whether activity overlaps
            this.errorPopup = new ErrorPopup("Overlap with other activities");
            errorPopup.display();
        });




        Scene activityScene = new Scene(grid);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();


        stage.show();
    }
    public ArrayList<TimeBlock> getTimeBlocks(){
        return this.timeBlocks;
    }

    public void update(){
        setLocation.getItems().setAll(this.roster.getLocationDatabase().values());
        setGroup.getItems().setAll(this.roster.getGroups());
    }
}

