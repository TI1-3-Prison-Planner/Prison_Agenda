package Simulatie;


import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PathFinding {
    public static Stack<Point2D.Double> path = new Stack<Point2D.Double>();

    public static boolean Path(int x, int y, Point2D target, int[][] map) {

        int targetx = (int) target.getX() / 32;
        int targety = (int) target.getY() / 32;



        if (map[x][y] == 9) {
            path.add(new Point2D.Double(x * 32-100, y * 32-100));
            return true;
        }

        map[targetx][targety] = 9;
        if (map[x][y] == 0) {
            map[x][y] = 2;


            int dx = 1;
            int dy = 0;
            if (Path(x + dx, y + dy, target, map)) {
                path.add(new Point2D.Double(x * 32, y * 32));
                return true;
            }

            dx = 0;
            dy = 1;
            if (Path(x + dx, y + dy, target, map)) {
                path.add(new Point2D.Double(x * 32, y * 32));
                return true;
            }

            dx = -1;
            dy = 1;
            if (Path(x + dx, y + dy, target, map)) {
                path.add(new Point2D.Double(x * 32, y * 32));
                return true;
            }

            dx = 0;
            dy = -1;
            if (Path(x + dx, y + dy, target, map)) {
                path.add(new Point2D.Double(x * 32, y * 32));
                return true;
            }
        }
        return false;
    }


    public static Stack<Point2D.Double> path(int x, int y, Point2D target, int[][] map) {
        Path(x, y, target, map);


        return path;
    }
}
