package eu.kormos.robotcleaner.model;

import eu.kormos.robotcleaner.model.datastructures.Position;

public class Robot {

    private final Position position;
    private final Position chargerPosition;
    private int rotation;
    private final char visual;

    public Robot(Position position, Position chargerPosition, int rotation, char visual) {
        this.position = position;
        this.chargerPosition = chargerPosition;
        this.rotation = rotation;
        this.visual = visual;

    }

    public Position getChargerPosition() {
        return chargerPosition;
    }

    public Position getPosition() {
        return this.position;
    }



    public int getRotation() {
        return this.rotation;
    }

    public char getVisual() {
        return visual;
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
