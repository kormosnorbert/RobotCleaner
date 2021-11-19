package eu.kormos.robotcleaner.model;

import java.util.ArrayList;
import java.util.List;

public class TileChart {

    private static List<List<Tile>> allTile;
    private static TileChart instance;

    private TileChart(){
        allTile = new ArrayList<>();
    }

    public static TileChart getInstance() {
        if(instance == null){
            instance = new TileChart();
        }
        return instance;
    }

    public void setAllTile(List<List<Tile>> allTile) {
        TileChart.allTile = allTile;
    }
    public List<List<Tile>> getAllTile() {
        return allTile;
    }

    public Tile getTileAt(Position pos){
        return allTile.get(pos.getY()).get(pos.getX());
    }
    public void setTileAt(Position pos,Tile tile){
        allTile.get(pos.getY()).set(pos.getX(),tile);
    }
    public void addRow(List<Tile> tiles){
        allTile.add(tiles);
    }
}
