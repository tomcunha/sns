package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DoctorMenuController extends SceneController {

    @FXML
    Button button123;

    @FXML
    public void setButtonPower() throws IOException {
        setScreen(button123, "LoginScene.fxml");

    }

}
