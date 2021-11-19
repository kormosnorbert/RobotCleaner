package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.Room;
import eu.kormos.robotcleaner.model.Tile;
import eu.kormos.robotcleaner.model.TileChart;

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
        List<String> roomStringList = new ArrayList<>();
        TileChart tileChart = room.getTileChart();
        List<List<Tile>> allTile = tileChart.getAllTile();
        for (List<Tile> tiles : allTile) {
            StringBuilder sb = new StringBuilder();
            for (Tile tile : tiles) {
                sb.append(tile.getVisual());
            }
            roomStringList.add(sb.toString());
        }
        return roomStringList;
    }

    public void insertRobotString(Robot robot,List<String> roomStringList) {
        int robX = robot.getPosition().getX();
        int robY = robot.getPosition().getY();

        StringBuilder sb = new StringBuilder(roomStringList.get(robY));
        sb.setCharAt(robX*2, robot.getVisual());
        roomStringList.set(robY, sb.toString());
    }

}
