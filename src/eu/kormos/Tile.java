package eu.kormos;

public class Tile {

    private char visual;
    protected Tile(char visual){
        this.visual = visual;
    }
    public char getVisual() {
        return visual;
    }

    public void setVisual(char visual) {
        this.visual = visual;
    }
}

