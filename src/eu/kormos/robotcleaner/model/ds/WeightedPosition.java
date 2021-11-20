package eu.kormos.robotcleaner.model.ds;

import eu.kormos.robotcleaner.model.Position;

import java.util.Objects;

public class WeightedPosition {
    private Position position;
    private Integer distance;

    public WeightedPosition(Position position, Integer distance) {
        this.position = position;
        this.distance = distance;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "WeightedPosition{" +
                "position=" + position +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedPosition that = (WeightedPosition) o;
        return Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getDistance());
    }
}
