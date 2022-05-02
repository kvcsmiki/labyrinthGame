package labyrinth;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;

public class Scene2Controller {
    @FXML
    VBox root;

    @FXML
    Canvas canvas;

    private String name;

    Model model;

    public void initialize(){
        model = new Model();
    }


    public void setName(String name){
        this.name = name;
    }
}
