package eu.kormos.robotcleaner;

import java.util.ArrayList;
import java.util.List;

public class TileChart {

    private static List<List<Tile>> allTile;
    private static TileChart instance;

    private TileChart(){
        allTile = new ArrayList<>();
    }

    public static void setAllTile(List<List<Tile>> allTile) {
        TileChart.allTile = allTile;
    }

    public static TileChart getInstance() {
        if(instance == null){
            instance = new TileChart();
        }
        return instance;
    }
    public static List<List<Tile>> getAllTile() {
        return allTile;
    }
    public Tile getTileAt(Position position){
        return allTile.get(position.getY()).get(position.getX());
    }
    public void setTileAt(Position position,Tile tile){
        allTile.get(position.getY()).set(position.getX(),tile);
    }
    public void addRow(List<Tile> tiles){
        allTile.add(tiles);
    }
}
