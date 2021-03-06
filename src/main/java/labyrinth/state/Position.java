package labyrinth.state;

import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.Objects;

/** A class represents a single cell.
 *
 */
public class Position {

    /**
     * The x coordinate of the position.
     */
    private int x;
    /**
     * The y coordinate of the position.
     */
    private int y;
    /**
     * A list of the walls that the cells has.
     */
    private ArrayList<Direction> walls;

    /**
     * Creates a position if x and y are between 0 and 6.
     * @param x the x coordinate of the position
     * @param y the x coordinate of the position
     */
    public Position(int x, int y) {
        if(x > 6 || y > 6 || x < 0 || y < 0){
            Logger.warn("x and y can only be between 0 and 6");
            return;
        }
        this.x = x;
        this.y = y;
        this.walls = new ArrayList<>();
    }

    /** Returns the x coordinate of the position.
     *
     * @return the x coordinate of the position
     */
    public int getX() {
        return x;
    }

    /** Returns the y coordinate of the position.
     *
     * @return the y coordinate of the position
     */
    public int getY() {
        return y;
    }

    /** Sets the x coordinate to the provided value.
     *
     * @param x what we want the {@code x} to be set
     */
    public void setX(int x) {
        if(x > 6 || x < 0){
            Logger.warn("x can only be between 0 and 6");
            return;
        }
        this.x = x;
        Logger.info("X set to: {}",x);
    }
    /** Sets the y coordinate to the provided value.
     *
     * @param y what we want the {@code y} to be set
     */
    public void setY(int y) {
        if(y > 6 || y < 0){
            Logger.warn("y can only be between 0 and 6");
            return;
        }
        this.y = y;
        Logger.info("Y set to: {}",y);
    }

    /** Returns the list of the walls that the position has.
     *
     * @return the list of the walls that the position has
     */
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
