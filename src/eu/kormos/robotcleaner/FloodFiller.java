package eu.kormos.robotcleaner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
}
