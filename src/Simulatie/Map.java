package Simulatie;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;


public class Map {
    private int mapWidth;
    private int mapHeight;
    private int tileWidth;
    private int tileHeight;
    private int xOfset = 100;
    private int yOfset = 100;

    private JsonObject root;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private ArrayList<BufferedImage> tileSets = new ArrayList<>();
    public ArrayList<int[][]> layers = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<BufferedImage>>> layerImageCache = new ArrayList<>();
    private static int chunksize = 16;
    public HashMap<String, LocationObject> locationObjects = new HashMap<>();


    public Map() {


        try {
// C:\Users\midas\Documents\school\jaar 1\periode 3\proftaak\proftaakRepo\Prison_Agenda\src\Simulatie\res
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("/tileSetObj1.png")));
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("/Walls.jpg")));
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("/tileSetObj2.png")));

            JsonReader reader = null;
            reader = Json.createReader(getClass().getResourceAsStream("/Prison.json"));
            root = reader.readObject();

            this.mapWidth = root.getInt("width");
            this.mapHeight = root.getInt("height");

            this.tileHeight = root.getInt("tileheight");
            this.tileWidth = root.getInt("tilewidth");
            for (int i = 0; i < root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").size(); i++) {

                locationObjects.put(root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getJsonString("name").getString()
                        , new LocationObject(root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getJsonString("name").getString()
                                , new Point2D.Double(root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getInt("x"), root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getInt("y")),
                                new Point2D.Double(root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getInt("width"), root.getJsonArray("layers").getJsonObject(6).getJsonArray("objects").getJsonObject(i).getInt("height"))));

            }

            ;


            for (BufferedImage tilemap : tileSets) {
                for (int y = 0; y < tilemap.getHeight(); y += this.tileHeight) {
                    for (int x = 0; x < tilemap.getWidth(); x += this.tileWidth) {
                        tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < root.getJsonArray("layers").size() - 1; i++) {
            int[][] layer = new int[mapWidth][mapHeight];

            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {

//                layer1[y][x] = root.getJsonArray("layers").getJsonArray("data").getJsonArray(0).getInt(y*mapWidth + x)-1;
                    layer[y][x] = root.getJsonArray("layers").getJsonObject(i).getJsonArray("data").getInt(y * mapWidth + x) - 1;
                }

            }
            layers.add(layer);

            ArrayList<ArrayList<BufferedImage>> layerImages = new ArrayList<>();
            for (int x = 0; x < this.mapWidth; x += chunksize) {
                ArrayList<BufferedImage> row = new ArrayList<>();
                for (int y = 0; y < this.mapHeight; y += chunksize) {
                    BufferedImage img = new BufferedImage(chunksize * tileWidth, chunksize * tileHeight, BufferedImage.TYPE_INT_ARGB);
                    drawLayer((Graphics2D) img.getGraphics(), layer, x, y, x + chunksize, y + chunksize);
                    row.add(img);
                }
                layerImages.add(row);
            }
            layerImageCache.add(layerImages);
        }
    }

    public void draw(FXGraphics2D g, Camera camera) {
        try {
            Point2D topLeft = camera.getTransform(1024, 1024).inverseTransform(new Point2D.Double(0, 0), null);
            Point2D botRight = camera.getTransform(1024, 1024).inverseTransform(new Point2D.Double(1024, 1024), null);
            for (ArrayList<ArrayList<BufferedImage>> layer : layerImageCache) {
                for (int x = Math.max(0, (int) topLeft.getX() / 32 / chunksize); x < Math.min(mapWidth / chunksize, botRight.getX() / 32 / chunksize); x++) {
                    for (int y = Math.max(0, (int) topLeft.getY() / 32 / chunksize); y < Math.min(mapHeight / chunksize, botRight.getY() / 32 / chunksize); y++) {
                        g.drawImage(layer.get(x).get(y), AffineTransform.getTranslateInstance(x * tileWidth * chunksize, y * tileHeight * chunksize), null);
                    }

                }
            }
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void drawLayer(Graphics2D g, int[][] layer, int xMin, int yMin, int xMax, int yMax) {
        for (int y = yMin; y < Math.min(yMax, this.mapHeight); y++) {
            for (int x = xMin; x < Math.min(xMax, this.mapWidth); x++) {

                if (x < 0 || y < 0 || layer[y][x] < 0 || layer[y][x] > 16000 || x > 150 || y > 150) {
                    continue;
                }
                g.drawImage(
                        tiles.get(layer[y][x]),
                        AffineTransform.getTranslateInstance((x - xMin) * tileWidth, (y - yMin) * tileHeight),
                        null);
            }

        }
    }

    public int flip(int n) {

        return ~n;
    }

    public void setOffset(int xOfset, int yOfset) {
        this.xOfset = xOfset;
        this.yOfset = yOfset;
    }

    //    public int[][] getMap() {
//        return layer;
//    }
}

