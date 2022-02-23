package Gui;

import Data.Activity;
import Data.Location;
import Data.PrisonGroup;
import Data.Roster;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;

public class ActivityCreator extends Observer {
    private ErrorPopup errorPopup;
    public ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
    private ArrayList<PrisonGroup> groups = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    private Roster roster;
    private ComboBox<Location> setLocation;
    private ComboBox<PrisonGroup> setGroup;
    private Spinner<LocalTime> setStartTime;
    private Spinner<LocalTime> setEndTime;
    private TextField activityName;
    private Stage activityPlanner;
    public ActivityCreator(Roster roster) {
        this.roster = roster;
        this.roster.attach(this);
    }

    public void display() {
        this.errorPopup = new ErrorPopup("");
        GridPane grid = new GridPane();
        this.activityPlanner = new Stage();

        activityPlanner.initModality(Modality.APPLICATION_MODAL);
        activityPlanner.setTitle("Activity Planner");


        Label activity = new Label("Activity:");
        Label location = new Label("Location:");
        Label group = new Label("Group:");
        Label timeStart = new Label("Time start: ");
        Label timeEnd = new Label("Time end: ");

        this.activityName = new TextField();
        this.setLocation = new ComboBox<>();
        this.setGroup = new ComboBox<>();
        this.setStartTime = new Spinner<>();
        this.setStartTime.setEditable(true);
        setLocation.getItems().setAll(roster.getLocationDatabase().values());
        setGroup.getItems().setAll(this.roster.getGroups());


        this.setEndTime = new Spinner<>();
        setEndTime.setEditable(true);

        Button cancel = new Button("Cancel");
        Button add = new Button("Add");


        grid.add(activity, 1, 10);
        grid.add(location, 1, 20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        grid.add(activityName, 2, 10);
        grid.add(setLocation, 2, 20);
        grid.add(setGroup, 2, 30);
        grid.add(setStartTime, 2, 40);
        grid.add(setEndTime, 2, 50);
        grid.add(cancel, 2, 80);
        grid.add(add, 1, 80);


        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {
            addActivity();
            close();
            //creating a timeBlock;

//            timeBlocks.add(new TimeBlock(setGroup.getValue().toString(),setLocation.getValue().toString(),Integer.parseInt(setStartTime.getValue().toString()),Integer.parseInt(setEndTime.getValue().toString()),5));

//            startTime = setStartTime.getValue();
//            Activity newActivity = new Activity(activityName.getText(), setStartTime);

        });


        Scene activityScene = new Scene(grid);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();

    }

    private void close() {
        activityName.clear();
        setLocation.getSelectionModel().selectFirst();
        setGroup.getSelectionModel().selectFirst();
        activityPlanner.close();
    }

    private void addActivity() {
        if (isOverlapping()) {
            this.errorPopup.setErrorMessage("Overlap with other activities");
            errorPopup.display();
        } else {
            this.roster.getActivities().add(new Activity(activityName.getText(),setStartTime.getValue(),setEndTime.getValue(),setGroup.getValue(),setLocation.getValue()));
            this.roster.notifyObservers();
        }
    }

    private boolean isOverlapping() {
        for (Activity activity : this.roster.getActivities()) {
            if (activity.getPrisonGroup().equals(setGroup.getValue())) {
                if (setStartTime.getValue().isAfter(activity.getStartTime())
                        && setStartTime.getValue().isBefore(activity.getStartTime())) {
                    return true;
                }
                if (setEndTime.getValue().isAfter(activity.getStartTime())
                        && setEndTime.getValue().isBefore(activity.getEndTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<TimeBlock> getTimeBlocks() {
        return this.timeBlocks;
    }

    public void update() {
        if (this.setLocation != null || this.setGroup != null) {
            assert setLocation != null;
            setLocation.getItems().setAll(this.roster.getLocationDatabase().values());
            setGroup.getItems().setAll(this.roster.getGroups());
        }
    }
}

