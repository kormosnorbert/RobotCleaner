package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.datastructures.Position;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.*;

public class AppController {

    private final AppView appView;
    private final GraphicsModel graphicsModel;

    RobotController robotController;

    public AppController(AppView appView, GraphicsModel graphicsModel){
        this.appView = appView;
        this.graphicsModel = graphicsModel;
    }

    public void initController() {
        initGraphics();
        initRobot();
        setUpListeners();
    }

    public void setUpListeners(){
        JButton runButton = appView.getRunButton();
        runButton.addActionListener(e -> {
            robotController.cleanTheRoom();
        });
    }

    public void initRobot(){
        robotController = new RobotController(appView,graphicsModel);
    }

    public void initGraphics(){
        appView.renderModel(graphicsModel);
    }

    public Position getManualPosition(){
        int x = Integer.parseInt(appView.getPosXTextField().getText());
        int y = Integer.parseInt(appView.getPosYTextField().getText());

        return new Position(x,y);
    }

}
