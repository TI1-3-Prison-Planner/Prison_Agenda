package Simulatie;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Main extends Application {

    private Map maps;
    private ResizableCanvas canvas;


    private Camera camera;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();

        canvas = new ResizableCanvas(g->draw(g),mainPane);



        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        camera = new Camera(canvas,g -> draw(g),g2d);
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();



        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.setWidth(1024);
        stage.setHeight(1024);
        stage.show();
        draw(g2d);

        canvas.setOnMouseMoved(event -> {
            try {
                Point2D clickPos = camera.getTransform(1024, 1024).inverseTransform(new Point2D.Double(event.getX(), event.getY()), null);

                for(Visitor visitor : this.visitors) {
                    visitor.setTarget(clickPos);
                }
            } catch (NoninvertibleTransformException e) {
                e.printStackTrace();
            }

        });


    }

    private  ArrayList<Visitor> visitors;
    private double timer;
    public void init()
    {
        maps = new Map();


        this.visitors = new ArrayList<>();
        while(this.visitors.size() < 1)
        {
            Visitor visitor = new Visitor(new Point2D.Double(Math.random()*1000, Math.random()*1000), 0);
            if(!visitor.checkCollision(this.visitors))
            {
                this.visitors.add(visitor);
            }
        }

        timer = 0;
    }





    public void draw(FXGraphics2D g)
    {
        g.scale(0.5,0.5);
        g.setTransform(new AffineTransform());
        g.setBackground(Color.black);
        g.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());
        AffineTransform originalTransform = g.getTransform();
        g.setTransform(camera.getTransform((int)canvas.getWidth(),(int)canvas.getHeight()));

        g.setTransform(camera.getTransform((int)canvas.getWidth(),(int)canvas.getHeight()));
       maps.draw(g,camera);
        for(Visitor visitor : this.visitors) {
            visitor.draw(g);
        }
       g.setTransform(originalTransform);


    }

    public void update(double deltaTime) {

//        maps.setTx(camera.getTransform((int)canvas.getWidth(),(int)canvas.getHeight()).);
        timer+=deltaTime;
        if(timer > 10)
        {
            timer -= 10;
            for(Visitor visitor : this.visitors)
            {
                visitor.setTarget(new Point2D.Double(Math.random()*1000, Math.random()*1000));
            }
        }


        for(Visitor visitor : this.visitors) {
            visitor.update(this.visitors);
        }
    }







    public static void main(String[] args)
    {
        launch(Main.class);
    }

}


