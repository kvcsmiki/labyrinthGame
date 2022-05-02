package labyrinth;

import java.util.ArrayList;

public class Model {

    private int steps;
    private Position ballPos;
    private Position victoryPos;
    private Direction direction;
    private boolean victory;
    private ArrayList<Position> allPos;

    public Model(){
        steps = 0;
        ballPos = new Position(5,2);
        victoryPos = new Position(3,6);
        direction = Direction.NONE;
        victory = false;
        allPos = new ArrayList<>(); fill();
        buildWalls();

    }

    private void fill(){
        for(int y=1;y<8;y++){
            for(int x=1;x<8;x++){
                allPos.add(new Position(x,y));
            }
        }
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
                if(pos.getY() == 1)
                    return;
                Position temp = new Position(pos.getX(),pos.getY()-1);
                buildWall(getFromAllPos(temp),Direction.DOWN);
            }
            case DOWN -> {
                if(pos.getY() == 7)
                    return;
                Position temp = new Position(pos.getX(),pos.getY()+1);
                buildWall(getFromAllPos(temp),Direction.UP);
            }
            case RIGHT -> {
                if(pos.getX() == 7)
                    return;
                Position temp = new Position(pos.getX()+1,pos.getY());
                buildWall(getFromAllPos(temp),Direction.LEFT);
            }
            case LEFT -> {
                if(pos.getX() == 1)
                    return;
                Position temp = new Position(pos.getX()-1,pos.getY());
                buildWall(getFromAllPos(temp),Direction.RIGHT);
            }
        }
    }
    private void buildWalls(){
        for(int i=0;i< allPos.size();i++){
            if(allPos.get(i).getX() == 1)
                buildWall(allPos.get(i),Direction.LEFT);
            if(allPos.get(i).getX() == 7)
                buildWall(allPos.get(i),Direction.RIGHT);
            if(allPos.get(i).getY() == 1)
                buildWall(allPos.get(i),Direction.UP);
            if(allPos.get(i).getY() == 7)
                buildWall(allPos.get(i),Direction.DOWN);
        }
        buildWall(getFromAllPos(1,1),Direction.RIGHT);
        buildWall(getFromAllPos(3,1),Direction.DOWN);
        buildWall(getFromAllPos(4,1),Direction.RIGHT);
        buildWall(getFromAllPos(7,1),Direction.DOWN);
        buildWall(getFromAllPos(2,3),Direction.DOWN);
        buildWall(getFromAllPos(3,3),Direction.RIGHT);
        buildWall(getFromAllPos(6,3),Direction.RIGHT);
        buildWall(getFromAllPos(4,4),Direction.DOWN);
        buildWall(getFromAllPos(4,4),Direction.RIGHT);
        buildWall(getFromAllPos(5,4),Direction.RIGHT);
        buildWall(getFromAllPos(7,4),Direction.DOWN);
        buildWall(getFromAllPos(1,5),Direction.DOWN);
        buildWall(getFromAllPos(5,5),Direction.DOWN);
        buildWall(getFromAllPos(3,6),Direction.LEFT);
        buildWall(getFromAllPos(3,6),Direction.DOWN);
        buildWall(getFromAllPos(3,6),Direction.RIGHT);
        buildWall(getFromAllPos(4,7),Direction.RIGHT);
        buildWall(getFromAllPos(6,7),Direction.RIGHT);
    }
    private void printAllWalls(){
        for(int i=0;i<allPos.size();i++)
            System.out.println("pos: "+allPos.get(i)+" walls: "+allPos.get(i).getWalls());
    }



}
