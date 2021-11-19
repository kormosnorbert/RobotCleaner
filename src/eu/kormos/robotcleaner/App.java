package eu.kormos.robotcleaner;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.controller.RobotController;
import eu.kormos.robotcleaner.model.Room;
import eu.kormos.robotcleaner.model.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.*;

public class App {

    private Room room;
    private Robot robot;
    private AppView appView;

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
        room = new Room(50, 20);
        room.generateRoomWithWalls();
        robot = new Robot(new Position(5, 5), 0, 'O');

        GraphicsModel gc = new GraphicsModel(robot,room);

        appView = AppView.getInstance();
        run();

    }

    public void run() throws InterruptedException {

        appView.setRenderedRobot(robot);
        RobotController robotController = new RobotController(robot);
        robotController.setTargetPosition(new Position(47,17));
        robotController.goToTargetPosition();

        appView.render();

    }
}
