package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.datastructures.Tile;
import eu.kormos.robotcleaner.model.datastructures.TileChart;

import java.util.ArrayList;
import java.util.List;

public class GraphicsModel {

    private Robot robot;
    private Room room;

    public GraphicsModel(Robot robot, Room room) {
        this.robot = robot;

        this.room = room;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<String> getRoomString() {
        List<String> roomString = new ArrayList<>();
        TileChart tileChart = room.getTileChart();
        List<List<Tile>> allTile = tileChart.getAllTile();
        for (List<Tile> tiles : allTile) {
            StringBuilder sb = new StringBuilder();
            for (Tile tile : tiles) {
                sb.append(tile.getVisual());
            }
            roomString.add(sb.toString());
        }
        return roomString;
    }

    public void insertRobotString(List<String> roomString) {
        int robX = robot.getPosition().getX();
        int robY = robot.getPosition().getY();

        StringBuilder sb = new StringBuilder(roomString.get(robY));
        sb.setCharAt(robX*2, robot.getVisual());
        roomString.set(robY, sb.toString());
    }

    public List<String> getRenderedModel(){
        List<String> roomString = getRoomString();
        insertRobotString(roomString);
        return roomString;
    }

}
