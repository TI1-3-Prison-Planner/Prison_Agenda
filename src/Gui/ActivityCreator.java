package Gui;

import Data.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ActivityCreator extends Observer implements Popup {
    public ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
    private ObservableList<PrisonGroup> groups = FXCollections.observableArrayList();
    private ObservableList<Location> locations = FXCollections.observableArrayList();
    private Roster roster;
    private ComboBox<Location> setLocation;
    private ComboBox<PrisonGroup> setGroup;
    private Spinner<LocalTime> setStartTime;
    private Spinner<LocalTime> setEndTime;
    private TextField activityName;
    private Stage activityPlanner;
    private ObserverRefresh obsRefresh;
    private Activity activity;

    public void init(Roster roster) {
        this.groups.clear();
        this.groups.addAll(roster.getGroups());

        this.roster = roster;
        this.locations.clear();
        this.locations.addAll(roster.getLocationDatabase().values());
    }

    public ActivityCreator(Roster roster, ObserverRefresh observer) {
        this.roster = roster;
        this.obsRefresh = observer;
        this.obsRefresh.addObservers(this);
    }

    public ActivityCreator(Roster roster, ObserverRefresh observer, Activity activity) {
        this.roster = roster;
        this.obsRefresh = observer;
        this.obsRefresh.addObservers(this);
        this.activity = activity;
    }

    @Override
    public void display() {
        GridPane grid = new GridPane();
        this.activityPlanner = new Stage();

        this.activityPlanner.initModality(Modality.APPLICATION_MODAL);
        this.activityPlanner.setTitle("Activity Planner");


        Label activity = new Label("Activity:");
        Label location = new Label("Location:");
        Label group = new Label("Group:");
        Label timeStart = new Label("Time start: ");
        Label timeEnd = new Label("Time end: ");

        this.activityName = new TextField();
        this.setLocation = new ComboBox<>();
        this.setGroup = new ComboBox<>();
        this.setStartTime = timeSpinner();
        this.setStartTime.setEditable(true);
        this.setLocation.getItems().setAll(roster.getLocationDatabase().values());
        this.setGroup.getItems().setAll(this.roster.getGroups());

        this.setEndTime = timeSpinner();
        this.setStartTime.setEditable(true);
        this.setEndTime.setEditable(true);

        Button cancel = new Button("Cancel");
        Button add = new Button("Add");
        Button edit = new Button("Edit");


        grid.add(activity, 1, 10);
        grid.add(location, 1, 20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        grid.add(this.activityName, 2, 10);
        grid.add(this.setLocation, 2, 20);
        grid.add(this.setGroup, 2, 30);
        grid.add(this.setStartTime, 2, 40);
        grid.add(this.setEndTime, 2, 50);
        grid.add(cancel, 2, 80);

        if (this.activity != null) {
            grid.add(edit, 1, 80);
            this.activityName.setText(this.activity.getActivityName());
            this.setLocation.getSelectionModel().select(this.activity.getLocation());
            this.setGroup.getSelectionModel().select(this.activity.getPrisonGroup());
        } else {
            grid.add(add, 1, 80);
        }

        HBox hBox1;
        grid.add(hBox1 = new HBox(this.setLocation), 2, 20);
        hBox1.setSpacing(70);

        HBox hbox2;
        grid.add(hbox2 = new HBox(this.setGroup), 2, 30);
        hbox2.setSpacing(70);

        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {

            try {
                if (!setStartTime.getValue().isAfter(setEndTime.getValue()) && setEndTime.getValue().isAfter(setStartTime.getValue()) && setLocation.getValue() != null && setGroup.getValue() != null && !isOverlapping()) {

                    activityPlanner.close();
                    this.roster.getActivities().add(new Activity(this.activityName.getText(), this.setStartTime.getValue(), this.setEndTime.getValue(), this.setGroup.getValue(), this.setLocation.getValue()));
                    this.obsRefresh.updateAllObservers();

                } else {
                    Alert overlapWarning = new Alert(Alert.AlertType.WARNING);
                    overlapWarning.setContentText("Time overlaps with existing activities. Please enter a valid time.");
                    overlapWarning.showAndWait();
                    //TODO, Code missing until adding function is working
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("An error has occurred. " + e.toString());
                alert.showAndWait();
            }
        });

        edit.setOnAction(event -> editActivity());


        Scene activityScene = new Scene(grid);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();

    }

    private void editActivity() {

    }

    @Override
    public void close() {
        activityName.clear();
        setLocation.getSelectionModel().selectFirst();
        setGroup.getSelectionModel().selectFirst();
        activityPlanner.close();
    }

    private void addActivity() {
        if (isOverlapping()) {
            Alert overlapAlert = new Alert(Alert.AlertType.ERROR, "Overlap with other activities");
            overlapAlert.show();
        } else {
            this.roster.getActivities().add(new Activity(activityName.getText(), setStartTime.getValue(), setEndTime.getValue(), setGroup.getValue(), setLocation.getValue()));
            this.obsRefresh.updateAllObservers();
        }
    }

    //TODO: fix overlapping function - doesn't work
    private boolean isOverlapping() {
        try {
            for (Activity activity : this.roster.getActivities()) {
                if (activity.getPrisonGroup().equals(setGroup.getValue())) {
                    if (this.setStartTime.getValue().isAfter(activity.getStartTime()) && this.setStartTime.getValue().isBefore(activity.getEndTime())
                            && (this.setStartTime.getValue().equals(activity.getStartTime()) || this.setStartTime.getValue().equals(activity.getEndTime()))) {
                        return true;
                    }
                    if (setEndTime.getValue().isAfter(activity.getStartTime()) && setEndTime.getValue().isBefore(activity.getEndTime())
                            && (setEndTime.getValue().equals(activity.getStartTime()) || setEndTime.getValue().equals(activity.getEndTime()))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Alert exceptionAlert = new Alert(Alert.AlertType.ERROR, e.toString());
            exceptionAlert.show();
        }
        return false;
    }

    //this method creates a time spinner.
    public Spinner<LocalTime> timeSpinner() {
        return new Spinner<>(new SpinnerValueFactory<LocalTime>() {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                setConverter(new LocalTimeStringConverter(formatter, null));
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null) {
                    setValue(LocalTime.now());
                } else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps));
                }
            }

            @Override
            public void increment(int steps) {
                if (this.getValue() == null) setValue(LocalTime.now());
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps));
                }
            }
        });
    }

    public Roster getRoster() {
        return this.roster;
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

