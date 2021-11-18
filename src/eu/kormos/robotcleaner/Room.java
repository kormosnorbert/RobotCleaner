package eu.kormos.robotcleaner;

import java.util.ArrayList;
import java.util.List;

public final class Room {

    private static Room instance;
    private static TileChart tileChart;

    private Room(){

    }

    public static Room getInstance(){
        if(instance == null){
            instance = new Room();
        }
        return instance;
    }

    public void generateRectangularRoom(int sizeX, int sizeY){
        tileChart = TileChart.getInstance();

        for (int y = 0; y < sizeY; y++) {
            List<Tile> tiles = new ArrayList<>();
            for (int x = 0; x < sizeX; x++) {
                if(x == 0 || y == 0 || x == sizeX-1 || y == sizeY-1) {
                    tiles.add(new WallTile());
                } else {
                    tiles.add(new FloorTile());
                }
            }
            tileChart.addRow(tiles);
        }
    }

    public void generateWall(int sizeX, int sizeY, Position position){
        for(int x = 0;x<sizeX;x++){
            for (int y = 0; y < sizeY; y++) {
                tileChart.setTileAt(new Position(x+position.getX(),y+ position.getY()), new WallTile());
            }
        }
    }

    public void generateRoomWithWalls(int width, int height){
        generateRectangularRoom(width,height);
        generateWall(2,8, new Position(25,10));
        generateWall(width-5,2, new Position(0,7));
        generateWall(width-5,2, new Position(4,15));
    }


}
