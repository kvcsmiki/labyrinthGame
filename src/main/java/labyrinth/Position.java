package labyrinth;

import java.util.ArrayList;
import java.util.Objects;

public class Position {

    private int x;
    private int y;
    private ArrayList<Direction> walls;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.walls = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Direction> getWalls(){
        return this.walls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
