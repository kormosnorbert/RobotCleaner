package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.model.Position;
import eu.kormos.robotcleaner.view.AppView;

import javax.swing.*;

public class GraphicsController {

    private final AppView appView;
    private final GraphicsModel graphicsModel;

    public GraphicsController(AppView appView, GraphicsModel graphicsModel){
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
        runButton.addActionListener(new ActionController(appView,graphicsModel));
    }

    public void initRobot(){
        RobotController robotController = new RobotController(graphicsModel);
        robotController.setTargetPosition(new Position(47, 17));
        robotController.goToTargetPosition();
    }

    public void initGraphics(){
        appView.renderModel(graphicsModel);
    }

}
