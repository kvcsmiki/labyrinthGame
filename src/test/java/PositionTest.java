import labyrinth.state.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    Position position;

    @BeforeEach
    private void setup(){
        position = new Position(3,2);
    }
    @Test
    void setXWithBadParam(){
        position.setX(-1);
        assertEquals(3,position.getX());
        position.setX(7);
        assertEquals(3,position.getX());
    }
    @Test
    void setX(){
        position.setX(4);
        assertEquals(4,position.getX());
    }
    @Test
    void setYWithBadParam(){
        position.setY(-1);
        assertEquals(2,position.getY());
        position.setY(9);
        assertEquals(2,position.getY());
    }
    @Test
    void setY(){
        position.setY(4);
        assertEquals(4,position.getY());
    }
    @Test
    void equals(){
        Position pos = new Position(3,2);
        assertEquals(true,position.equals(pos));
        Position pos2 = new Position(3,4);
        assertEquals(false,position.equals(pos2));
    }
    @Test
    void hashCodeTest(){
        Position pos = new Position(3,2);
        assertEquals(true, position.hashCode() == pos.hashCode());
        Position pos2 = new Position(3,4);
        assertEquals(false,position.hashCode() == pos2.hashCode());
    }
    @Test
    void toStringTest(){
        String toString = "Position{x=3, y=2}";
        assertEquals(true,position.toString().equals(toString));
    }
}
