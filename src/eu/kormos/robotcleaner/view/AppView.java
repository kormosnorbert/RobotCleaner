package eu.kormos.robotcleaner.view;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.TileChart;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.Tile;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class AppView {

    private JTextArea textArea;
    private JPanel panel;
    private List<String> layout;
    private Robot robot;

    private static AppView instance;
    private GraphicsModel graphicsModel;

    private AppView() {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(panel);
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static AppView getInstance() {
        if (instance == null) {
            instance = new AppView();
        }
        return instance;
    }

    public void setRenderedRobot(Robot robot) {
        this.robot = robot;
    }

    public void generateRoomString() {

        layout = new ArrayList<>();
        TileChart tileChart = TileChart.getInstance();
        List<List<Tile>> allTile = tileChart.getAllTile();
        for (List<Tile> tiles : allTile) {
            StringBuilder sb = new StringBuilder();
            for (Tile tile : tiles) {
                sb.append(tile.getVisual());
            }
            layout.add(sb.toString());
        }
    }

    public void generateRobotString(Robot robot) {
        int robX = robot.getPosition().getX();
        int robY = robot.getPosition().getY();

        StringBuilder sb = new StringBuilder(layout.get(robY));
        sb.setCharAt(robX*2, robot.getVisual());
        layout.set(robY, sb.toString());
    }

    public List<String> getAllVisual(Robot robot) {
        generateRoomString();
        generateRobotString(robot);
        return layout;
    }

    public void render() {

        textArea.setText("");
        List<String> layout = getAllVisual(robot);
        for (String row : layout) {
            textArea.append(row + "\n");
        }
        Font font = new Font("Monospaced", Font.PLAIN, 15);
        textArea.setFont(font);
    }
}
