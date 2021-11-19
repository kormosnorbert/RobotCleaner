package eu.kormos.robotcleaner;

import javax.swing.Timer;
import java.util.*;

public class FloodFiller {

    Graphics graphics = Graphics.getInstance();

    public FloodFiller() {

    }

    public void floodFillStacked(Position initPosition) {
        TileChart tileChart = TileChart.getInstance();
        List<Position> positionList = new ArrayList<>();
        positionList.add(initPosition);

        Timer timer = new Timer(1, e -> {
            graphics.render();
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

        Map<Position, Integer> weightedPositions = new HashMap<>();
        List<WeightedPosition> positionList = new ArrayList<>();

        TileChart tileChart = TileChart.getInstance();

        Integer iteration = 0;

        positionList.add(new WeightedPosition(initPosition,iteration));


            while (!positionList.isEmpty()) {

                WeightedPosition weightedPosition = positionList.remove(0);
                Position position = weightedPosition.getPosition();

                Tile tile = tileChart.getTileAt(position);

                if (tile instanceof FloorTile) {
                    FloorTile floorTile = (FloorTile) tile;

                    if (!(weightedPositions.containsKey(position))) {

                        weightedPositions.put(position,weightedPosition.getDistance());
                        String s = String.format("%02d",weightedPosition.getDistance() );
                        tileChart.getTileAt(position).setVisual(s);
                        iteration = weightedPosition.getDistance()+1;
                        positionList.add(new WeightedPosition(new Position(position.getX() , position.getY() + 1),iteration));
                        positionList.add(new WeightedPosition(new Position(position.getX()+1, position.getY()),iteration));
                        positionList.add(new WeightedPosition(new Position(position.getX(), position.getY() - 1),iteration));
                        positionList.add(new WeightedPosition(new Position(position.getX()-1, position.getY()),iteration));
                    }
                }
            }
            return weightedPositions;
    }
}
