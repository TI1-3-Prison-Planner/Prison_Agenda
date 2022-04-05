package Simulatie;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

public class Visitor {
    private Point2D position;
    private double angle;
    private ArrayList<BufferedImage> sprites;
    private double speed;
    private double frame;
    private Point2D target;
    private static double rotationSpeed = 1;
    private Map map;
    private int path[][] = new int[150][150];
    private double distance[][];
    private Stack<Point2D.Double> route;
    public boolean walking = false;


    public Visitor(Point2D position, double angle, Map map) {
        this.position = position;
        this.angle = angle;
        this.map = map;
        this.speed = 10;
        this.target = new Point2D.Double(map.locationObjects.get("Cell 4").getPosition().getX() - map.locationObjects.get("Cell 4").getSize().getX() / 2,
                map.locationObjects.get("Cell 4").getPosition().getY() - map.locationObjects.get("Cell 4").getSize().getY() / 2);
        this.frame = Math.random() * 10;

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (map.layers.get(1)[i][j] < 0) {
                    path[j][i] = 1;
                } else {
                    path[j][i] = 0;
                }
            }

        }
//        distance = map.locationObjects.get("Yard 1").recalc(path);

        this.sprites = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/npc.png"));
            int w = image.getWidth() / 2;
            int h = image.getHeight() / 3;
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 2; x++) {
                    this.sprites.add(image.getSubimage(x * w, y * h, w, h));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pathFinding() {
        this.route = PathFinding.path((int) Math.max(0, this.position.getX() / 32), (int) Math.max(0, this.position.getY() / 32), this.target, this.path);


    }


    public void update(ArrayList<Visitor> visitors) {
        if (this.route != null) {
            this.target = this.route.peek();
            if (this.position.getY() < this.route.peek().getY() + 32 && this.position.getY() > this.route.peek().getY() - 32 && this.position.getX() < this.route.peek().getX() + 32 && this.position.getX() > this.route.peek().getX() - 32) {
                route.pop();
            }
        }


        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());
        double rotation = targetAngle - this.angle;
        while (rotation < -Math.PI) {
            rotation += 2 * Math.PI;
        }
        while (rotation > Math.PI) {
            rotation -= 2 * Math.PI;
        }

        double oldAngle = this.angle;
        if (rotation < -rotationSpeed) {
            this.angle -= rotationSpeed;
        } else if (rotation > rotationSpeed) {
            this.angle += rotationSpeed;
        } else {
            this.angle = targetAngle;
        }

        Point2D oldPosition = this.position;

        this.position = new Point2D.Double(
                this.position.getX() + this.speed * Math.cos(this.angle),
                this.position.getY() + this.speed * Math.sin(this.angle));
        boolean hasCollision = false;
        hasCollision = hasCollision || checkCollision(visitors);


        if (hasCollision) {
            this.position = oldPosition;
            this.frame = 0;
            this.angle = oldAngle + rotationSpeed;
        } else {
            this.frame += 0.1;
        }
    }

    public boolean checkCollision(ArrayList<Visitor> visitors) {

        boolean hasCollision = false;
        if (map.layers.get(1)[(int) Math.max(0, this.position.getY() / 32)][(int) Math.max(0, this.position.getX() / 32)] == -1) {
            hasCollision = true;
        }

//        for(Visitor visitor : visitors) {
//            if(visitor != this) {
//                if(visitor.position.distanceSq(position) < 64*64) {
//                    hasCollision = true;
//                }
//            }
//        }
        return hasCollision;
    }

    public void draw(Graphics2D g2d) {
        int centerX = sprites.get(0).getWidth() / 2;
        int centerY = sprites.get(0).getHeight() / 2;
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - centerX, position.getY() - centerY);
        tx.rotate(angle + Math.PI / 2, centerX, centerY);
        tx.translate(0, 20);


        if (route != null) {
            float i = 0;
            float size = route.size();
            for (Point2D.Double aDouble : route) {
                i++;
                g2d.setPaint(Color.getHSBColor(i / size, 1.0f, 1.0f));
                Rectangle2D rect = new Rectangle2D.Double(aDouble.getX(), aDouble.getY(), 32, 32);
                g2d.fill(rect);
            }
        }


        g2d.drawImage(this.sprites.get((int) Math.floor(frame) % this.sprites.size()), tx, null);

//        for (int i = 0; i < 150; i++) {
//            for (int j = 0; j < 150; j++) {
//                if(path[i][j]==0||path[i][j]>1){
//                    g2d.fill(new Rectangle2D.Double(j*32,i*32,32,32));
//                }
//            }
//
//        }


        g2d.setColor(Color.white);
        g2d.draw(new Ellipse2D.Double(position.getX() - 32, position.getY() - 32, 64, 64));
//        g2d.draw(new Line2D.Double(position, target));
    }

    public void setTarget(Point2D newTarget) {
        path = new int[150][150];
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (map.layers.get(1)[i][j] < 0) {
                    path[j][i] = 1;
                } else {
                    path[j][i] = 0;
                }
            }

        }
        this.route = new Stack<>() ;
        this.route.addAll(PathFinding.path((int) this.position.getX() / 32, (int) this.position.getY() / 32, newTarget, this.path));
    }
}
