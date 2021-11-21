package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.App;
import eu.kormos.robotcleaner.model.AppModel;
import eu.kormos.robotcleaner.model.Robot;
import eu.kormos.robotcleaner.model.Room;
import eu.kormos.robotcleaner.model.datastructures.Position;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.*;

public class AppController {

    private AppView appView;
    private AppModel appModel;

    private RobotController robotController;

    public AppController(AppView appView, AppModel appModel) {
        this.appView = appView;
        this.appModel = appModel;
    }

    public void initController() {
        initModel();
        initView();
    }

    public void initModel() {
        Room room = new Room(30, 20);
        Robot robot = new Robot(new Position(1, 5), new Position(1, 5), 0, 'O');

        appModel.setRoom(room);
        appModel.setRobot(robot);
        room.generateRoomWithWalls();
        room.generateClutterObjects(1, robot.getPosition());
        robotController = new RobotController(appView, appModel);
    }

    private void initView() {
        appView.setAppModel(appModel);
        setUpListeners();
        JFrame frame = appView.getFrame();
        frame.setSize(appModel.getRoom().getWidth() * 20, appModel.getRoom().getHeight() * 30);
        appView.renderModel();
    }

    public void setUpListeners() {
        JButton runButton = appView.getRunButton();
        JButton resetButton = appView.getResetButton();
        JButton cleanAllButton = appView.getCleanAllButton();

        runButton.addActionListener(e -> {
            robotController.cleanTheRoom();
        });

        resetButton.addActionListener(e -> {
            appView.getFrame().dispose();
            App.main(null);
        });
        cleanAllButton.addActionListener(e -> {
            appModel.getRoom().getTileChart().cleanAllTile();
            appView.renderModel();
        });
    }

}
