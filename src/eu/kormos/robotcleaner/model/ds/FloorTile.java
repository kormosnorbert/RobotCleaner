package eu.kormos.robotcleaner.model.ds;

public class FloorTile extends Tile {

    private boolean cleaned;

    public FloorTile() {
        super(": ");
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public void cleanTile(){
        this.cleaned = true;
        super.setVisual("  ");
    }
}
