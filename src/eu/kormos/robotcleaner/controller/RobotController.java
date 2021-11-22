package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.AppModel;
import eu.kormos.robotcleaner.model.datastructures.FloorTile;
import eu.kormos.robotcleaner.model.datastructures.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.datastructures.Tile;
import eu.kormos.robotcleaner.model.datastructures.TileChart;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.Timer;

import java.util.List;
import java.util.*;

public class RobotController {

    private final AppView appView;
    private final AppModel appModel;
    private final Robot robot;
    private final TileChart tileChart;
    private final FloodFiller floodFiller;
    private Position targetPos;
    private Boolean isDone = false;

    public RobotController(AppView appView, AppModel appModel) {
        this.appView = appView;
        this.appModel = appModel;
        this.robot = appModel.getRobot();
        this.tileChart = appModel.getRoom().getTileChart();
        this.floodFiller = new FloodFiller(appModel.getRoom().getTileChart());
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public void cleanTileAt(Position position) {
        ((FloorTile) appModel.getRoom().getTileChart().getTileAt(position)).cleanTile();
    }

    public void cleanTheRoom() {
        cleanTileAt(robot.getPosition());
        targetPos = getNearestNotCleanTile();
        if ((targetPos.equals(robot.getPosition()))) {
            targetPos = robot.getChargerPosition();
            goToTargetPosition();
            isDone = true;
        }
        if (!isDone) {
            goToTargetPosition();
        }
    }

    public void goToTargetPosition() {

        Timer timer = new Timer(20, e -> {
            if (!(robot.getPosition().equals(targetPos))) {
                Position nextPosition = getAdjacentMinPosition(targetPos, robot.getPosition());
                if (!(nextPosition.equals(robot.getPosition()))) {
                    goToNextPosition(nextPosition);
                }
                appView.renderModel();
            } else {
                ((Timer) e.getSource()).stop();
                cleanTheRoom();
            }
        });
        timer.start();
    }

    private void goToNextPosition(Position nextPosition) {
        Position moveVector = new Position(
                nextPosition.getX() - robot.getPosition().getX(),
                nextPosition.getY() - robot.getPosition().getY()
        );
        rotateToDirection(moveVector);
        robot.moveForward();
        cleanTileAt(nextPosition);
    }

    private void rotateToDirection(Position moveVector) {

        int targetRotation = 0;

        if (moveVector.equals(new Position(0, 1))) {
            targetRotation = 90;
        }
        if (moveVector.equals(new Position(-1, 0))) {
            targetRotation = 180;
        }
        if (moveVector.equals(new Position(0, -1))) {
            targetRotation = 270;
        }
        while (robot.getRotation() != targetRotation) {
            if ((robot.getRotation() == 0 && targetRotation == 270)) {
                robot.rotateLeft();
            } else if (robot.getRotation() < targetRotation || (robot.getRotation() == 270 && targetRotation == 0)) {
                robot.rotateRight();
            } else {
                robot.rotateLeft();
            }
        }
    }

    public double calculateDistance(Position pos1, Position pos2) {
        return Math.ceil(Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2)));
    }

    private Position getNearestNotCleanTile() {

        double minDistance = Double.POSITIVE_INFINITY;
        Position tilePos = new Position(robot.getPosition().getX(), robot.getPosition().getY());

        for (List<Tile> row : tileChart.getAllTile()) {
            for (Tile tile : row) {

                if (tile instanceof FloorTile && !((FloorTile) tile).isCleaned()) {
                    double distance = calculateDistance(robot.getPosition(), tileChart.getTilePosition(tile));
                    if (distance < minDistance) {
                        minDistance = distance;
                        tilePos = tileChart.getTilePosition(tile);
                    }
                }
            }
        }
        return tilePos;
    }

    private Position getAdjacentMinPosition(Position targetPos, Position robotPos) {
        Map<Position, Integer> positionDistanceMap = floodFiller.floodFillPathFind(targetPos, robotPos);
        Map<Position, Integer> adjacentPositions = new HashMap<>();

        for (Position pos : getAdjacentPositions(robotPos)) {
            if (positionDistanceMap.containsKey(pos))
                adjacentPositions.put(pos, positionDistanceMap.get(pos));
        }
        if (adjacentPositions.isEmpty()) {
            return robotPos;
        }
        Position min = Collections.min(adjacentPositions.entrySet(), Map.Entry.comparingByValue()).getKey();
        return min;

    }

    public List<Position> getAdjacentPositions(Position position) {
        List<Position> adjacentPositions = new ArrayList<>();
        adjacentPositions.add(new Position(position.getX() + 1, position.getY()));
        adjacentPositions.add(new Position(position.getX() - 1, position.getY()));
        adjacentPositions.add(new Position(position.getX(), position.getY() + 1));
        adjacentPositions.add(new Position(position.getX(), position.getY() - 1));
        return adjacentPositions;
    }


}
