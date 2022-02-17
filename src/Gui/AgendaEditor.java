package Gui;

import Data.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalTime;

public class AgendaEditor{
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity  Activity;


    public void display(Stage stage) {



        GridPane grid = new GridPane();
        stage.setWidth(500);
        stage.setHeight(500);
        grid.setPrefSize(stage.getWidth(),stage.getHeight());
        Label activity = new Label("activity:");
        Label location = new Label("location:");
        Label group = new Label("group:");
        Label timeStart = new Label("time start:");
        Label timeEnd = new Label("times end:");

        TextArea activityName = new TextArea();

        ComboBox setLocation = new ComboBox();
        ComboBox setGroup = new ComboBox();

        Spinner setStartTime = new Spinner();
        Spinner setEndTime = new Spinner();

        Button cancel = new Button("cancel");
        Button add = new Button("add");

        grid.add(activity,1,1);
        grid.add(location,1,5);

        stage.setScene(new Scene(grid));

        stage.show();



    }
}
