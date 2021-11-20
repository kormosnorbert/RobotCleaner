package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.ds.FloorTile;
import eu.kormos.robotcleaner.model.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.ds.TileChart;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.Timer;
import java.util.List;
import java.util.*;

public class RobotController {

    private GraphicsModel graphicsModel;
    private TileChart tileChart;
    private Robot robot;
    private Position targetPosition;

    public RobotController(GraphicsModel graphicsModel) {
        this.graphicsModel = graphicsModel;
        this.robot = graphicsModel.getRobot();
        this.tileChart = graphicsModel.getRoom().getTileChart();
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public void cleanTileAt(Position position){
        ((FloorTile)graphicsModel.getRoom().getTileChart().getTileAt(position)).cleanTile();
    }

    public void cleanTheRoom(Robot robot) {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < i / 2; j++) {
                int x = robot.getPosition().getX();
                int y = robot.getPosition().getY();
                FloorTile floorTile = (FloorTile) tileChart.getTileAt(new Position(x, y));
                floorTile.cleanTile();
                robot.moveForward();
            }
            robot.rotateRight();
        }
    }
    public void goToTargetPosition() {

        Timer timer = new Timer(50, e -> {

            if (!(robot.getPosition().equals(targetPosition))) {
                Position nextPosition = getAdjacentMinPosition(targetPosition, robot.getPosition());
                goToNextPosition(nextPosition);

            } else {
                ((Timer) e.getSource()).stop();
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
        Map<Position, Integer> positionDistanceMap = floodFiller.floodFillPathFind(targetPosition);
        Map<Position, Integer> adjacentPositions = new HashMap<>();

        for (Position pos : getAdjacentPositions(robotPosition)) {
            if (positionDistanceMap.containsKey(pos))
                adjacentPositions.put(pos, positionDistanceMap.get(pos));
        }
        Position min = Collections.min(adjacentPositions.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(min);
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
