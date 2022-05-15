package labyrinth.controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import results.GameResult;
import org.tinylog.Logger;
import results.GameResultRepository;

import java.io.IOException;
import java.util.List;

public class Scene3Controller {

    private GameResultRepository gameResultRepository = new GameResultRepository();

    @FXML
    private Label stepsLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;

    @FXML
    private TextArea resultField;


    private String name;
    private int steps;
    private String victoryTime;
    private int resets;

    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }

    public String getVictoryTime() {
        return victoryTime;
    }

    public int getResets() {
        return resets;
    }

    public void setName(String name) {
        this.name = name;
        Logger.info("name is set to {}", name);
    }

    public void setSteps(int steps) {
        this.steps = steps;
        Logger.info("steps is set to {}", steps);
    }
    public void setResets(int resets){
        this.resets = resets;
        Logger.info("resets is set to {}", resets);
    }

    public void setVictoryTime(String victoryTime) {
        this.victoryTime = victoryTime;
        Logger.info("victoryTime is set to {}", victoryTime);
    }
    private void savePlayer() throws IOException {
        gameResultRepository.addOne(createGameResult());
        Logger.info("player saved");
    }

    public void showLabels(){
        Logger.info("Showing labels on scene 3");
        nameLabel.setText("Congratulations, "+name);
        dateLabel.setText("Date: "+ victoryTime);
        stepsLabel.setText("Steps: "+steps+", resets: "+resets);
        try{
            savePlayer();
        }catch (IOException e){Logger.error(e.getMessage());}
    }

    public void showTable(){
        List<GameResult> gameResultList = gameResultRepository.toList();
        resultField.setText("Previous results: \n");
        for(int i=gameResultList.size()-1;i>(gameResultList.size() > 10 ? gameResultList.size()-11 : 0);i--){
            resultField.setText(resultField.getText() + gameResultList.get(i).toString()+"\n");
        }
    }

    @FXML
    private void newGame(ActionEvent actionEvent) throws IOException {
        Logger.info("Starting new game");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Labyrinth game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void quit(javafx.event.ActionEvent actionEvent){
        Logger.info("Shutting down...");
        Platform.exit();
    }
    private GameResult createGameResult() {
        return GameResult.builder()
                .name(this.name)
                .date(this.victoryTime)
                .steps(this.steps)
                .resets(this.resets)
                .build();

    }

}
