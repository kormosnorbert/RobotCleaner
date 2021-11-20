package eu.kormos.robotcleaner;

import eu.kormos.robotcleaner.controller.GraphicsController;
import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.data.Position;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.Room;
import eu.kormos.robotcleaner.view.AppView;

public class App {

    public static void main(String[] args) {

        Room room = new Room(50, 20);
        room.generateRoomWithWalls();
        Robot robot = new Robot(new Position(5, 5), 0, 'O');

        GraphicsModel graphicsModel = new GraphicsModel(robot, room);
        AppView appView = new AppView();
        GraphicsController graphicsController = new GraphicsController(appView, graphicsModel);
        graphicsController.initController();
    }
}
