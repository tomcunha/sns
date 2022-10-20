package hospitalmanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginMenuController extends SceneController{

    @FXML
    Button signInButton;

    @FXML
    public void setSignInButton() throws IOException {
        setScreen(signInButton,"AdminMenuScene.fxml");
    }
}
