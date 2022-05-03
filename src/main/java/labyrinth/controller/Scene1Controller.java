package labyrinth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class Scene1Controller {


    @FXML
    private TextField nameField;

    @FXML
    public void nextScene(ActionEvent actionEvent) throws IOException {
        Logger.info("Name entered: {}", nameField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene2.fxml"));
        Parent root = fxmlLoader.load();
        Scene2Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        controller.initHandlers(scene);
        controller.setName(nameField.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }
}
