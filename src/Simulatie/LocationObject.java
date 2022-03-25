package Simulatie;

import java.awt.geom.Point2D;

public class LocationObject {
    private Point2D.Double position;
    private String name;
    private Point2D.Double size;

    public LocationObject(String name,Point2D.Double position,  Point2D.Double size) {
        this.name = name;
        this.position = position;
        this.size = size;
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Point2D.Double getSize() {
        return size;
    }
}
