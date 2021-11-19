package eu.kormos.robotcleaner.controller;

import eu.kormos.robotcleaner.view.AppView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    private final AppView AppView;

    public Listener(AppView AppView) {
        this.AppView = AppView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
