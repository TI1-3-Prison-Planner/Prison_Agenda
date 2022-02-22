package Gui;

import Data.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class ActivityCreator {
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity Activity;
    private ErrorPopup errorPopup;
    public ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
    private ArrayList<PrisonGroup> groups = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();


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
        setStartTime.setEditable(true);

        Spinner setEndTime = new Spinner();
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
        grid.add(hBox1 = new HBox(setLocation, newLocation), 2, 20);
        hBox1.setSpacing(70);

        newLocation.setOnAction(e->{

        });

        HBox hbox2;
        grid.add(hbox2 = new HBox(setGroup, newGroup), 2, 30);
        hbox2.setSpacing(70);

        newGroup.setOnAction(e->{

        });


        cancel.setOnAction(event -> {
            activityPlanner.close();
        });

        add.setOnAction(event -> {
            this.errorPopup = new ErrorPopup("Overlap with other activities");
            errorPopup.display(stage);


            //creating a timeBlock;
            timeBlocks.add(new TimeBlock(setGroup.getValue().toString(),setLocation.getValue().toString(),Integer.parseInt(setStartTime.getValue().toString()),Integer.parseInt(setEndTime.getValue().toString()),5));

//            startTime = setStartTime.getValue();
//            Activity newActivity = new Activity(activityName.getText(), setStartTime);
            //TODO, Code missing until adding function is working
        });




        Scene activityScene = new Scene(grid, 300, 250);

        activityPlanner.setScene(activityScene);
        activityPlanner.showAndWait();


        stage.show();
    }
    public ArrayList<TimeBlock> getTimeBlocks(){
        return this.timeBlocks;
    }

}
