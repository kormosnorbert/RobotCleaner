package eu.kormos.robotcleaner;

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
    /*
    public void showIterationNumber(int num){
        String formattedNum = String.format("%02d",Math.min(num, 99));
        super.setVisual(formattedNum);
    }
    */
    public void cleanTile(){
        this.cleaned = true;
        super.setVisual("  ");
    }
}
