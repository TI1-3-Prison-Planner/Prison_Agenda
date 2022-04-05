package Simulatie;


import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

public class LocationObject {
    private Point2D.Double position;
    private String name;
    private Point2D.Double size;
    private double distance[][] = new double[150][150];
    int targetX ;
    int targetY ;

    public LocationObject(String name,Point2D.Double position,  Point2D.Double size) {
        this.name = name;
        this.position = position;
        this.size = size;
        targetX =(int) position.getX()/32;
        targetY = (int) position.getY()/32;
    }

    public Point2D.Double getPosition() {
        return new Point2D.Double(position.getX()+size.getX()/2,position.getY()-size.getY()/2);

    }

    public String getName() {
        return name;
    }

    public Point2D.Double getSize() {
        return size;
    }

    public int[][] getDistance(){

        return null;
    }

    class Point { public int x; public int y; Point(int x, int y) { this.x = x; this.y = y; } }

    private static int ensureRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    public double[][] recalc(Boolean[][] map) {
        map[targetX][targetY] = false;

        for(int x = 0; x < 100; x++)
            for(int y = 0; y < 100; y++)
                distance[x][y] = Integer.MAX_VALUE;

        Queue<Point> points = new LinkedList<Point>();
        points.offer(new Point(targetX, targetY));
        distance[targetX][targetY] = 0;
        while(!points.isEmpty())
        {
            Point p = points.poll();

            for(int x = -1; x <= 1; x++)
            {
                for(int y = -1; y <= 1; y++)
                {
                    // Check new point is inside field
                    if(p.x+x < 0 || p.x+x >= 100 || p.y+y < 0 || p.y+y >= 100 || Math.abs(x) == Math.abs(y))
                        continue;
                    double d = distance[p.x][p.y] + Math.sqrt(x*x+y*y);
                    if(d < distance[p.x+x][p.y+y] && !map[p.x+x][p.y+y])
                    {
                        distance[p.x+x][p.y+y] = d;
                        points.offer(new Point(p.x+x, p.y+y));
                    }
                }
            }
        }
return distance;

    }
}
