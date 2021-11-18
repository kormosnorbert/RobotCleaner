package eu.kormos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Graphics {


    private JTextArea textArea;
    private JPanel panel;
    static Graphics instance;
    static List<String> layout;
    Robot robot;

    private Graphics(){
        JFrame frame = new JFrame("AppFx");
        frame.setContentPane(panel);
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void setRenderedRobot(Robot robot){
        this.robot = robot;
    }

    public static Graphics getInstance() {
        if(instance == null){
            instance = new Graphics();
        }
        return instance;
    }

    public  List<String> getLayout() {
        return layout;
    }

    public  void setLayout(List<String> layout) {
        Graphics.layout = layout;
    }

    public void generateRoomString() {

        layout = new ArrayList<>();
        List<List<Tile>> allTile = TileChart.getAllTile();
        for (int y = 0; y < allTile.size(); y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < allTile.get(y).size(); x++) {
                sb.append(allTile.get(y).get(x).getVisual());
            }
            layout.add(sb.toString());
        }
    }

    public void generateRobotString(Robot robot){
        int robX = robot.getPosition().getX();
        int robY = robot.getPosition().getY();

        StringBuilder sb = new StringBuilder(layout.get(robY));
        sb.setCharAt(robX, robot.getVisual());
        layout.set(robY, sb.toString());
    }

    public List<String> drawAll(Robot robot){
        generateRoomString();
        generateRobotString(robot);
        return layout;
    }
    public void render(){
        //System.out.println("Rendered");
        textArea.setText("");
        Graphics graphics = Graphics.getInstance();
        List<String> layout = graphics.drawAll(robot);
        for (String row : layout) {
            textArea.append(row + "\n");
        }
        Font font =new Font( "Monospaced", Font.PLAIN, 15 );
        textArea.setFont(font);
    }
}
