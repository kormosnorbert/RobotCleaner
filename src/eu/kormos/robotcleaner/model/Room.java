package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.datastructures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final int width;
    private final int height;
    private TileChart tileChart;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileChart getTileChart() {
        return tileChart;
    }

    public void setTileChart(TileChart tileChart) {
        this.tileChart = tileChart;
    }

    public void generateRectangularRoom() {
        tileChart = new TileChart();
        for (int y = 0; y < height; y++) {
            List<Tile> tiles = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    tiles.add(new WallTile());
                } else {
                    tiles.add(new FloorTile());
                }
            }
            tileChart.addRow(tiles);
        }
    }

    public void generateWall(Position fromPos, Position toPos) {
        for (int x = fromPos.getX(); x < toPos.getX(); x++) {
            for (int y = fromPos.getY(); y < toPos.getY(); y++) {
                tileChart.setTileAt(new Position(x, y), new WallTile());
            }
        }
    }

    public void generateClutterObjects(int percentage, Position exceptHere) {
        Random r = new Random();
        Position pos;
        List<List<Tile>> tc = tileChart.getAllTile();
        for (List<Tile> row : tc) {
            for (Tile tile : row) {
                if (r.nextInt(100) < percentage) {
                    pos = new Position(row.indexOf(tile), tc.indexOf(row));
                    tileChart.setTileAt(pos, new WallTile());
                }
            }
        }
        tileChart.setTileAt(exceptHere, new FloorTile());
    }

    public void generateRoomWithWalls() {
        generateRectangularRoom();
        generateWall(new Position(32, 0), new Position(34, 17));
        generateWall(new Position(0, 8), new Position(30, 10));
        generateWall(new Position(11, 10), new Position(13, 17));
        generateWall(new Position(40, 8), new Position(43, 15));
    }

    public void generateRandomRoom() {
        Random r = new Random();
        generateRectangularRoom();
    }


}
