package labyrinth.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class Scene3Controller {

    @FXML
    private Label stepsLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;

    private String name;
    private int steps;
    private String victoryTime;

    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }

    public String getVictoryTime() {
        return victoryTime;
    }

    public void setName(String name) {
        this.name = name;
        Logger.info("name is set to {}", name);
    }

    public void setSteps(int steps) {
        this.steps = steps;
        Logger.info("steps is set to {}", steps);
    }

    public void setVictoryTime(String victoryTime) {
        this.victoryTime = victoryTime;
        Logger.info("victoryTime is set to {}", victoryTime);
    }

    public void showLabels(){
        Logger.info("Showing labels on scene 3");
        nameLabel.setText("Congratulations, "+name);
        dateLabel.setText("Date: "+ victoryTime);
        stepsLabel.setText("Steps: "+steps);
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

}