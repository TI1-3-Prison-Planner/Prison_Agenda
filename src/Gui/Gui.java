package Gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;


import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


/**
 * Author:Midas
 *
 */
public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topBox());
        mainPane.setCenter(getTabPane());
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();


    }

    public TabPane getTabPane(){
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.getTabs().addAll(getRosterViewTab(),getTableViewRosterTab());

        return tabPane;
    }

    public Tab getRosterViewTab(){

        Tab rosterTab=new Tab();
        rosterTab.setText("roster");
        rosterTab.setContent(getCanvas());

        return rosterTab;
    }

    public Tab getTableViewRosterTab(){
        Tab tableViewRoster = new Tab();
        tableViewRoster.setText("tableview");
        tableViewRoster.setContent(getTableViewRoster());
        return tableViewRoster;
    }

    public Canvas getCanvas(){
        Canvas canvas = new Canvas();
        canvas.setOnMouseClicked(e->{drawBlock(new FXGraphics2D(canvas.getGraphicsContext2D()),e);

        });
        canvas.setHeight(500);
        canvas.setWidth(500);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()),canvas.getWidth(),canvas.getHeight());
        return canvas;
    }

    public TableView getTableViewRoster(){
        TableView tableView = new TableView();
        return tableView;
    }

    public HBox topBox(){
        HBox topBox= new HBox();
        Label New = new Label("new");

        topBox.getChildren().add(New);
        return topBox;
    }

    public void draw(FXGraphics2D graphics,double width,double height){
       for (int i = 0;i<width;i+=50){

           graphics.draw(new Line2D.Double(i,height,i,0));

       }
       for (int i = 0;i<height;i+=50){
           graphics.draw(new Line2D.Double(0,i,50,i));
       }

    }
    public void drawBlock(FXGraphics2D g, MouseEvent e){

        Rectangle2D rect;
        g.draw(rect =new Rectangle2D.Double(e.getX()-e.getX()%50,e.getY(),50,50));





    }



}
