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
    private Point2D.Double target;
    private static double rotationSpeed = 0.1;
    private Map map;
    private Font nameFont;
    private double[][] distance;
    private boolean walking = false;
    private String targetname ="";
    private Sim_Main main;
    public Visitor(Point2D.Double position, double angle, Map map, boolean isGuard, int groupID, String Name,Sim_Main main) {
        this.position = position;
        this.angle = angle;
        this.speed = 1 + 5 * Math.random(); //TODO set fixed speed
        this.target = position;
        this.frame = Math.random() * 10;
        this.map = map;
        this.isGuard = isGuard;
        this.group = groupID;
        this.name = Name;
        this.nameFont = new Font("Arial", Font.PLAIN, 12);
        this.main = main;
        this.inmateSprites = new ArrayList<>();
        this.guardSprites = new ArrayList<>();
        try {

            BufferedImage inmateImage = ImageIO.read(getClass().getResourceAsStream("/images/inmate.png"));
            BufferedImage guardImage = ImageIO.read(getClass().getResourceAsStream("/images/guard.png"));
            int w = 46;
            int h = 64;
            for (int y = 0; y < 1; y++) {
                for (int x = 0; x < 4; x++) {
                    this.inmateSprites.add(inmateImage.getSubimage((int) (x * w+0.5), h * y, w, h));
                    this.guardSprites.add(guardImage.getSubimage(x * w+1, h * y, w, h));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(ArrayList<Visitor> visitors) {
//        if (target.distanceSq(position) < 32)
//            return;

        if(distance != null){
            for(int xxx = -1; xxx <= 1; xxx++)
            {
                for(int yyy = -1; yyy <= 1; yyy++)
                {

                    if(distance[(int)this.position.getX()/32 +xxx][(int)this.position.getY()/32+yyy]< distance[(int)this.position.getX()/32][(int)this.position.getY()/32]){

                        this.target.x = this.position.getX() + xxx*32;
                        this.target.y = this.position.getY() + yyy*32;
                        continue;
                    }





                }
            }

        }
        if(distance[(int)this.position.getX()/32][(int)this.position.getY()/32]<5){
            walking = false;
            this.target = (Point2D.Double) main.randomMove(targetname);
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
            this.angle = targetAngle;
            this.angle = oldAngle + rotationSpeed;
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
            this.frame = 3;
        }
    }

    public boolean checkCollision(ArrayList<Visitor> visitors) {

        boolean hasCollision = false;
        if (map.layers.get(1)[(int) Math.max(0, this.position.getY() / 32)][(int) Math.max(0, this.position.getX() / 32)] == -1) {
            hasCollision = true;
        }
        return hasCollision;
    }

    public void draw(Graphics2D g2d) {
        int centerX = guardSprites.get(0).getWidth() / 2;
        int centerY = guardSprites.get(0).getHeight() / 2;
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - centerX, position.getY() - centerY);

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
    }

    public void setTarget(String target) {
        if (!walking&&!this.targetname.equals(target)) {

            this.distance = map.locationObjects.get(target).getDistance();
            this.targetname = target;
        }
        walking = true;
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
