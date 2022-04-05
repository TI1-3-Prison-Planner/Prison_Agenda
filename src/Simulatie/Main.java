package Simulatie;

import Data.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    private Map maps;
    private ResizableCanvas canvas;
    private Camera camera;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        javafx.scene.control.Label mouseX = new javafx.scene.control.Label();
        javafx.scene.control.Label mouseY = new javafx.scene.control.Label();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(mouseX, mouseY);
        mainPane.setTop(hBox);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);

        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        camera = new Camera(canvas, g -> draw(g), g2d);
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();


        stage.setScene(new Scene(mainPane));
        stage.setResizable(false);
        stage.setTitle("Fading image");
        stage.setWidth(1024);
        stage.setHeight(1024);
        stage.show();
        draw(g2d);

        canvas.setOnMouseMoved(event -> {
            try {
                Point2D clickPos = camera.getTransform((int) this.canvas.getWidth(), (int) this.canvas.getHeight()).inverseTransform(new Point2D.Double(event.getX(), event.getY()), null);
                mouseX.setText("x: " + (int) clickPos.getX());
                mouseY.setText("Y: " + (int) clickPos.getY());
                for (Visitor visitor : this.visitors) {
                    visitor.setTarget(clickPos);
                }
            } catch (NoninvertibleTransformException e) {
                e.printStackTrace();
            }
        });
    }

    private ArrayList<Visitor> visitors;
    private double timer;

    public void init() {
        FileIO fileIO = new FileIO();
        Roster roster = fileIO.readData(new File("roster.ser"));
        this.maps = new Map();
        this.visitors = new ArrayList<>();
        createVisitors(roster);
        timer = 0;
    }

    private Point2D goToNewTarget(Roster roster) {
        ArrayList<Activity> temp2 = roster.getActivities();

        //TODO timeline link aan startTime in activity
        String newLocation = temp2.get(1).getLocation().getLocationName();

        return this.maps.locationObjects.get(newLocation).getPosition();
    }

    private void createVisitors(Roster roster) {
        for (PrisonGroup group : roster.getGroups()) {
            for (Person inmate : group.getInmates()) {
                Visitor prisoner = new Visitor(new Point2D.Double(2235,3746), 0, this.maps, false, group.getGroupID(), inmate.getName());
                visitors.add(prisoner);
            }
            for (Person guard : group.getGuards()) {
                Visitor guardian = new Visitor(new Point2D.Double(2235,3746), 0, this.maps, true, group.getGroupID(), guard.getName());
                visitors.add(guardian);
            }
        }
    }


    public void draw(FXGraphics2D g) {
        g.scale(0.5, 0.5);
        g.setTransform(new AffineTransform());
        g.setBackground(Color.black);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        AffineTransform originalTransform = g.getTransform();
        g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

        g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        this.maps.draw(g, camera);
        for (Visitor visitor : this.visitors) {
            visitor.draw(g);
            //todo: implementeer connectie met agendalocaties
        }
        g.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
//        maps.setTx(camera.getTransform((int)canvas.getWidth(),(int)canvas.getHeight()).);
        timer += deltaTime;
        if (timer > 10) {
            timer -= 10;
            for (Visitor visitor : this.visitors) {
                visitor.setTarget(new Point2D.Double(Math.random() * 4800, Math.random() * 4800));
            }
        }

        for (Visitor visitor : this.visitors) {
            visitor.update(this.visitors);
        }
    }


    public static void main(String[] args) {
        launch(Main.class);
    }

}


