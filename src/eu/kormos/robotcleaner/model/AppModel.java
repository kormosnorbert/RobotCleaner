package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.datastructures.Position;
import eu.kormos.robotcleaner.model.datastructures.Tile;
import eu.kormos.robotcleaner.model.datastructures.TileChart;

import java.util.ArrayList;
import java.util.List;

public class AppModel {

    private Robot robot;
    private Room room;

    public AppModel() {

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

    public void insertCharToPosition(List<String> roomString, Position pos, char c) {
        int posX = pos.getX();
        int posY = pos.getY();

        StringBuilder sb = new StringBuilder(roomString.get(posY));
        sb.setCharAt(posX*2, c);
        roomString.set(posY, sb.toString());
    }

    public List<String> getRenderedModel(){
        List<String> roomString = getRoomString();
        insertCharToPosition(roomString, robot.getChargerPosition(), 'x');
        insertCharToPosition(roomString, robot.getPosition(), robot.getVisual());
        return roomString;
    }
}
