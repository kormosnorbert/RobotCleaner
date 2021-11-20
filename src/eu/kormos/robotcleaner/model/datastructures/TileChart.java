package eu.kormos.robotcleaner.model.datastructures;

import java.util.ArrayList;
import java.util.List;

public class TileChart {

    private static List<List<Tile>> allTile;

    public TileChart(){
        allTile = new ArrayList<>();
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

    public Position getTilePosition(Tile tile){
        int x = 0;
        int y = 0;
        for (List<Tile> row: getAllTile()){
            for (Tile t : row) {
                if(t.equals(tile)){
                x = row.indexOf(tile);
                y = getAllTile().indexOf(row);
                }
            }
        }
        return new Position(x,y);
    }
}
