package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.datastructures.*;
import eu.kormos.robotcleaner.model.datastructures.Position;

import javax.swing.Timer;
import java.util.*;

public class FloodFiller {

    private final TileChart tileChart;

    public FloodFiller(TileChart tileChart) {
        this.tileChart = tileChart;
    }

    public void floodFillStacked(Position initPosition) {
        List<Position> positionList = new ArrayList<>();
        positionList.add(initPosition);

        Timer timer = new Timer(1, e -> {
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
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public Map<Position, Integer> floodFillPathFind(Position currentPos, Position robotPos) {

        Map<Position, Integer> allReachablePos = new HashMap<>();
        List<WeightedPosition> nextPosList = new ArrayList<>();

        int distance = 0;

        nextPosList.add(new WeightedPosition(currentPos, distance));

        while (!(nextPosList.isEmpty() || (currentPos.equals(robotPos)))) {
            WeightedPosition weightPos = nextPosList.remove(0);
            currentPos = weightPos.getPosition();
            distance = weightPos.getDistance();

            Tile currentTile = tileChart.getTileAt(currentPos);
            //if (currentTile instanceof FloorTile) {
                if (!(allReachablePos.containsKey(currentPos))) {
                    allReachablePos.put(currentPos,distance);
                    nextPosList.addAll(getSurroundingTilesToList(currentPos, distance));
                }
            //}
        }
        incWeightOfCleanTile(allReachablePos);
        return allReachablePos;
    }

    public List<WeightedPosition> getSurroundingTilesToList(Position currentPos, Integer distance) {
        List<WeightedPosition> list = new ArrayList<>();
        list.add(new WeightedPosition(new Position(currentPos.getX(), currentPos.getY() + 1), distance + 1));
        list.add(new WeightedPosition(new Position(currentPos.getX() - 1, currentPos.getY()), distance + 1));
        list.add(new WeightedPosition(new Position(currentPos.getX(), currentPos.getY() - 1), distance + 1));
        list.add(new WeightedPosition(new Position(currentPos.getX() + 1, currentPos.getY()), distance + 1));
        removeWallTiles(list);
        return list;
    }

    public void removeWallTiles(List<WeightedPosition> list) {
        list.removeIf(wp -> !(tileChart.getTileAt(wp.getPosition()) instanceof FloorTile));
    }

    public void incWeightOfCleanTile(Map<Position, Integer> map) {

        for (Map.Entry<Position, Integer> entry : map.entrySet()){
            if(tileChart.getTileAt(entry.getKey()) instanceof FloorTile) {
                FloorTile ft = (FloorTile) tileChart.getTileAt(entry.getKey());
                if (ft.isCleaned()) {
                    entry.setValue(entry.getValue() + 10);
                }
            }
        }
    }

}
