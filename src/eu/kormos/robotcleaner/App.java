package eu.kormos.robotcleaner;

import eu.kormos.robotcleaner.controller.AppController;
import eu.kormos.robotcleaner.model.AppModel;
import eu.kormos.robotcleaner.view.AppView;

public class App {

    public static void main(String[] args) {
        AppModel appModel = new AppModel();
        AppView appView = new AppView();
        AppController appController = new AppController(appView, appModel);
        appController.initController();
    }
}
