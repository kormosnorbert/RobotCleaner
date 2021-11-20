package eu.kormos.robotcleaner;

import eu.kormos.robotcleaner.controller.AppController;
import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.datastructures.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.Room;
import eu.kormos.robotcleaner.view.AppView;

public class App {

    public static void main(String[] args) {

        Room room = new Room(50, 20);
        room.generateRoomWithWalls();
        //room.generateRandomRoom();

        Robot robot = new Robot(new Position(5, 5), 0, 'O');

        room.generateClutterObjects(1, robot.getPosition());

        GraphicsModel graphicsModel = new GraphicsModel(robot, room);
        AppView appView = new AppView();
        AppController appController = new AppController(appView, graphicsModel);
        appController.initController();
    }
}
