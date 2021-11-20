package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.data.FloorTile;
import eu.kormos.robotcleaner.model.data.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.data.Tile;
import eu.kormos.robotcleaner.model.data.TileChart;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.Timer;

import java.util.List;
import java.util.*;

public class RobotController {
    private final AppView appView;
    private final GraphicsModel graphicsModel;
    private final TileChart tileChart;
    private final Robot robot;
    private Position targetPosition;


    public RobotController(AppView appView,GraphicsModel graphicsModel) {
        this.appView = appView;
        this.graphicsModel = graphicsModel;
        this.robot = graphicsModel.getRobot();
        this.tileChart = graphicsModel.getRoom().getTileChart();
    }

    public void cleanTileAt(Position position){
        ((FloorTile)graphicsModel.getRoom().getTileChart().getTileAt(position)).cleanTile();
    }

    public void cleanTheRoom() {

            targetPosition = getNearestNotCleanTile();
            if (!(targetPosition.equals(robot.getPosition()))) {
                goToTargetPosition();
            }
    }

    private Position getNearestNotCleanTile() {

        Position tilePos = new Position(robot.getPosition().getX(),robot.getPosition().getY());
        for (List<Tile> row: tileChart.getAllTile()){
            for (Tile tile : row) {
                if(tile instanceof FloorTile && !((FloorTile) tile).isCleaned()){
                    return tileChart.getTilePosition(tile);
                }
            }
        }
        return tilePos;
    }

    public void goToTargetPosition() {

        Timer timer = new Timer(20, e -> {
            if (!(robot.getPosition().equals(targetPosition))) {
                Position nextPosition = getAdjacentMinPosition(targetPosition, robot.getPosition());
                goToNextPosition(nextPosition);
                appView.renderModel(graphicsModel);
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

    private Position getAdjacentMinPosition(Position targetPosition, Position robotPosition) {
        FloodFiller floodFiller = new FloodFiller(graphicsModel.getRoom().getTileChart());
        Map<Position, Integer> positionDistanceMap = floodFiller.floodFillPathFind(targetPosition, robotPosition);
        Map<Position, Integer> adjacentPositions = new HashMap<>();

        for (Position pos : getAdjacentPositions(robotPosition)) {
            if (positionDistanceMap.containsKey(pos))
                adjacentPositions.put(pos, positionDistanceMap.get(pos));
        }
        Position min = Collections.min(adjacentPositions.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(adjacentPositions);
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


}
