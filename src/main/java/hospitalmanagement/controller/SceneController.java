package hospitalmanagement.controller;

import hospitalmanagement.StartApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void setScreen(Button button, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(StartApplication.class.getResource(fxml));
        stage = ((Stage) button.getScene().getWindow());
        scene = new Scene(fxmlLoader.load(), 1280,720);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public FXMLLoader getFXML(){
        return fxmlLoader;
    }
}
