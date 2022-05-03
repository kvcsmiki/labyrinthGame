package labyrinth;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Scene2Controller {
    @FXML
    VBox root;

    @FXML
    Canvas canvas;

    private String name;

    Model model;

    private final int cellSize = 100;

    public void initialize(){
        model = new Model();
        drawStart();
    }

    private GraphicsContext getGraphicsContext(){
        return canvas.getGraphicsContext2D();
    }

    public void setName(String name){
        this.name = name;
    }

    private void drawStart(){
        drawCells();
        drawWalls();
        drawBall();
        drawFinish();
    }
    private void drawCells(){
        GraphicsContext gc = getGraphicsContext();
        ArrayList<Position> allPos = model.getAllPos();
        for(int i=0;i<allPos.size();i++){
            gc.setFill(Color.BLACK);
            gc.fillRect(allPos.get(i).getX()*cellSize,allPos.get(i).getY()*cellSize,cellSize,cellSize);
            gc.setFill(Color.WHITE);
            gc.fillRect(allPos.get(i).getX()*cellSize+1,allPos.get(i).getY()*cellSize+1,cellSize-1,cellSize-1);
        }
    }
    private void drawWalls(){
        GraphicsContext gc = getGraphicsContext();
        gc.setLineWidth(4);
        ArrayList<Position> allPos = model.getAllPos();
        for(int i=0;i<allPos.size();i++){
            for(int j=0;j<allPos.get(i).getWalls().size();j++){
                switch(allPos.get(i).getWalls().get(j)){
                    case UP -> {
                        gc.moveTo(allPos.get(i).getX()*cellSize,allPos.get(i).getY()*cellSize);
                        gc.lineTo(allPos.get(i).getX()*cellSize+cellSize,allPos.get(i).getY()*cellSize);
                        gc.stroke();
                    }
                    case DOWN -> {
                        gc.moveTo(allPos.get(i).getX()*cellSize,allPos.get(i).getY()*cellSize+cellSize);
                        gc.lineTo(allPos.get(i).getX()*cellSize+cellSize,allPos.get(i).getY()*cellSize+cellSize);
                        gc.stroke();
                    }
                    case LEFT -> {
                        gc.moveTo(allPos.get(i).getX()*cellSize,allPos.get(i).getY()*cellSize);
                        gc.lineTo(allPos.get(i).getX()*cellSize,allPos.get(i).getY()*cellSize+cellSize);
                        gc.stroke();
                    }
                    case RIGHT -> {
                        gc.moveTo(allPos.get(i).getX()*cellSize+cellSize,allPos.get(i).getY()*cellSize);
                        gc.lineTo(allPos.get(i).getX()*cellSize+cellSize,allPos.get(i).getY()*cellSize+cellSize);
                        gc.stroke();
                    }
                }
            }
        }
    }

    private void drawBall(){
        GraphicsContext gc = getGraphicsContext();
        Position ballPos = model.getBallPos();
        gc.setFill(Color.BLUE);
        gc.fillOval(ballPos.getX()*cellSize+cellSize/4, ballPos.getY()*cellSize+cellSize/4, cellSize/2,cellSize/2);
    }

    private void drawFinish(){
        Position victoryPos = model.getVictoryPos();
        GraphicsContext gc = getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Calibri",38));
        gc.fillText("CÉL", victoryPos.getX()*cellSize + 23, victoryPos.getY()*cellSize + cellSize/2*1.2);
    }



}
