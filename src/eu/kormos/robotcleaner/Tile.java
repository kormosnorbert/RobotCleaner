package eu.kormos.robotcleaner;

public class Tile {

    private String visual;
    protected Tile(String visual){
        this.visual = visual;
    }
    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }
}

