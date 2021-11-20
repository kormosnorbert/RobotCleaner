package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.model.GraphicsModel;
import eu.kormos.robotcleaner.view.AppView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {

    private final AppView appView;
    private final GraphicsModel graphicsModel;

    public ActionController(AppView appView, GraphicsModel graphicsModel) {
        this.appView = appView;
        this.graphicsModel = graphicsModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appView.renderModel(graphicsModel);
    }
}
