package Simulatie;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    private ArrayList<int[][]> layers = new ArrayList<>();


    public Map() {


        try {
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("tileSetObj1.png")));
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("Walls.jpg")));
            tileSets.add(ImageIO.read(getClass().getResourceAsStream("tileSetObj2.png")));

            JsonReader reader = null;
            reader = Json.createReader(getClass().getResourceAsStream("Prison.json"));
            root = reader.readObject();

            this.mapWidth = root.getInt("width");
            this.mapHeight = root.getInt("height");

            this.tileHeight = root.getInt("tileheight");
            this.tileWidth = root.getInt("tilewidth");

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
        for (int i = 0; i<root.getJsonArray("layers").size()-1;i++){
           int[][] layer = new int[mapWidth][mapHeight];

            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {

//                layer1[y][x] = root.getJsonArray("layers").getJsonArray("data").getJsonArray(0).getInt(y*mapWidth + x)-1;
                    layer[y][x] = root.getJsonArray("layers").getJsonObject(i).getJsonArray("data").getInt(y*mapWidth + x)-1;
                }

        }
            layers.add(layer);
        }
    }

    public void draw(FXGraphics2D g, Camera camera) {

        for (int[][] layer : layers) {


            for (int y = ~(int)g.getTransform().getTranslateY()/32; y <(int)g.getTransform().getScaleY()/32+32; y++) {
                for (int x =0;x<1024/32; x++) {

                    if (x < 0|| y < 0||layer[y][x] < 0 || layer[y][x] > 16000||x >150 || y > 150) {
                        continue;
                    }






                        g.drawImage(

                                tiles.get(layer[y][x]),
                                AffineTransform.getTranslateInstance(x * tileWidth, y * tileHeight),
                                null);
                    }

                }
            }
        }

    public  int flip(int n){

        return ~n;
    }

    public void setOffset(int xOfset,int yOfset) {
        this.xOfset = xOfset;
        this.yOfset = yOfset;
    }

    //    public int[][] getMap() {
//        return layer;
//    }
}

