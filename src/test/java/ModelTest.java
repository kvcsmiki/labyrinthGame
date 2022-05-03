import labyrinth.state.Direction;
import labyrinth.state.Model;
import labyrinth.state.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {


    Model model = new Model();

    @BeforeEach
    private void setup(){
        model.setSteps(10);
    }
    @Test
    void incrementSteps(){
        model.incrementSteps();
        assertEquals(11,model.getSteps());
    }

    @Test
    void setSteps(){
        model.setSteps(20);
        assertEquals(20,model.getSteps());
    }
    @Test
    void moveBallUp(){
        model.setBallPos(new Position(3,3));
        model.moveBall(Direction.UP);
        assertEquals(new Position(3,0), model.getBallPos());
    }
    @Test
    void moveBallUpBlocked(){
        model.setBallPos(new Position(2,1));
        model.moveBall(Direction.UP);
        assertEquals(new Position(2,1), model.getBallPos());
    }
    @Test
    void moveBallDown(){
        model.setBallPos(new Position(0,0));
        model.moveBall(Direction.DOWN);
        assertEquals(new Position(0,4), model.getBallPos());
    }
    @Test
    void moveBallDownBlocked(){
        model.setBallPos(new Position(1,2));
        model.moveBall(Direction.DOWN);
        assertEquals(new Position(1,2), model.getBallPos());
    }
    @Test
    void moveBallLeft(){
        model.setBallPos(new Position(3,3));
        model.moveBall(Direction.LEFT);
        assertEquals(new Position(0,3), model.getBallPos());
    }
    @Test
    void moveBallLeftBlocked(){
        model.setBallPos(new Position(6,6));
        model.moveBall(Direction.LEFT);
        assertEquals(new Position(6,6), model.getBallPos());
    }
    @Test
    void moveBallRightBlocked(){
        model.setBallPos(new Position(3,3));
        model.moveBall(Direction.RIGHT);
        assertEquals(new Position(3,3), model.getBallPos());
    }
    @Test
    void moveBallRight(){
        model.setBallPos(new Position(1,0));
        model.moveBall(Direction.RIGHT);
        assertEquals(new Position(3,0), model.getBallPos());
    }
    @Test
    void moveBallFromVictory(){
        model.setBallPos(model.getVictoryPos());
        model.moveBall(Direction.UP);
        assertEquals(model.getVictoryPos(),model.getBallPos());
    }
    @Test
    void setStart(){
        model.setStart();
        assertEquals(0,model.getSteps());
        assertEquals(new Position(4,1),model.getBallPos());
    }



}
