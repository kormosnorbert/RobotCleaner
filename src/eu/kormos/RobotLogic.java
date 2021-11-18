package eu.kormos;


public class RobotLogic {

    private Robot robot;
    private TileChart tileChart;

    RobotLogic(Robot robot) {
        this.robot = robot;
    }

    public void cleanTheRoom(Robot robot) {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < i / 2; j++) {
                int x = robot.getPosition().getX();
                int y = robot.getPosition().getY();
                FloorTile floorTile = (FloorTile) tileChart.getTileAt(new Position(x, y));
                floorTile.cleanTile();
                robot.moveForward();
            }
            robot.rotateRight();
        }
    }

    public void goToPosition(Robot robot, Position position) {

        while (!(robot.getPosition().equals(position))) {
            Position nextPosition = getLowestNumberPosition();
            Position moveVector = new Position(
                    robot.getPosition().getX()- nextPosition.getX(),
                    robot.getPosition().getY() -nextPosition.getY()
            );
            rotateToDirection(robot, moveVector);
            robot.moveForward();
        }
    }

    private Position getLowestNumberPosition() {
        return new Position(robot.getPosition().getX(),robot.getPosition().getY()+1);
    }

    private void rotateToDirection(Robot robot, Position moveVector) {

        int targetRotation = 0;

        if (moveVector.equals(new Position(0, -1))) {
            targetRotation = 90;
        }
        if (moveVector.equals(new Position(-1, 0))) {
            targetRotation = 180;
        }
        if (moveVector.equals(new Position(0, 1))) {
            targetRotation = 270;
        }
        while (robot.getRotation() != targetRotation) {
            if (robot.getRotation() == 0 && targetRotation == 270) {
                robot.rotateLeft();
            } else if (robot.getRotation() < targetRotation) {
                robot.rotateRight();
            }

        }
    }

    public void floodFillAlgorithm(Position position) throws InterruptedException {
        FloodFiller floodFiller = new FloodFiller();
        floodFiller.floodFillStacked(position);
    }

}
