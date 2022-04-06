package Simulatie;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor {
    private Point2D position;

    private double angle;
    private ArrayList<BufferedImage> inmateSprites;
    private ArrayList<BufferedImage> guardSprites;
    private boolean isGuard;
    private int group;
    private String name;
    private double speed;
    private double frame;
    private Point2D target;
    private static double rotationSpeed = 0.1;
    private Map map;
    private Font nameFont;

    public Visitor(Point2D position, double angle, Map map, boolean isGuard, int groupID, String Name) {
        this.position = position;
        this.angle = angle;
        this.speed = 1 + 5 * Math.random();
        this.target = position;
        this.frame = Math.random() * 10;
        this.map = map;
        this.isGuard = isGuard;
        this.group = groupID;
        this.name = Name;
        this.nameFont = new Font("Arial", Font.PLAIN, 12);

        this.inmateSprites = new ArrayList<>();
        this.guardSprites = new ArrayList<>();
        try {

            BufferedImage inmateImage = ImageIO.read(getClass().getResourceAsStream("/images/inmate.png"));
            BufferedImage guardImage = ImageIO.read(getClass().getResourceAsStream("/images/guard.png"));
            int w = 64;
            int h = 64;
            for (int y = 0; y < 1; y++) {
                for (int x = 0; x < 3; x++) {
                    this.inmateSprites.add(inmateImage.getSubimage(x * w, y * h, w, h));
                    this.guardSprites.add(guardImage.getSubimage(x * w, y * h, w, h));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void update(ArrayList<Visitor> visitors) {
        if (target.distanceSq(position) < 32)
            return;

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
            this.angle = targetAngle;
//            this.angle = oldAngle + rotationSpeed;
        }

        double degrees = Math.toDegrees(this.angle);
        if (degrees < 0) {
            degrees += 360;
        }
        degrees %= 360;
        if (degrees >= 45 && degrees < 135) {
            this.frame = 0;
        } else if (degrees >= 135 && degrees < 225) {
            this.frame = 2;
        } else if (degrees >= 225 && degrees < 315) {
            this.frame = 1;
        } else {
            this.frame = 2;
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
        int centerX = guardSprites.get(0).getWidth() / 2;
        int centerY = guardSprites.get(0).getHeight() / 2;
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - centerX, position.getY() - centerY);
//        tx.translate(0, 20);

        ArrayList<BufferedImage> sprites;
        if (isGuard) {
            sprites = guardSprites;
        } else {
            sprites = inmateSprites;
        }


        g2d.drawImage(sprites.get((int) Math.floor(frame) % sprites.size()), tx, null);

        g2d.setFont(nameFont);
        g2d.setColor(Color.black);
        g2d.drawString(name, (int) (tx.getTranslateX()), (int) (tx.getTranslateY()));
        g2d.draw(new Ellipse2D.Double(position.getX() - 32, position.getY() - 32, 64, 64));
//        g2d.draw(new Line2D.Double(position, target));
    }

    public void setTarget(Point2D newTarget) {
        this.target = newTarget;
    }

    public double getAngle() {
        return angle;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }
}
