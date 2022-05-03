package labyrinth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        Logger.info("Drawing starting state");
        drawCells();
        drawWalls();
        drawBall();
        drawFinish();
    }
    private void drawTable(){
        drawCells();
        drawWalls();
        drawFinish();
    }
    private void drawCells(){
        GraphicsContext gc = getGraphicsContext();
        ArrayList<Position> allPos = model.getAllPos();
        for (Position allPo : allPos) {
            gc.setFill(Color.BLACK);
            gc.fillRect(allPo.getX() * cellSize, allPo.getY() * cellSize, cellSize, cellSize);
            gc.setFill(Color.WHITE);
            gc.fillRect(allPo.getX() * cellSize + 1, allPo.getY() * cellSize + 1, cellSize - 1, cellSize - 1);
        }
    }
    private void drawWalls(){
        GraphicsContext gc = getGraphicsContext();
        gc.setLineWidth(8);
        ArrayList<Position> allPos = model.getAllPos();
        for (Position allPo : allPos) {
            for (int j = 0; j < allPo.getWalls().size(); j++) {
                switch (allPo.getWalls().get(j)) {
                    case UP -> {
                        gc.moveTo(allPo.getX() * cellSize, allPo.getY() * cellSize);
                        gc.lineTo(allPo.getX() * cellSize + cellSize, allPo.getY() * cellSize);
                        gc.stroke();
                    }
                    case DOWN -> {
                        gc.moveTo(allPo.getX() * cellSize, allPo.getY() * cellSize + cellSize);
                        gc.lineTo(allPo.getX() * cellSize + cellSize, allPo.getY() * cellSize + cellSize);
                        gc.stroke();
                    }
                    case LEFT -> {
                        gc.moveTo(allPo.getX() * cellSize, allPo.getY() * cellSize);
                        gc.lineTo(allPo.getX() * cellSize, allPo.getY() * cellSize + cellSize);
                        gc.stroke();
                    }
                    case RIGHT -> {
                        gc.moveTo(allPo.getX() * cellSize + cellSize, allPo.getY() * cellSize);
                        gc.lineTo(allPo.getX() * cellSize + cellSize, allPo.getY() * cellSize + cellSize);
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

    private void keyPressHandler(KeyEvent keyEvent){
        model.setSteps(model.getSteps() + 1);
        switch(keyEvent.getCode()){
            case UP, W -> {
                model.moveBall(Direction.UP);
                drawTable();
                drawBall();
                if(model.isVictory()){
                    nextScene();
                }
            }
            case A, LEFT -> {
                model.moveBall(Direction.LEFT);
                drawTable();
                drawBall();
                if(model.isVictory()){
                    nextScene();
                }
            }
            case D, RIGHT -> {
                model.moveBall(Direction.RIGHT);
                drawTable();
                drawBall();
                if(model.isVictory()){
                    nextScene();
                }
            }
            case S, DOWN -> {
                model.moveBall(Direction.DOWN);
                drawTable();
                drawBall();
                if(model.isVictory()){
                    nextScene();
                }
            }
            case R ->{
                model.setStart();
                drawStart();
            }
        }
    }

    public void initHandlers(Scene scene){
        scene.setOnKeyPressed(this::keyPressHandler);
    }

    public void nextScene(){
        Logger.info("Passing name: {} to next scene", name);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene3.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }catch (IOException e){Logger.error(e.getMessage());}
        Scene3Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(root);

        controller.setName(name);
        controller.setSteps(model.getSteps());
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        controller.setVictoryTime(formatter.format(now));
        controller.showLabels();

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }

}
