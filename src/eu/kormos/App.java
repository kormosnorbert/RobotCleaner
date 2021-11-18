package eu.kormos;

import javax.swing.*;

public class App {

    Room room;
    Robot robot;
    Graphics graphics;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new App().initialize();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
    public void initialize() throws InterruptedException {
        graphics = Graphics.getInstance();

        run();
    }
    public void run() throws InterruptedException {
        room = Room.getInstance();
        room.generateRoomWithWalls(50,20);
        robot = new Robot(new Position (5, 5), 0, 'O');
        graphics.setRenderedRobot(robot);
        graphics.render();

        RobotLogic robotLogic = new RobotLogic(robot);
        robotLogic.goToPosition(robot,new Position(robot.getPosition().getX(),robot.getPosition().getY()+1));
        //robotLogic.floodFillAlgorithm(new Position(robot.getPosition().getX(), robot.getPosition().getY()));

        graphics.render();
    }
}
