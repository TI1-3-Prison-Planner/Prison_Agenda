package Gui;

import Data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import org.jfree.fx.FXGraphics2D;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ActivityCreator {
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity Activity;
    private ErrorPopup errorPopup;
    public ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
    private ObservableList<PrisonGroup> groups = FXCollections.observableArrayList();
    private ObservableList<String> locations = FXCollections.observableArrayList();
    private Roster roster;
    private Canvas c;
    private Gui g;


    public void init(Roster roster) {
        groups.clear();
        groups.addAll(roster.getGroups());

        this.roster = roster;
        locations.clear();
        locations.addAll(roster.getLocationDatabase().keySet());


        this.c = c;

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
        setLocation.setItems(locations);
        ComboBox setGroup = new ComboBox();
        setGroup.setItems(groups);


        Spinner<LocalTime> setStartTime =timeSpinner();


        setStartTime.setEditable(true);

        Spinner<LocalTime> setEndTime = timeSpinner();
        setEndTime.setEditable(true);

        Button cancel = new Button("Cancel");
        Button add = new Button("Add");

        Button newLocation = new Button("New");
        Button newGroup = new Button("New");

        grid.add(activity, 1, 10);
        grid.add(location, 1, 20);
        grid.add(group, 1, 30);
        grid.add(timeStart, 1, 40);
        grid.add(timeEnd, 1, 50);

        grid.add(activityName, 2, 10);

        grid.add(setGroup, 2, 30);
        grid.add(setStartTime, 2, 40);
        grid.add(setEndTime, 2, 50);
        grid.add(cancel, 2, 80);
        grid.add(add, 1, 80);

        HBox hBox1;
        grid.add(hBox1 = new HBox(setLocation), 2, 20);
        hBox1.setSpacing(70);

        newLocation.setOnAction(e -> {

        });

        HBox hbox2;
        grid.add(hbox2 = new HBox(setGroup), 2, 30);
        hbox2.setSpacing(70);

        newGroup.setOnAction(e -> {

        });


        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {
            activityPlanner.close();

            roster.getActivities().add(new Activity(activityName.getText(),setStartTime.getValue(),setEndTime.getValue(),(PrisonGroup)setGroup.getValue(),roster.getLocationDatabase().get(setLocation.getValue())));



            System.out.println(setStartTime.getValue() + ":start, end :" + setEndTime.getValue());
            System.out.println(timeBlocks.size());



            //TODO, Code missing until adding function is working
        });


        Scene activityScene = new Scene(grid, 300, 250);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();


        stage.show();
    }


    public Spinner<LocalTime> timeSpinner(){
        return  new Spinner<>(new SpinnerValueFactory<LocalTime>() {
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
                if (this.getValue() == null)
                    setValue(LocalTime.now());
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps));
                }
            }
        });
    }

    public Roster getRoster(){
        return this.roster;
    }


}
