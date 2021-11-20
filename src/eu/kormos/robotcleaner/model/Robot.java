package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.data.Position;

public class Robot {

    private Position position;
    private int rotation;
    private char visual = 'O';

    public Robot(Position position, int rotation, char visual) {
        this.position = position;
        this.rotation = rotation;
        this.visual = visual;

    }

    public Position getPosition() {
        return this.position;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return this.rotation;
    }

    public char getVisual() {
        return visual;
    }

    public void setVisual(char visual) {
        this.visual = visual;
    }

    public void moveForward(){
        int posX = position.getX();
        int posY = position.getY();
        switch(rotation){
            case(0):{
                position.setX(posX+1);
                break;
            }
            case(90):{
                position.setY(posY+1);
                break;
            }
            case(180):{
                position.setX(posX-1);
                break;
            }
            case(270):{
                position.setY(posY-1);
                break;
            }
        }

    }

    public void rotateLeft(){
        this.rotation = (this.rotation == 0) ? 270 : this.rotation-90;

    }
    public void rotateRight(){
        this.rotation = (this.rotation == 270) ? 0 : this.rotation+90;
    }


}
