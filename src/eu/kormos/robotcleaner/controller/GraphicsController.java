package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.view.AppView;

public class GraphicsController {

    private AppView appView;
    private GraphicsModel graphicsModel;

    public GraphicsController(AppView appView, GraphicsModel graphicsModel){
        this.appView = appView;
        this.graphicsModel = graphicsModel;
    }
}
