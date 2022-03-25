package Simulatie;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class NPC {
    private Point2D pos;
    private Direction direction;
    private boolean isGuard;
    private ArrayList<BufferedImage> sprites;
    private double speed;
    private BufferedImage sprite;
    private Point2D nextPos;
    private static double rotationSpeed = 0.1;


    public enum Direction {
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    public NPC(Point2D pos, Direction direction, boolean isGuard) {
        this.pos = pos;
        this.direction = direction;
        this.isGuard = isGuard;
        this.speed = 1 + 5 * Math.random();
        this.nextPos = pos;

        this.sprites = new ArrayList<>();

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/PrisonSprites.png"));
            int w = image.getWidth() / 4;
            int h = image.getHeight() / 2;
            for (int y = 0; y < 2; y++) {
                for (int x = 0; x < 3; x++) {
                    this.sprites.add(image.getSubimage(x * w, y * h, w, h));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(ArrayList<NPC> npcs)
    {
        if(nextPos.distanceSq(pos) < 32)
            return;

        double targetAngle = Math.atan2(this.nextPos.getY() - this.pos.getY(), this.nextPos.getX() - this.pos.getX());
        
        Point2D oldPosition = this.pos;

        this.pos = new Point2D.Double(
                this.pos.getX() + this.speed,
                this.pos.getY() + this.speed);
        boolean hasCollision = false;
        hasCollision = hasCollision || checkCollision(npcs);


        if(hasCollision) {
            this.pos = oldPosition;
        }
    }

    public boolean checkCollision(ArrayList<NPC> npcs)
    {
        boolean hasCollision = false;
        for(NPC npc : npcs) {
            if(npc != this) {
                if(npc.pos.distanceSq(pos) < 64*64) {
                    hasCollision = true;
                }
            }
        }
        return hasCollision;
    }

    public void draw(Graphics2D g2d) {
        int centerX = sprites.get(0).getWidth() / 2;
        int centerY = sprites.get(0).getHeight() / 2;

        AffineTransform tx = new AffineTransform();
        tx.translate(pos.getX() - centerX, pos.getX() - centerY);

        int index;
        if (isGuard) {
            index = 4;
        } else {
            index = 0;
        }

        switch (direction) {
            case LEFT:
                this.sprite = this.sprites.get(index + 2);
                break;
            case RIGHT:
                this.sprite = this.sprites.get(index + 2);
                g2d.drawImage(this.sprite, (int) tx.getTranslateX(), (int)tx.getTranslateY()
                        + this.sprite.getHeight(), this.sprite.getWidth(), -this.sprite.getHeight(),null);
            case UP:
                this.sprite = this.sprites.get(index + 1);
                break;
            case DOWN:
                this.sprite = this.sprites.get(index);
                break;
        }

        if(this.direction != Direction.RIGHT) {
            g2d.drawImage(this.sprites.get(0), tx, null);
        }
    }
}
