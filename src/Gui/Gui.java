package Gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Node;
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
import Gui.*;


/**
 * Author:Midas
 */
public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.out.println("hello world");


        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topBox());
        mainPane.setCenter(getTabPane());
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();


    }

    public TabPane getTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.getTabs().addAll(getRosterViewTab(), getTableViewRosterTab());

        return tabPane;
    }

    public Tab getRosterViewTab() {

        Tab rosterTab = new Tab();
        rosterTab.setText("roster");
        ScrollPane scrollPane = new ScrollPane(getCanvas());
        rosterTab.setContent(scrollPane);

        return rosterTab;
    }

    public Tab getTableViewRosterTab() {
        Tab tableViewRoster = new Tab();
        tableViewRoster.setText("tableview");
        tableViewRoster.setContent(getTableViewRoster());
        return tableViewRoster;
    }

    public Canvas getCanvas() {
        Canvas canvas = new Canvas();
        canvas.setOnMouseClicked(e -> {
            drawBlock(new FXGraphics2D(canvas.getGraphicsContext2D()), e);
        });

        canvas.setHeight(1500);
        canvas.setWidth(1500);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()), canvas.getWidth(), canvas.getHeight());
        return canvas;
    }

    public TableView getTableViewRoster() {
        TableView tableView = new TableView();
        return tableView;
    }

    public HBox topBox() {
        HBox topBox = new HBox();
        Label New = new Label("new");

        topBox.getChildren().add(New);
        return topBox;
    }

    public void draw(FXGraphics2D graphics, double width, double height) {
        int hour = 0;
        int minute = 0;
        for (int i = 0; i < width; i += 100) {

            graphics.draw(new Line2D.Double(i, height, i, 0));

        }
        for (int i = 0; i < height; i += 60) {
            graphics.draw(new Line2D.Double(0, i, 100, i));

            if (i > 1) {
                graphics.drawString(hour + ":00 - " + (hour + 1) + ":00", 10, i - 25);
                hour++;
            }

        }

    }

    public void drawBlock(FXGraphics2D g, MouseEvent e) {

        AgendaEditor add = new AgendaEditor(AgendaEditor.Editor.ADD);
        add.start(new Stage());

//        javafx.geometry.Rectangle2D rect;
//        g.drawString("midas",(int)(e.getX()-e.getX()%50),(int)e.getY()+25);
//        g.draw(new Rectangle2D.Double(e.getX()-e.getX()%50,e.getY(),50,50));


    }


}
