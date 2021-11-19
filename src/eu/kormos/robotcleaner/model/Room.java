package eu.kormos.robotcleaner.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int width;
    private final int height;
    private TileChart tileChart;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
        tileChart = TileChart.getInstance();
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

    public void generateWall(int sizeX, int sizeY, Position position) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tileChart.setTileAt(
                        new Position(
                                x + position.getX(),
                                y + position.getY()),
                        new WallTile()
                );
            }
        }
    }

    public void generateRoomWithWalls() {
        generateRectangularRoom();
        generateWall(2, 8, new Position(25, 10));
        generateWall(width - 5, 2, new Position(0, 7));
        generateWall(width - 5, 2, new Position(4, 15));
        generateWall(2, 8, new Position(35, 4));
    }


}
