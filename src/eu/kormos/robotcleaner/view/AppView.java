package eu.kormos.robotcleaner.view;

import eu.kormos.robotcleaner.model.GraphicsModel;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class AppView {

    private JTextArea textArea;
    private JPanel panel;
    private JButton runButton;

    private JTextField posXTextField;

    private JTextField posYTextField;
    public AppView() {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JTextField getPosXTextField() {
        return posXTextField;
    }

    public JTextField getPosYTextField() {
        return posYTextField;
    }

    public void renderModel(GraphicsModel graphicsModel){
        textArea.setText("");
        List<String> roomString = graphicsModel.getRenderedModel();
        for (String row : roomString) {
            textArea.append(row + "\n");
        }
        Font font = new Font("Monospaced", Font.PLAIN, 15);
        textArea.setFont(font);
    }
}
