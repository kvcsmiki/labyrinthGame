package labyrinth;

import java.util.ArrayList;

public class Model {

    private int steps;
    private Position ballPos;
    private final Position victoryPos;
    private Direction direction;
    private boolean victory;
    private ArrayList<Position> allPos;

    public Model(){
        steps = 0;
        ballPos = new Position(4,1);
        victoryPos = new Position(2,5);
        direction = Direction.NONE;
        victory = false;
        allPos = new ArrayList<>(); fill();
        buildWalls();
        printAllWalls();
    }

    private void fill(){
        for(int y=0;y<7;y++){
            for(int x=0;x<7;x++){
                allPos.add(new Position(x,y));
            }
        }
    }

    public ArrayList<Position> getAllPos() {
        return allPos;
    }

    private Position getFromAllPos(Position pos){
        return allPos.get(allPos.indexOf(pos));
    }
    private Position getFromAllPos(int x,int y){
        return getFromAllPos(new Position(x,y));
    }

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
    }
    private void buildWalls(){
        for(int i=0;i< allPos.size();i++){
            if(allPos.get(i).getX() == 0)
                buildWall(allPos.get(i),Direction.LEFT);
            if(allPos.get(i).getX() == 6)
                buildWall(allPos.get(i),Direction.RIGHT);
            if(allPos.get(i).getY() == 0)
                buildWall(allPos.get(i),Direction.UP);
            if(allPos.get(i).getY() == 6)
                buildWall(allPos.get(i),Direction.DOWN);
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
    }
    private void printAllWalls(){
        for(int i=0;i<allPos.size();i++)
            System.out.println("pos: "+allPos.get(i)+" walls: "+allPos.get(i).getWalls());
    }

    public void moveBall(Direction direction){
        if(ballPos.equals(victoryPos)){
            victory = true;
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
                moveBall(Direction.UP);
            }
            case LEFT -> {
                ballPos.setX(ballPos.getX()-1);
                moveBall(Direction.UP);
            }
            case RIGHT -> {
                ballPos.setX(ballPos.getX()+1);
                moveBall(Direction.UP);
            }
        }
    }
    public boolean isVictory(){
        return victory;
    }


    public Position getBallPos() {
        return ballPos;
    }
    public Position getVictoryPos(){
        return victoryPos;
    }
}
