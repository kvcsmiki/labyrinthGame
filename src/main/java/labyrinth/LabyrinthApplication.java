package labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;


public class LabyrinthApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger.info("Starting application");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene1.fxml"));
        Parent root = loader.load();
        Scene1Controller controller = loader.getController();
        stage.setTitle("Labyrinth game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
