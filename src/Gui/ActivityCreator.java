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

    /**
     * method to set up the stage activityPlanner.
     */
    @Override
    public void display() {
        GridPane grid = new GridPane();
        this.activityPlanner = new Stage();

        this.activityPlanner.initModality(Modality.APPLICATION_MODAL);
        this.activityPlanner.setTitle("Activity Planner");

        //labels created for gridPane
        Label activity = new Label("Activity:");
        Label location = new Label("Location:");
        Label group = new Label("Group:");
        Label timeStart = new Label("Time start: ");
        Label timeEnd = new Label("Time end: ");

        this.activityName = new TextField();
        this.setLocation = new ComboBox<>();
        this.setGroup = new ComboBox<>();
        this.setStartTime = timeSpinner();
        this.setLocation.getItems().setAll(roster.getLocationDatabase().values());
        this.setGroup.getItems().setAll(this.roster.getGroups());

        this.setEndTime = timeSpinner();
        this.setStartTime.setEditable(true);
        this.setEndTime.setEditable(true);

        Button cancel = new Button("Cancel");
        Button add = new Button("Add");
        Button edit = new Button("Edit");

        //added labels to gridPane
        grid.add(activity, 1, 10);
        grid.add(location, 1, 20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        //added textfield comboboxes and spinners to gridPane
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

        //HBoxes to add location and groups to the gridPane.
        HBox hBox1;
        grid.add(hBox1 = new HBox(this.setLocation), 2, 20);
        hBox1.setSpacing(70);

        HBox hBox2;
        grid.add(hBox2 = new HBox(this.setGroup), 2, 30);
        hBox2.setSpacing(70);

        //setOnAction methods for all buttons.
        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> addActivity());

        edit.setOnAction(event -> editActivity());

        Scene activityScene = new Scene(grid);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();

    }

    /**
     * method to get an activity from the roster, create a new activity with the changes and replace the old activity with the new activity. After that all observers are updated.
     */
    private void editActivity() {
        int indexActiv = this.roster.getActivities().indexOf(this.activity);
        Activity tempActiv = new Activity(this.activityName.getText(), this.setStartTime.getValue(), this.setEndTime.getValue(), this.setGroup.getValue(), this.setLocation.getValue());
        this.roster.getActivities().set(indexActiv, tempActiv);
        this.obsRefresh.updateAllObservers();
        close();
    }

    /**
     * close method to clear the name and close the stage.
     */
    @Override
    public void close() {
        activityName.clear();
        setLocation.getSelectionModel().selectFirst();
        setGroup.getSelectionModel().selectFirst();
        activityPlanner.close();
    }

    /**
     * method to create a new activity if the location and group are added and if the isOverlapping is false. The method gets all information from the GUI and updates observers after that.
     */
    private void addActivity() {
        try {
            LocalTime startTime = this.setStartTime.getValue();
            LocalTime endTime = this.setEndTime.getValue();
            if (setLocation.getValue() != null && setGroup.getValue() != null && !isOverlapping(startTime, endTime)) {

                activityPlanner.close();
                this.roster.getActivities().add(new Activity(this.activityName.getText(), startTime,
                        endTime, this.setGroup.getValue(), this.setLocation.getValue()));
                this.obsRefresh.updateAllObservers();

            } else {
                //alert to warn that activities are planned on the same time.
                Alert overlapWarning = new Alert(Alert.AlertType.ERROR);
                overlapWarning.setContentText("Time overlaps with existing activities. Please enter a valid time.");
                overlapWarning.showAndWait();
                //TODO, Code missing until adding function is working
            }
        } catch (Exception e) {
            //error alert function.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occurred. " + e.toString());
            alert.showAndWait();
        }
    }

    /**
     * method to loop through all activities and to check if the startTime of a activity is before the endTime of an other activity.
     * @param startTime
     * @param endTime
     * @return boolean if activities are planned on the same time.
     */
    private boolean isOverlapping(LocalTime startTime, LocalTime endTime) {
        try {
            for (Activity activity : this.roster.getActivities()) {
                if (activity.getPrisonGroup().equals(setGroup.getValue())) {
                    if (startTime.isBefore(activity.getEndTime()) && activity.getStartTime().isBefore(endTime)) {
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

    /**
     * method to create spinner with help of dataTimeFormatter to get only hours and minutes.
     * @return spinner with hours and minutes
     */
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

    //getter for roster Object
    public Roster getRoster() {
        return this.roster;
    }

    //method to return an Arraylist with Timeblocks
    public ArrayList<TimeBlock> getTimeBlocks() {
        return this.timeBlocks;
    }

    /**
     * update method to update the location and group.
     */
    public void update() {
        if (this.setLocation != null || this.setGroup != null) {
            assert setLocation != null;
            setLocation.getItems().setAll(this.roster.getLocationDatabase().values());
            setGroup.getItems().setAll(this.roster.getGroups());
        }
    }

}

