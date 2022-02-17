package Gui;
import Data.*;
import com.sun.javafx.text.TextLine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


import java.time.LocalTime;

public class AgendaEditor extends Application {
    private LocalTime startTime;
    private LocalTime endTime;
    private Activity  Activity;

    enum Editor {
        ADD,
        EDIT,
        REMOVE
    }

    private Editor editor;



    public AgendaEditor(Editor s){
       {
          if(s == Editor.ADD){

          }else if(s== Editor.EDIT){

          }else if(s==Editor.REMOVE);


        }

    }

    @Override
    public void start(Stage stage) {



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
