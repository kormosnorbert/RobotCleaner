package eu.kormos.robotcleaner.view;

import eu.kormos.robotcleaner.model.AppModel;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class AppView {
    private JFrame frame;

    private JTextArea textArea;
    private JPanel panel;
    private JButton runButton;
    private JButton resetRoomButton;
    private JButton cleanAllButton;
    private AppModel appModel;

    public AppView() {

        frame = new JFrame("Application");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JButton getResetRoomButton() {
        return resetRoomButton;
    }

    public JButton getCleanAllButton() {
        return cleanAllButton;
    }

    public void renderModel(){
        textArea.setText("");
        List<String> roomString = appModel.getRenderedModel();
        for (String row : roomString) {
            textArea.append(row + "\n");
        }
        Font font = new Font("Monospaced", Font.PLAIN, 15);
        textArea.setFont(font);
    }
}
