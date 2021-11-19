package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.TileChart;
import eu.kormos.robotcleaner.model.FloorTile;
import eu.kormos.robotcleaner.model.Position;
import eu.kormos.robotcleaner.model.Tile;
import eu.kormos.robotcleaner.model.WeightedPosition;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.Timer;
import java.util.*;

public class FloodFiller {

    AppView appView = AppView.getInstance();

    public FloodFiller() {

    }

    public void floodFillStacked(Position initPosition) {
        TileChart tileChart = TileChart.getInstance();
        List<Position> positionList = new ArrayList<>();
        positionList.add(initPosition);

        Timer timer = new Timer(1, e -> {
            appView.render();
            if (!positionList.isEmpty()) {
                Position position = positionList.remove(0);
                Tile tile = tileChart.getTileAt(position);

                if (tile instanceof FloorTile) {
                    FloorTile floorTile = (FloorTile) tile;

                    if (!(floorTile.isCleaned())) {
                        floorTile.cleanTile();
                        positionList.add(new Position(position.getX(), position.getY() + 1));
                        positionList.add(new Position(position.getX() + 1, position.getY()));
                        positionList.add(new Position(position.getX(), position.getY() - 1));
                        positionList.add(new Position(position.getX() - 1, position.getY()));
                    }
                }
            }
            else {
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();
    }
    public Map<Position, Integer> floodFillPathFind(Position initPosition) {
        TileChart tileChart = TileChart.getInstance();
        Map<Position, Integer> allReachableTile = new HashMap<>();
        List<WeightedPosition> posList = new ArrayList<>();

        Position currentPos = initPosition;
        Integer distance = 0;

        posList.add(new WeightedPosition(currentPos,distance));

            while (!posList.isEmpty()) {

                WeightedPosition weigthPos = posList.remove(0);
                currentPos = weigthPos.getPosition();
                distance = weigthPos.getDistance();
                Tile currentTile = tileChart.getTileAt(currentPos);

                if (currentTile instanceof FloorTile) {
                    if (!(allReachableTile.containsKey(currentPos))) {
                        allReachableTile.put(currentPos,distance);
                        posList.add(new WeightedPosition(new Position(currentPos.getX() , currentPos.getY() + 1),distance+1));
                        posList.add(new WeightedPosition(new Position(currentPos.getX()+1, currentPos.getY()),distance+1));
                        posList.add(new WeightedPosition(new Position(currentPos.getX(), currentPos.getY() - 1),distance+1));
                        posList.add(new WeightedPosition(new Position(currentPos.getX()-1, currentPos.getY()),distance+1));
                    }
                }
            }
            return allReachableTile;
    }
}
