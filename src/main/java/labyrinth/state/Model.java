package labyrinth.state;

import org.tinylog.Logger;

import java.util.ArrayList;

/**
 * Represents the game model (the table), with walls, cells, and the ball.
 */
public class Model {

    /**
     * Represents how many steps the player has made.
     */
    private int steps;
    /**
     * Represents the position of the ball.
     */
    private Position ballPos;
    /**
     * Represents the position of the finish, cannot be changed.
     */
    private final Position victoryPos =  new Position(2,5);
    /**
     * Represents if the player won the game.
     */
    private boolean victory;
    /**
     * Represents a list that will contain all positions of the table.
     */
    private final ArrayList<Position> allPos = new ArrayList<>();

    /**
     * Creates an instance of the model.
     */
    public Model(){
        setStart();
        Logger.info("Model has been built");
    }

    /**
     * Increments the steps by one.
     */
    public void incrementSteps(){
        this.steps++;
        Logger.info("Steps has been incremented: {}",steps);
    }

    /**
     *  Sets the steps to the specified amount.
     * @param steps the amount of steps the player has made
     */
    public void setSteps(int steps){
        this.steps = steps;
        Logger.info("Steps has been set to: {}",steps);
    }

    /** Returns the steps representing the key presses of the user.
     *
     * @return the steps representing the key presses of the user.
     */
    public int getSteps(){
        return this.steps;
    }

    /**
     * Fills the {@code allPos} list with positions.
     */
    private void fill(){
        for(int y=0;y<7;y++){
            for(int x=0;x<7;x++){
                allPos.add(new Position(x,y));
            }
        }
        Logger.info("allPos has been filled up");
    }

    /** Returns {@code allPos} containing all positions on the table.
     *
     * @return {@code allPos} containing all positions on the table.
     */
    public ArrayList<Position> getAllPos() {
        return allPos;
    }

    /** Returns the searched position.
     *
     * @param pos a position which we want to search in {@code allPos}
     * @return the searched position
     */
    private Position getFromAllPos(Position pos){
        return allPos.get(allPos.indexOf(pos));
    }

    /** Returns a position which has the same x and y coordinates as provided.
     *
     * @param x x coordinate of the position
     * @param y y coordinate of the position
     * @return a position which has the same x and y coordinates as provided
     */
    private Position getFromAllPos(int x,int y){
        return getFromAllPos(new Position(x,y));
    }

    /** Builds a provided wall on the provided position, building a wall recursively.
     *  on consecutive positions that share the same wall
     * @param pos the position where we want to build the {@code wall}
     * @param wall a direction of which side the wall is on the position
     */
    private void buildWall(Position pos, Direction wall){
        if(pos.getWalls().contains(wall))
            return;
        pos.getWalls().add(wall);
        switch (wall){
            case UP -> {
                if(pos.getY() == 0)
                    return;
                Position temp = new Position(pos.getX(),pos.getY()-1);
                buildWall(getFromAllPos(temp),Direction.DOWN);
            }
            case DOWN -> {
                if(pos.getY() == 6)
                    return;
                Position temp = new Position(pos.getX(),pos.getY()+1);
                buildWall(getFromAllPos(temp),Direction.UP);
            }
            case RIGHT -> {
                if(pos.getX() == 6)
                    return;
                Position temp = new Position(pos.getX()+1,pos.getY());
                buildWall(getFromAllPos(temp),Direction.LEFT);
            }
            case LEFT -> {
                if(pos.getX() == 0)
                    return;
                Position temp = new Position(pos.getX()-1,pos.getY());
                buildWall(getFromAllPos(temp),Direction.RIGHT);
            }
        }
        Logger.info("Built a wall on the {} at position: {}", wall,pos);
    }

    /**
     * Building all walls, and the edge of the table.
     */
    private void buildWalls(){
        for (Position allPo : allPos) {
            if (allPo.getX() == 0)
                buildWall(allPo, Direction.LEFT);
            if (allPo.getX() == 6)
                buildWall(allPo, Direction.RIGHT);
            if (allPo.getY() == 0)
                buildWall(allPo, Direction.UP);
            if (allPo.getY() == 6)
                buildWall(allPo, Direction.DOWN);
        }
        buildWall(getFromAllPos(0,0),Direction.RIGHT);
        buildWall(getFromAllPos(2,0),Direction.DOWN);
        buildWall(getFromAllPos(3,0),Direction.RIGHT);
        buildWall(getFromAllPos(6,0),Direction.DOWN);
        buildWall(getFromAllPos(1,2),Direction.DOWN);
        buildWall(getFromAllPos(2,2),Direction.RIGHT);
        buildWall(getFromAllPos(5,2),Direction.RIGHT);
        buildWall(getFromAllPos(3,3),Direction.DOWN);
        buildWall(getFromAllPos(3,3),Direction.RIGHT);
        buildWall(getFromAllPos(4,3),Direction.RIGHT);
        buildWall(getFromAllPos(6,3),Direction.DOWN);
        buildWall(getFromAllPos(0,4),Direction.DOWN);
        buildWall(getFromAllPos(4,4),Direction.DOWN);
        buildWall(getFromAllPos(2,5),Direction.LEFT);
        buildWall(getFromAllPos(2,5),Direction.DOWN);
        buildWall(getFromAllPos(2,5),Direction.RIGHT);
        buildWall(getFromAllPos(3,6),Direction.RIGHT);
        buildWall(getFromAllPos(5,6),Direction.RIGHT);
        Logger.info("All walls have been built");
    }

    /** Moves the ball to the provided direction.
     *  if there are no walls blocking, and if the ball is not
     *  already in the victory position
     *  if the ball is in the victory position, the user has won
     * @param direction where we want the ball to be moved
     */
    public void moveBall(Direction direction){
        if(ballPos.equals(victoryPos)){
            victory = true;
            Logger.info("User has won");
            return;
        }
        Position pos = getFromAllPos(ballPos);
        if(pos.getWalls().contains(direction))
            return;
        switch (direction){
            case UP -> {
                ballPos.setY(ballPos.getY()-1);
                moveBall(Direction.UP);
            }
            case DOWN -> {
                ballPos.setY(ballPos.getY()+1);
                moveBall(Direction.DOWN);
            }
            case LEFT -> {
                ballPos.setX(ballPos.getX()-1);
                moveBall(Direction.LEFT);
            }
            case RIGHT -> {
                ballPos.setX(ballPos.getX()+1);
                moveBall(Direction.RIGHT);
            }
        }
        Logger.info("The ball moved {}",direction);
    }

    /** Returns true or false whether the user has won.
     *
     * @return true or false whether the user has won
     */
    public boolean isVictory(){
        return victory;
    }

    /** Returns the current position of the ball.
     *
     * @return the current position of the ball
     */
    public Position getBallPos() {
        return ballPos;
    }

    /** Sets the position of the ball to the provided position.
     *
     * @param ballPos the position of the ball
     */
    public void setBallPos(Position ballPos) {
        this.ballPos = ballPos;
    }

    /** Returns the position of the victory cell.
     *
     * @return the position of the victory cell
     */
    public Position getVictoryPos(){
        return victoryPos;
    }

    /**
     * Clearing the table, moving the ball to its starting position,
     * clearing the steps, and rebuilding everything.
     */
    public void setStart(){
        steps = 0;
        ballPos = new Position(4,1);
        victory = false;
        allPos.clear(); fill();
        buildWalls();
        Logger.info("Starting state is ready");
    }
}
